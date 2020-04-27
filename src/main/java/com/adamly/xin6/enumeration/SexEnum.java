package com.adamly.xin6.enumeration;

/**
 * @author adamly
 * @version 1.0
 * @date 2020/3/22 14:01
 */
public enum SexEnum {
    MALE(1,"男"),
    FEMALE(2,"女");

    private int id;
    private String name;

    SexEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static SexEnum getEnumById(int id){
        for(SexEnum sex : SexEnum.values()){
            if(sex.getId() == id){
                return sex;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(byte id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
