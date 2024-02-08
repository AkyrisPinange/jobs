package com.jobs.job.service;

import com.jobs.job.entiteis.Job;
import com.jobs.job.model.NewJob;
import com.jobs.job.repositorys.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;
    private final JavaMailSender javaMailSender;

    public String addNewJob(NewJob newJob) {
        Job job = createJobFromNewJob(newJob);
        jobRepository.save(job);

        sendEmail(newJob);

        return "Success";
    }

    private Job createJobFromNewJob(NewJob newJob) {
        Job job = new Job();
        job.setDate(new Date());
        job.setRole(newJob.getRole());
        job.setCompany(newJob.getCompany());
        job.setCountry(newJob.getCountry());
        job.setResponse("waiting");
        job.setGoForward("waiting");
        return job;
    }

    private void sendEmail(NewJob newJob) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("akyris.2903@gmail.com");
            helper.setTo(newJob.getEmail());
            helper.setText(newJob.getContent());
            helper.setSubject(newJob.getRole());
            addAttachment(helper, newJob.getAttachment());
            javaMailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private void addAttachment(MimeMessageHelper helper, MultipartFile attachmentFile) {
        try {
            String fileName = attachmentFile.getOriginalFilename();
            String uploadDirectory = "D:/programin/jobs/src/main/resources/templates";
            Files.createDirectories(Paths.get(uploadDirectory));

            Path filePath = Paths.get(uploadDirectory, fileName);
            try (var outputStream = Files.newOutputStream(filePath)) {
                outputStream.write(attachmentFile.getBytes());
            }
            FileSystemResource file = new FileSystemResource(filePath.toString());
            helper.addAttachment("Invoice", file);
        } catch (IOException | MessagingException e) {
            throw new RuntimeException("Failed to save or attach file", e);
        }
    }

    public List<Job> getJobs() {
       return jobRepository.findAll();
    }
}
