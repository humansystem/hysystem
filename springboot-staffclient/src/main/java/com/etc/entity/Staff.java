package com.etc.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "staff")
@Data
public class Staff {
    @Id
    @Column(name = "staff_id")
    private Integer staffId;    //员工ID
    @Column(name = "dept_id")
    private Integer deptId;     //部门ID
    @Column(name = "staff_num")
    private String staffNum;    //员工编号
    @Column(name = "staff_name")
    private String staffName;   //员工姓名
    @Column(name = "position")
    private Integer position;   //职位
    @Column(name = "edu_background")
    private String  eduBackground; //学历
    @Column(name = "major")
    private String major;        //专业
    @Column(name = "salary")
    private Integer salary;     //薪资
    @Column(name = "birthday")
    private String birthday;    //出生日期
    @Column(name = "picture")
    private String picture;     //照片
    @Column(name = "native_place")
    private String nativePlace; //户籍
    @Column(name = "now_address")
    private String nowAddress;  //现居住地
    @Column(name = "idcard_no")
    private String idcardNo;    //身份证号
    @Column(name = "phone")
    private String phone;       //电话号码
    @Column(name = "work_seniority")
    private Integer workSeniority; //工作资历

}
