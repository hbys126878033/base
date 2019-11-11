package com.wondersgroup.framework.commom.web.view;

import com.github.pagehelper.Page;

import java.io.Serializable;
import java.util.List;

/**
 * @author chenlin
 * @create 2019-06-21 16:53
 * @description: 分页数据视图
 * @version：1.0
 **/
public class ResultPageView implements Serializable {
    /*//成功和失败的返回标志*/
    private boolean success;

    /**
     * 总记录数
     */
    private long total;
    /**
     * 列表数据
     */
    private Page<?> rows;

    public ResultPageView(Page<?> rows) {
        this.rows = rows;
        this.total = rows.getTotal();
    }


    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(Page<?> rows) {
        this.rows = rows;
    }
}
