package com.app.shopping.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 分页返回
 */
@Data
public class PageVo<T> implements Serializable {
    /**
     * 总页数
     */
    private int sum;
    /**
     * 当前页
     */
    private int nowPage;
    /**
     * 数据
     */
    private T data;

    public PageVo(int sum, int nowPage, T data) {
        this.sum = sum;
        this.nowPage = nowPage;
        this.data = data;
    }

}
