package com.wesley.pojo;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode
@Accessors(chain = true)
public class ChatMsg {
    public String from;
    public String to;
    public String content;
    public LocalDateTime date;
    public String fromNickName;

}
