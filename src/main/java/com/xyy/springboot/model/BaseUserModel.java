package com.xyy.springboot.model;

import netscape.security.PrivilegeTable;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.security.PrivateKey;
import java.time.LocalDate;

public class BaseUserModel extends AbstractPersistable<Long> {
    private String name;
//    @org.hibernate.validator.constraints.NotEmpty
//    @Range(max = 20, min = 5)
    private Integer age;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDay;
    private Double dou;
    private Double noUse;
    private String initBinder;

    public String getInitBinder() {
        return initBinder;
    }

    public void setInitBinder(String initBinder) {
        this.initBinder = initBinder;
    }

    public Double getNoUse() {
        return noUse;
    }

    public void setNoUse(Double noUse) {
        this.noUse = noUse;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public Double getDou() {
        return dou;
    }

    public void setDou(Double dou) {
        this.dou = dou;
    }

    @Override
    public String toString() {
        return "BaseUserModel{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", birthDay=" + birthDay +
                ", dou=" + dou +
                '}';
    }
}
