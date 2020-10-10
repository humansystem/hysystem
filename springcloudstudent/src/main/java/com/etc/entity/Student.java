package com.etc.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "student")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
@Data
public class Student {
    @Id
    @GeneratedValue
    @Column(name = "stu_id")
    private Integer stuId;
    @Column(name = "stu_name")
    private String stuName;
    @Column(name = "stu_cla_num")
    private String stuClaNum;
}
