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

@Repository
public interface StaffDao extends JpaRepository<Staff,Integer>, JpaSpecificationExecutor<Staff>, Serializable {
    @Query("select t from Staff t where t.staffName like ?1")
    public Page<Staff> findByStaffNameLike(Pageable pageable,String key);
    @Query("select t from Staff t where t.deptId=?1")
    public Page<Staff> findByDeptId(Pageable pageable,Integer departId);
    @Query("select t from Staff t ")
    public Page<Staff> findAllStaff(Pageable pageable);
}
