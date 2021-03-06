package com.eastwind.backend.model.front;

import com.eastwind.backend.model.Shop;

import java.util.List;

/**
 * 供前台页面使用的模型
 */
public class ShopInfo extends Shop {

    private String openId;

    private Integer userId;

    private List<String> pics;

    public List<String> getPics() {
        return pics;
    }

    public void setPics(List<String> pics) {
        this.pics = pics;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }



    @Override
    public String toString() {
        return "ShopInfo{" +
                "openId='" + openId + '\'' +
                '}';
    }
}
