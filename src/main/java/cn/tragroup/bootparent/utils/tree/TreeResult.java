package cn.tragroup.bootparent.utils.tree;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @author 耿传奇
 * @create 2020-11-03 09:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeResult<I,Entity> {

    private List<Entity> list;

    private Map<I,Entity> mapping;
}
