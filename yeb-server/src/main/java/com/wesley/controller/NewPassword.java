package com.wesley.controller;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

@Data
@EqualsAndHashCode
@Accessors(chain = true)
@Component
@ApiModel(value="PasswordChange", description = "")
public class NewPassword {
    @ApiModelProperty(value="newPassword", required = true)
    private String newPassword;

    // Add a default constructor
    public NewPassword() {
    }

    // Add a constructor that takes a String parameter for deserialization
    public NewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
