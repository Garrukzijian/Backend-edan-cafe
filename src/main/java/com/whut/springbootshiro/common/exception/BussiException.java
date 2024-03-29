package com.whut.springbootshiro.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 自定义业务异常类
 * 在很多业务场景中,涉及到多张表的更新操作.
 * 如果发生A表更新成功  B表更新失败. 此时需要进行事务回滚
 * 而事务的回滚则依赖异常.只有发生了异常  事务才会回滚.
 * 此时需要程序自己抛出异常.所以自定义一个异常.因为程序抛出的异常无法控制
 * 各种类型的异常都可能.所以自定义异常,开发者在全局异常处理器中捕获异常.
 * 进行自定义异常的处理,既可以回滚事务,也可以提高程序与用户的交互体验.
 * @author: Todd Ding
 * @date 2020-11-30 11:55
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BussiException extends RuntimeException {

    private Integer code; // 异常码

    private String message; // 异常信息

}
