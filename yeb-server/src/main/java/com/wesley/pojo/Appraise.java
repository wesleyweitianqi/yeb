package com.wesley.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
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
@TableName("t_appraise")
@ApiModel(value="AppraiseObj", description="")
public class Appraise implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "employeeId")
    private Integer eid;

    @ApiModelProperty(value = "考评日期")
    @TableField("appDate")
    private LocalDate appDate;

    @ApiModelProperty(value = "考评结果")
    @TableField("appResult")
    private String appResult;

    @ApiModelProperty(value = "考评内容")
    @TableField("appContent")
    private String appContent;

    @ApiModelProperty(value = "备注")
    private String remark;


}
