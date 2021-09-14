package cn.tragroup.bootparent.utils.tree;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * 格式化树形的返回结构
 * @author 耿传奇
 * @create 2020-11-03 09:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeResult<I,Entity> {

    /**
     * 格式化之后的列表
     */
    private List<Entity> list;

    /**
     * 格式化之后的map数据
     */
    private Map<I,Entity> mapping;
}
