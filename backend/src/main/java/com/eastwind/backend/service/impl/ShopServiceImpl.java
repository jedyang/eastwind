package com.eastwind.backend.service.impl;

import com.eastwind.backend.mapper.ShopMapper;
import com.eastwind.backend.model.Shop;
import com.eastwind.backend.service.ShopService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "shopService")
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopMapper shopMapper;

    @Override
    public Integer insertShop(Shop shop) {
        int shopId = shopMapper.insertSelective(shop);
        return shopId;
    }

    @Override
    public List<Shop> findAllShopsOfUser(Integer userId) {
        List<Shop> shops = shopMapper.selectAllShopsOfUser(userId);
        return shops;
    }

    @Override
    public Shop findShopByPk(Integer shopId) {
        Shop shop = shopMapper.selectByPrimaryKey(shopId);
        return shop;
    }

    @Override
    public Integer modifyShop(Shop shop) {
        int i = shopMapper.updateByPrimaryKeySelective(shop);
        return i;
    }

    @Override
    public List<Shop> findNearShops(Double longitude, Double latitude) {
        List<Shop> shops = shopMapper.selectNearShop(longitude, latitude);
        return shops;
    }
}
