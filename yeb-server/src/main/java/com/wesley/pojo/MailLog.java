package com.wesley.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
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
@TableName("t_mail_log")
@ApiModel(value="MailLog对象", description="")
public class MailLog implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "msgId")
    @TableField("msg_id")
    private String msgId;

    @ApiModelProperty(value = "ReciverId")
    private Integer eid;

    @ApiModelProperty(value = "status（0:sending 1:succeed 2:fail）")
    private Integer status;

    @ApiModelProperty(value = "routeKey")
    @TableField("route_key")
    private String routeKey;

    @ApiModelProperty(value = "exchange")
    private String exchange;

    @ApiModelProperty(value = "retrycount")
    private Integer count;

    @ApiModelProperty(value = "tryTime")
    @TableField("try_time")
    private LocalDateTime tryTime;

    @ApiModelProperty(value = "createDate")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "updateDate")
    @TableField("update_time")
    private LocalDateTime updateTime;


}
