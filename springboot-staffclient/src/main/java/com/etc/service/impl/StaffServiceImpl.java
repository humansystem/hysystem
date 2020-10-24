package com.etc.service.impl;

import com.etc.dao.StaffDao;
import com.etc.entity.Staff;
import com.etc.service.StaffService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class StaffServiceImpl implements StaffService {
    @Resource
    StaffDao staffDao;
    @Override
    public Page<Staff> findAllStaff(Pageable pageable) {
        return staffDao.findAllStaff(pageable);
    }

    @Override
    public Page<Staff> findStaffByDepartId(Pageable pageable,Integer departId) {
        return staffDao.findByDeptId(pageable,departId);
    }

    @Override
    public Page<Staff> findByStaffNameLike(Pageable pageable,String key) {
        return staffDao.findByStaffNameLike(pageable,key);
    }

    @Override
    public Staff getOneStaff(Integer staffId) {
        Optional<Staff> optional = staffDao.findById(staffId);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    @Override
    public void addStaff(Staff staff) {
        staffDao.save(staff);
    }

    @Override
    public void delStaff(Integer staffId) {
        staffDao.deleteById(staffId);
    }

    @Override
    public void updateStaff(Staff staff) {
        staffDao.save(staff);
    }
}
