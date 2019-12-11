package com.eastwind.backend.service;

import com.eastwind.backend.model.ShopUser;

import java.util.List;

public interface ShopUserService {
    int addShopUser(ShopUser shopUser);

    List<ShopUser> findBySelect(ShopUser shopUser);

    ShopUser findByOpenId(String openId);
}
