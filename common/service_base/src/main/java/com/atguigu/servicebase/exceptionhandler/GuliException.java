package com.atguigu.servicebase.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author shkstart
 * @create 2021-05-11-16:59
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuliException extends RuntimeException{

    private Integer code;
    private String msg;
}
