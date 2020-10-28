package com.etc.dao;

import com.etc.entity.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface StaffDao extends JpaRepository<Staff,Integer>, JpaSpecificationExecutor<Staff>, Serializable {

    @Query(value = "select staff.*,b.dept_name,c.job_name FROM staff,depart b,job c where staff.dept_id=b.dept_id\n" +
            "and b.dept_id=c.jdepart_id and staff.job_id=c.job_id",nativeQuery = true)
    public Page<Staff> findAllStaff(Pageable pageable);
    @Query(value = "SELECT staff.*,b.dept_name,c.job_name FROM staff \n" +
            "LEFT JOIN depart b ON a.dept_id=b.dept_id\n" +
            "LEFT JOIN job c ON staff.dept_id=c.jdepart_id and staff.job_id=c.job_id where staff.staff_name like %+?2+%",nativeQuery = true)
    public Page<Staff> findByStaffNameLike(Pageable pageable,String key);
    @Query(value = "SELECT a.*,b.dept_name,c.job_name FROM staff a\n" +
            "LEFT JOIN depart b ON a.dept_id=b.dept_id\n" +
            "LEFT JOIN job c ON a.dept_id=c.jdepart_id and a.job_id=c.job_id where a.dept_id=?2",nativeQuery = true)
    public Page<Staff> findByDeptId(Pageable pageable,Integer deptId);
}
