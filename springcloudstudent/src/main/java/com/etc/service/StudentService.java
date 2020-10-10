package com.etc.service;

import com.etc.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    public Page<Student> queryStu(Pageable pageable);
    public Student findById(Integer stuId);
    public void deleteStu(Integer stuId);
    public void addStu(Student stu);
    public void updateStu(Student stu);
}
