package com.etc.controller;

import com.etc.entity.Staff;
import com.etc.service.StaffService;
import com.netflix.ribbon.proxy.annotation.Http;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
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

@Controller
@RequestMapping("/staffController")
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

    @RequestMapping("/getAllStaff")
    public ModelAndView getAllStaff(@PageableDefault(value = 5,sort = {"staffId"},direction = Sort.Direction.DESC)Pageable pageable){
        Page<Staff> page = staffService.findAllStaff(pageable);
        ModelAndView mv = new ModelAndView();
        mv.addObject("pageInfo",page);
        mv.setViewName("staffPage");
        return mv;
    }

    @RequestMapping("/findStaffByDepartId/{departId}")    //查询某部门员工信息
    public Page<Staff> findStaffByDepartId(@PageableDefault(value = 5,sort = {"staffId"},direction = Sort.Direction.DESC)Pageable pageable, @PathVariable("departId") Integer departId){
        Page<Staff> page = staffService.findStaffByDepartId(pageable,departId);
        return page;
    }

    @RequestMapping("/addStaff/{staff}")
    public void addStaff(MultipartFile mf, @PathVariable("staff") Staff staff, HttpSession session){   //添加员工信息
        DateTimeFormatter  dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        staff.setBirthday(LocalDateTime.now().format(dtf));
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
            staff.setPicture(fileName);
            staffService.addStaff(staff);
        }

    }
    @RequestMapping("/toAddStaff")
    public String toAddStaff(HttpSession session){   //前往添加员工信息页面
        Map<String,Object> map = restTemplate.getForObject("http://localhost:82/getDepartByStatus",Map.class);
        session.setAttribute("departs",map);
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
