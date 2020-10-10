package com.etc.controller;

import com.etc.entity.Student;
import com.etc.feigninters.ClaFeignClient;
import com.etc.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceView;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("/stuController")
public class StudentController {
    @Resource
    StudentService studentService;

    @Autowired
    RestTemplate restTemplate;

    @Resource
    ClaFeignClient claFeignClient;

    @RequestMapping("/getOneStu/{stuId}")
    public ModelAndView getOneStu(@PathVariable Integer stuId){
        Student student = studentService.findById(stuId);
        Map<String,Object> map = claFeignClient.getClaByClaNum(student.getStuClaNum());
        map.put("stuId",student.getStuId());
        map.put("stuName",student.getStuName());
        map.put("stuClaNum",student.getStuClaNum());
        ModelAndView mv = new ModelAndView();
        mv.addObject("map",map);
        mv.setViewName("studentDetail");
        return mv;
    }

    @RequestMapping("/queryByPage")
    public ModelAndView getAllStu(@PageableDefault(value = 5,sort = {"stuId"},direction = Sort.Direction.DESC)Pageable pageable){
 
        Page<Student> page = studentService.queryStu(pageable);
        ModelAndView mv = new ModelAndView();
        mv.addObject("pageInfo",page);
        mv.setViewName("studentPage");
        return mv;
    }

    @RequestMapping("/updateStu")
    public ModelAndView updateStu(@RequestParam("stuName") String stuName,
                                  @RequestParam("claName") String claName,
                                  @RequestParam("stuId") Integer stuId){
        Map<String,Object> map = restTemplate.getForObject("http://localhost:8765/claController/getClaByName/"+claName
                , Map.class);
        String claNum = (String)map.get("claNum");
        Student stu = new Student();
        stu.setStuClaNum(claNum);
        stu.setStuId(stuId);
        stu.setStuName(stuName);
        studentService.updateStu(stu);
        ModelAndView mv = new ModelAndView(new InternalResourceView("/stuController/queryByPage"));
        return mv;
    }
}
