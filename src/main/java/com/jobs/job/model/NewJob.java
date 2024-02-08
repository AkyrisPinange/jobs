package com.jobs.job.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewJob {

    private String email;
    private String role;
    private String company;
    private String country;
    private String content;
    private MultipartFile attachment;

}
