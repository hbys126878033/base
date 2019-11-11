package com.wondersgroup.core.web.view;

import com.wondersgroup.core.exception.CustomException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author chenlin
 * @create 2019-06-13 22:24
 * @description: TODO
 * @version：1.0
 **/
@Setter
@Getter
@ToString
public class ResultView<T> {

    private Boolean success;
    private String message;
    private T data;

    public ResultView() {
        this.success = true;
    }

    public ResultView(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ResultView(CustomException e) {
        this.success = false;
        this.message = e.getMessage();
    }
}
