package com.eastwind.backend.mapper;

import com.eastwind.backend.model.HyCards;
import com.eastwind.backend.util.PageRequest;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("hyCardsMapper")
public interface HyCardsMapper {
    int deleteByPrimaryKey(Long cardId);

    int insert(HyCards record);

    int insertSelective(HyCards record);

    HyCards selectByPrimaryKey(Long cardId);

    int updateByPrimaryKeySelective(HyCards record);

    int updateByPrimaryKey(HyCards record);

    List<HyCards> selectListByPage(HyCards record, PageRequest<HyCards> pageRequest);
}