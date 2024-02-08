package com.jobs.job.controllers;


import com.jobs.job.entiteis.Job;
import com.jobs.job.model.NewJob;
import com.jobs.job.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/job/")
@CrossOrigin(origins = "http://127.0.0.1:8080")
public class JobController {

    private final JobService jobService;
    @PostMapping("newJob")
    public String newJob(
            @ModelAttribute  NewJob newJob
    ) {
        return jobService.addNewJob(newJob);
    }

    @GetMapping("jobs")
    public List<Job> getJob(
    ) {
        return jobService.getJobs();
    }
}
