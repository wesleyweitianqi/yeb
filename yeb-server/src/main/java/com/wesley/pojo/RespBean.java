package com.wesley.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespBean {
    private long code;
    private String message;
    private Object obj;

    /**
     * return result if success
     * @param message
     * @return
     */
    public static RespBean success(String message){
        return new RespBean(200,message, null);
    }

    /**
     *
     * @param message
     * @param obj
     * @return
     */
    public static RespBean success(String message, Object obj){
        return new RespBean(200,message, obj);
    }
    public static RespBean error(String message){
        return new RespBean(500, message, null);
    }
    /**
     * res if fail
     * @param message
     * @param obj
     * @return
     */
    public static RespBean error(String message, Object obj){
        return new RespBean(500, message, obj);
    }
}
