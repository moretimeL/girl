package com.hrd;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="girl")
public class Girlproperty {

    public String getCupSize() {
        return cupSize;
    }

    public Integer getAge() {
        return age;
    }

    private String cupSize;

    public void setCupSize(String cupSize) {
        this.cupSize = cupSize;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    private Integer age;



}
