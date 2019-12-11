package com.eastwind.backend.service.impl;

import com.eastwind.backend.model.Shop;
import com.eastwind.backend.service.ShopService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ShopServiceImplTest extends com.eastwind.backend.EastWindApplicationTests {
    @Autowired
    private ShopService shopService;
    @Test
    public void findNearShops() throws Exception {
        List<Shop> nearShops = shopService.findNearShops(100.00, 100.00);
        System.out.println(nearShops);
    }

}