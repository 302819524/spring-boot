package com.xyy.springboot.services;

import com.xyy.springboot.domain.BaseUser;

public interface BaseService {
    BaseUser getBaseUser(Long id);

    void putBaseUser(BaseUser baseUser);

    /**
     * 切面日志
     */
    void aspectLog();
    void aspectLogArgs(BaseUser baseUser, int i);
}
