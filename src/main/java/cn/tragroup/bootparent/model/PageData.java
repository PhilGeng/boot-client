package cn.tragroup.bootparent.model;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.CaseFormat;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class PageData {

    /**
     * 当前页
     */
    private int current = 1;
    /**
     * 每页条数
     */
    private int size = 15;
    /**
     * 总数
     */
    private int total = 0;
    /**
     * 总页数
     */
    private int pages = 1;
    /**
     * 是否正序
     */
    private Boolean asc;
    /**
     * 排序的列名
     */
    private String order;

    @JsonIgnore
    private transient List<String> whiteList = Arrays.asList("number", "create_time", "id", "total_time", "count");

    public void setOrder(String order) {
        if (order != null && !contains(order)) throw new RuntimeException("未经允许的排序字段:[ " + order + " ]");
        this.order = order;
    }

    private boolean contains(String order) {
        if (order.matches("`(.+)`"))
            order = order.substring(1, order.length() - 2);
        //驼峰转下划线
        order = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, order);
        return whiteList.contains(order);
    }

    public <T> Page<T> generatePage() {
        Page<T> page = new Page<>();
        page.setCurrent(current);
        page.setSize(size);
        return page;
    }

}
