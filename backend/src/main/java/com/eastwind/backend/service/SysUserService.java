package com.eastwind.backend.service;

import com.eastwind.backend.model.SysUsers;
import com.eastwind.backend.model.SysUsersExample;

import java.util.List;

public interface SysUserService {
    List<SysUsers> findList();

    List<SysUsers> findListByCondation(SysUsersExample param);

    /**
     * 获取盐值
     * @param username
     * @return
     */
    String selectSaltByName(String username);

    /**
     * 注册用户
     * @param user
     * @return
     */
    int register(SysUsers user);
}
