package com.eastwind.backend.service;

import com.eastwind.backend.model.ConsumeLog;
import com.eastwind.backend.model.Pay;
import com.eastwind.backend.model.ShopMemberMap;

import java.util.List;

public interface ShopMemberMapService {

    int insertRecord(ShopMemberMap shopMemberMap);

    List<ShopMemberMap> findRecord(ShopMemberMap shopMemberMap);

    ShopMemberMap findByPK(Integer id);

    int updateRecord(ShopMemberMap shopMemberMap);

    ConsumeLog pay(Pay payInfo);
}
