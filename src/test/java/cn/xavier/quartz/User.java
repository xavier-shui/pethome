package cn.xavier.quartz;

import lombok.Data;

import java.io.Serializable;

@Data
class User implements Serializable
{
    private Long id;
    private String name;

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public User() {
    }
}