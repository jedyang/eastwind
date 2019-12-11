package com.eastwind.backend.service.impl;

import com.eastwind.backend.mapper.HyCardsMapper;
import com.eastwind.backend.model.HyCards;
import com.eastwind.backend.service.CardService;
import com.eastwind.backend.util.PageRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Deprecated
@Service("cardServiceImpl")
public class CardServiceImpl implements CardService {

    @Autowired
    private HyCardsMapper hyCardsMapper;

    @Override
    public int insertRecord(HyCards hyCards) {
        int i = hyCardsMapper.insertSelective(hyCards);
        return i;
    }

    @Override
    public PageRequest<HyCards> queryRecordsByPage(HyCards hyCards, PageRequest<HyCards> pageRequest) {

        PageRequest<HyCards> data = pageRequest.data(hyCardsMapper.selectListByPage(hyCards, pageRequest));

        return data;
    }
}
