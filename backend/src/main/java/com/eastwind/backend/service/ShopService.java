package com.eastwind.backend.service;

import com.eastwind.backend.model.Shop;

import java.util.List;

public interface ShopService {
    Integer insertShop(Shop shop);

    List<Shop> findAllShopsOfUser(Integer userId);

    Shop findShopByPk(Integer shopId);

    Integer modifyShop(Shop shop);

    List<Shop> findNearShops(Double longitude, Double latitude);
}
