package cn.tragroup.bootparent.model;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Getter;
import lombok.Setter;

/**
 * @author GengChuanqi
 * @create 2020-06-17 17:33
 */
@Getter
@Setter
@SuppressWarnings({"UnusedReturnValue", "unchecked"})
public class EditorModel<T> {


    @TableField(value = "crt_id", updateStrategy = FieldStrategy.NEVER)
    private Integer crtId;
    @TableField(value = "crt_name", updateStrategy = FieldStrategy.NEVER)
    private String crtName;

    @TableField(value = "upd_id", insertStrategy = FieldStrategy.NEVER)
    private Integer updId;
    @TableField(value = "upd_name", insertStrategy = FieldStrategy.NEVER)
    private String updName;


    public T setUpdater(RequestUser user) {
        if (user != null) {
            this.updId = user.getId();
            this.updName = user.getRealName();
        }
        return (T)this;
    }

    public T setCreator(RequestUser user) {
        if (user != null) {
            this.crtId = user.getId();
            this.crtName = user.getRealName();
        }
        return  (T)this;
    }
}
