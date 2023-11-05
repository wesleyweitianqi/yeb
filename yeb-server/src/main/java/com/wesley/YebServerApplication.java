package com.wesley;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@MapperScan("com.wesley.mapper")
public class YebServerApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(YebServerApplication.class,
                args);
    }
}
