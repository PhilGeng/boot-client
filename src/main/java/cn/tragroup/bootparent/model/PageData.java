package cn.tragroup.bootparent.model;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

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


    public <T> Page<T> generatePage(){
        Page<T> page = new Page<>();
        page.setCurrent(current);
        page.setSize(size);
        return page;
    }

}
