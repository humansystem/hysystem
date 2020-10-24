package com.etc.service;

import com.etc.entity.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StaffService {
    Page<Staff> findAllStaff(Pageable pageable);  //分页查询所有员工信息
    Page<Staff> findStaffByDepartId(Pageable pageable,Integer departId);  //查询某部门的信息
    Page<Staff> findByStaffNameLike(Pageable pageable,String key);      //根据名字模糊查询
    Staff getOneStaff(Integer staffId); //获取员工详细信息
    void addStaff(Staff staff);      //添加员工
    void delStaff(Integer staffId);  //删除员工
    void updateStaff(Staff staff);   //修改员工信息
}
