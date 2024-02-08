package com.jobs.job.repositorys;

import com.jobs.job.entiteis.Job;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JobRepository extends MongoRepository<Job,String> {
}
