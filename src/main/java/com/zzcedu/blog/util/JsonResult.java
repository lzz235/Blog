package com.zzcedu.blog.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author liu
 * @Date 2020/12/29 17:02
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JsonResult {
    /**
     * 响应状态
     * 0是成功 其他的都是失败
     */
    private Integer status;
    /**
     * 封装后台的响应信息
     */
    private String msg;
    /**
     * 封装后台传给前台的数据
     */
    private Object data;
}
