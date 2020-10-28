package com.etc;

import com.etc.dao.StaffDao;
import com.etc.entity.Staff;
import com.etc.service.StaffService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import javax.annotation.Resource;

@SpringBootTest
class SpringbootStaffclientApplicationTests {
    @Resource
    StaffDao staffDao;
    @Test
    void contextLoads() {
    }

    @Test
    void testGetDetail(){
        System.out.println(staffDao.findAll());
    }

}
