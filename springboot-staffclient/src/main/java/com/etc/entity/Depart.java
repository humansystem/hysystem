package com.etc.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "depart")
@Data
public class Depart {
    @Id
    @Column(name = "dept_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer deptId;
    @Column(name = "dept_name")
    private String deptName;
    @Column(name = "status")
    private Integer status;
}
