package com.etc.dao;

import com.etc.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;


@Repository
public interface StudentDao extends JpaRepository<Student,Integer>, JpaSpecificationExecutor<Student>, Serializable {

    @Query("select stu from Student stu")
     Page<Student> getAllStudents(Pageable pageable);

    @Query("delete from Student where stuId=?1")
     void deleteStudentByStuId(Integer stuId);
}
