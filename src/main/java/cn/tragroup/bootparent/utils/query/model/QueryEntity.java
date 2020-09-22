package cn.tragroup.bootparent.utils.query.model;

import lombok.Data;

@Data
public class QueryEntity {

    private String column;

    private QueryType type;

    private Object value;


    public QueryEntity() {
    }

    public QueryEntity(String column, QueryType type, Object value) {
        this.column = column;
        this.type = type;
        this.value = value;
    }
}