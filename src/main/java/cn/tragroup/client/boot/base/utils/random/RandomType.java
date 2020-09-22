package cn.tragroup.client.boot.base.utils.random;

import java.util.Arrays;

public enum RandomType {
    Number(new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"}),
    Low(new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"}),
    Upper(new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"});

    private String[] value ;

    RandomType (String[] value){
        this.value(value);
    }

    public String[] value(){
        return value;
    }

    public void value(String[] value){
        this.value = value;
    }

}