package com.etc.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "job")
@Data
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="job_id")
    private Integer jobId;
    @Column(name = "job_name")
    private String jobName;
    @Column(name = "jdepart_id")
    private Integer jdepartId;
}
