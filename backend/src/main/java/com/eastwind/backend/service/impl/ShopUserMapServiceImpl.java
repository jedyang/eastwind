package com.eastwind.backend.service.impl;

import com.eastwind.backend.mapper.ShopUserMapMapper;
import com.eastwind.backend.model.ShopUserMap;
import com.eastwind.backend.service.ShopUserMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("shopUserMapService")
public class ShopUserMapServiceImpl implements ShopUserMapService {

    @Autowired
    private ShopUserMapMapper shopUserMapMapper;

    @Override
    public int insertRecord(ShopUserMap shopUserMap) {
        int i = shopUserMapMapper.insertSelective(shopUserMap);
        return i;
    }
}
