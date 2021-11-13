package cn.xavier.basic.util;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 异步请求返回json对象
 * @author Zheng-Wei Shui
 * @date 11/13/2021
 */
@Data
@NoArgsConstructor(staticName = "of")
public class AjaxResponse {
    private Boolean success = true;
    private String message = "操作成功";
    // chain method
    public AjaxResponse setSuccess(Boolean success) {
        this.success = success;
        return this;
    }

    public AjaxResponse setMessage(String message) {
        this.message = message;
        return this;
    }
}
