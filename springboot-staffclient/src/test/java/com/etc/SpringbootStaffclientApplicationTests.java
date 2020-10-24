package com.etc;

import com.etc.entity.Staff;
import com.etc.service.StaffService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import javax.annotation.Resource;

@SpringBootTest
class SpringbootStaffclientApplicationTests {
    @Resource
    StaffService staffService;
    @Test
    void contextLoads() {
    }


}
