package com.eastwind.backend.service.impl;

import com.eastwind.backend.mapper.SysUsersMapper;
import com.eastwind.backend.model.SysUsers;
import com.eastwind.backend.model.SysUsersExample;
import com.eastwind.backend.service.SysUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * @description: TODO
 * @author uncleY
 * @date 2019/12/11 9:48
 */
@Slf4j
@Service("SysUserService")
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUsersMapper sysUsersMapper;

    @Override
    public List<SysUsers> findList() {
        List<SysUsers> sysUsers = sysUsersMapper.selectByExample(null);
        return sysUsers;
    }

    @Override
    public List<SysUsers> findListByCondation(SysUsersExample param) {
        List<SysUsers> sysUsers = sysUsersMapper.selectByExample(param);
        return sysUsers;
    }

    @Override
    public String selectSaltByName(String username) {
        SysUsersExample example = new SysUsersExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<SysUsers> sysUsers = sysUsersMapper.selectByExample(example);
        if (null == sysUsers || sysUsers.size() != 1) {
            return "";
        }
        return sysUsers.get(0).getSalt();
    }

    @Override
    public int register(SysUsers user) {
        int i = sysUsersMapper.insertSelective(user);
        return i;
    }
}
