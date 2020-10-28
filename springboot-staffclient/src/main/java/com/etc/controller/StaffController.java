package com.etc.controller;

import com.etc.entity.Depart;
import com.etc.entity.Staff;
import com.etc.service.StaffService;
import com.netflix.ribbon.proxy.annotation.Http;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/staffController")
@CrossOrigin("*")
public class StaffController {
    @Resource
    StaffService staffService;
    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/getStaffDetail/{staffId}")    //获取某员工详细信息
    public Map<String,Object> getStaffDetail(@PathVariable("staffId") Integer staffId){
        Staff staff = staffService.getOneStaff(staffId);
        Map<String,Object> map = new HashMap<>();
        map.put("staffName",staff.getStaffName());
        map.put("staffNum",staff.getStaffNum());
        map.put("now_address",staff.getNowAddress());
        return map;
    }

    @RequestMapping("/getAllStaff")     //获取所有的数据
    public Map<String,Object> getAllStaff(@PageableDefault(value = 5,sort = {"staff_id"},direction = Sort.Direction.DESC)Pageable pageable){
        Page<Staff> page = staffService.findAllStaff(pageable);
        Map<String,Object> map = new HashMap<>();
        map.put("page",page);
        return map;
    }

    @RequestMapping("/findStaffByDepartId/{departId}")    //查询某部门员工信息
    public Map<String,Object> findStaffByDepartId(@PageableDefault(value = 5,sort = {"staffId"},direction = Sort.Direction.DESC)Pageable pageable, @PathVariable("departId") Integer departId){
        Page<Staff> page = staffService.findStaffByDepartId(pageable,departId);
        Map<String,Object> map = new HashMap<>();
        map.put("page",page);
        return map;
    }

    @RequestMapping(value = "/uploadImg")
    public void uploadImg(MultipartFile file){
        System.out.println("12344");
        System.out.println(file);
        DateTimeFormatter  dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Staff staff = new Staff();
        staff.setHiredate(LocalDateTime.now().format(dtf));
        staff.setStaffStatus("在职");
        String fileName = UUID.randomUUID().toString()+".jpg";
        //文件保存路径
        String path = "D://IdeaProjects//springboot-staffclient//src//main//resources//static//img"+fileName;
        //文件保存的真实路径
        //System.out.println(session.getServletContext().getRealPath("/"));
        if (file != null&&file.getSize()!=0){
            try{
                //把文件按照path路径存储起来
                file.transferTo(new java.io.File(path));
            } catch (Exception e) {
                e.printStackTrace();
            }

            staff.setPicture(fileName);
            System.out.println(staff);
            staffService.addStaff(staff);
        }

    }

    @RequestMapping("/addStaff")
    public ModelAndView addStaff(MultipartFile mf , HttpSession session,
                                 @RequestParam("staffNum") String staffNum,
                                 @RequestParam("staffName") String staffName,
                                 @RequestParam("deptId") Depart deptId,
                                 @RequestParam("position") Integer position,
                                 @RequestParam("eduBackground") String eduBackground,
                                 @RequestParam("major") String major,
                                 @RequestParam("salary") Integer salary,
                                 @RequestParam("nativePlace") String nativePlace,
                                 @RequestParam("nowAddress") String nowAddress,
                                 @RequestParam("idcardNo") String idcardNo,
                                 @RequestParam("phone") String phone,
                                 @RequestParam("workSeniority") String workSeniority,
                                 @RequestParam("staffStatus") String staffStatus){   //添加员工信息
        DateTimeFormatter  dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Staff staff = new Staff();
        staff.setHiredate(LocalDateTime.now().format(dtf));
        staff.setDeptId(deptId);
        staff.setStaffNum(staffNum);
        staff.setStaffName(staffName);
        //staff.setPosition(position);
        staff.setEduBackground(eduBackground);
        staff.setMajor(major);
        staff.setSalary(salary);
        staff.setNativePlace(nativePlace);
        staff.setNowAddress(nowAddress);
        staff.setIdcardNo(idcardNo);
        staff.setPhone(phone);
        staff.setWorkSeniority(workSeniority);
        staff.setStaffStatus("在职");
        String fileName = UUID.randomUUID().toString()+".jpg";
        //文件保存路径
        String path = session.getServletContext().getRealPath("/")+"img/"+fileName;
        //文件保存的真实路径
        System.out.println(session.getServletContext().getRealPath("/"));
        if (mf != null&&mf.getSize()!=0){
            try{
                //把文件按照path路径存储起来
                mf.transferTo(new java.io.File(path));
            } catch (Exception e) {
                e.printStackTrace();
            }
        System.out.println(staff);
            staff.setPicture(fileName);
            staffService.addStaff(staff);
       }
        ModelAndView mv = new ModelAndView(new InternalResourceView("/staffController/getAllStaff"));
        return mv;

    }
    @RequestMapping("/toAddStaff")
    public String toAddStaff(HttpSession session){   //前往添加员工信息页面
        //Map<String,Object> map = restTemplate.getForObject("http://localhost:82/getDepartByStatus",Map.class);
        //session.setAttribute("departs",map);
        return "addStaff";
    }

    @RequestMapping("/getJobByDepart/{departId}")   //ajax响应获取某部门工作
    @ResponseBody
    public Map<String,Object> getJobByDepart(@PathVariable("departId") Integer departId){
        Map<String,Object> map = restTemplate.getForObject("http://localhost:83/getJobBydepart/"+departId,Map.class);
        return map;
    }

    @RequestMapping("/modStaffMessage/{staff}")      //修改员工信息
    public ModelAndView modStaffMessage(@PathVariable("staff") Staff staff){
        ModelAndView mv = new ModelAndView(new InternalResourceView("/staffController/getAllStaff"));
        staffService.updateStaff(staff);
        return mv;
    }
    @RequestMapping("/delStaff/{staffId}")     //删除员工信息
    public ModelAndView delStaff(@PathVariable("staffId") Integer staffId){
        ModelAndView mv = new ModelAndView(new InternalResourceView("/staffController/getAllStaff"));
        staffService.delStaff(staffId);
        return mv;
    }
}
