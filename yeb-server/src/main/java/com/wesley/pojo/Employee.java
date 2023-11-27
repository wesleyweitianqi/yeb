package com.wesley.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Wesley
 * @since 2023-11-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_employee")
@ApiModel(value="Employee", description="")
public class Employee implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "employee#")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "employee name")
    @Excel(name="name")
    private String name;

    @ApiModelProperty(value = "sex")
    @Excel(name="sex")
    private String gender;

    @ApiModelProperty(value = "birth")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    @Excel(name="birth", width = 20, format = "yyyy-MM-dd")
    private LocalDate birthday;

    @ApiModelProperty(value = "id")
    @Excel(name="id", width = 30)
    private String idCard;

    @ApiModelProperty(value = "wedding")
    @Excel(name = "wedding")
    private String wedlock;

    @ApiModelProperty(value = "ethic")
    private Integer nationId;

    @ApiModelProperty(value = "籍贯")
    @Excel(name="nativePlace")
    private String nativePlace;

    @ApiModelProperty(value = "politic")
    private Integer politicId;

    @ApiModelProperty(value = "email adress")
    @Excel(name="email", width = 30)
    private String email;

    @ApiModelProperty(value = "phone")
    @Excel(name="phone", width = 15)
    private String phone;

    @ApiModelProperty(value = "联系地址")
    @Excel(name="address", width = 40)
    private String address;

    @ApiModelProperty(value = "department")
    private Integer departmentId;

    @ApiModelProperty(value = "joblevelId")
    private Integer jobLevelId;

    @ApiModelProperty(value = "posId")
    private Integer posId;

    @ApiModelProperty(value = "engageform")
    private String engageForm;

    @ApiModelProperty(value = "education")
    private String tiptopDegree;

    @ApiModelProperty(value = "specialty")
    private String specialty;

    @ApiModelProperty(value = "school")
    private String school;

    @ApiModelProperty(value = "beginDate")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private LocalDate beginDate;

    @ApiModelProperty(value = "workState")
    private String workState;

    @ApiModelProperty(value = "workId")
    @TableField("workId")
    private String workId;

    @ApiModelProperty(value = "contractTerm")
    private Double contractTerm;

    @ApiModelProperty(value = "conversionTime")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private LocalDate conversionTime;

    @ApiModelProperty(value = "notWorkDate")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    @TableField("notWorkDate")
    private LocalDate notWorkDate;

    @ApiModelProperty(value = "beginContract")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private LocalDate beginContract;

    @ApiModelProperty(value = "endContract")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private LocalDate endContract;

    @ApiModelProperty(value = "workAge")
    @TableField("workAge")
    private Integer workAge;

    @ApiModelProperty(value = "salaryId")
    private Integer salaryId;

    @ApiModelProperty(value = "nation")
    @TableField(exist = false)
    @ExcelEntity(name="nation")
    private Nation nation;

    @ApiModelProperty(value = "politicsStatus")
    @TableField(exist = false)
    @ExcelEntity(name="politicsStatus")
    private PoliticsStatus politicsStatus;

    @ApiModelProperty(value ="department")
    @TableField(exist = false)
    @ExcelEntity(name="department")
    private Department department;

    @ApiModelProperty(value ="joblevel")
    @TableField(exist = false)
    @ExcelEntity(name="joblevel")
    private Joblevel joblevel;

    @ApiModelProperty(value ="position")
    @TableField(exist = false)
    @ExcelEntity(name="position")
    private Position position;
}
