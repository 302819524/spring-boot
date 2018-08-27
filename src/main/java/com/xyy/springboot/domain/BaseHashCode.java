package com.xyy.springboot.domain;

/**
 * @author xuyy
 * @date 2018/7/13 15:13
 */
public class BaseHashCode {
    private String name;
    private Integer age;

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

    @Override
    public int hashCode() {
        return 3;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        BaseHashCode baseHashCode = (BaseHashCode) obj;

        if (name != null ? !name.equals(baseHashCode.name) : baseHashCode.name != null) return false;
        return age != null ? age.equals(baseHashCode.age) : baseHashCode.age == null;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
