package com.eastwind.backend.mapper;

import com.eastwind.backend.model.Shop;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("shopMapper")
public interface ShopMapper {
    int deleteByPrimaryKey(Integer shopId);

    int insert(Shop record);

    int insertSelective(Shop record);

    Shop selectByPrimaryKey(Integer shopId);

    int updateByPrimaryKeySelective(Shop record);

    int updateByPrimaryKey(Shop record);

    List<Shop> selectAllShopsOfUser(Integer userId);

    List<Shop> selectNearShop(@Param("longitude") Double longitude, @Param("latitude") Double latitude);
}