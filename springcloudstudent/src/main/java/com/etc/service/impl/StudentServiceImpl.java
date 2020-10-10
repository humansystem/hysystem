package com.etc.service.impl;

import com.etc.dao.StudentDao;
import com.etc.entity.Student;
import com.etc.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    @Resource
    StudentDao studentDao;

    @Override
    public Page<Student> queryStu(Pageable pageable) {
        return studentDao.getAllStudents(pageable);
    }

    @Override
    public Student findById(Integer stuId) {
        Optional<Student> optional = studentDao.findById(stuId);
        //调用JPA接口自带的方法 findById(),不建议使用getOne()方法，查不到会出错。
        if (optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    @Override
    public void deleteStu(Integer stuId) {
          studentDao.deleteStudentByStuId(stuId);
    }

    @Override
    public void addStu(Student stu) {
        studentDao.save(stu);
    }

    @Override
    public void updateStu(Student stu) {
        studentDao.save(stu);
    }
}
