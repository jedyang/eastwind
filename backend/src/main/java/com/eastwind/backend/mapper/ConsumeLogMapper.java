package com.eastwind.backend.mapper;

import com.eastwind.backend.model.ConsumeLog;
import com.eastwind.backend.util.PageRequest;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("consumeLogMapper")
public interface ConsumeLogMapper {
    int deleteByPrimaryKey(Integer logId);

    int insert(ConsumeLog record);

    int insertSelective(ConsumeLog record);

    ConsumeLog selectByPrimaryKey(Integer logId);

    List<ConsumeLog> selectBySelective(ConsumeLog record, PageRequest<ConsumeLog> pageRequest);

    int updateByPrimaryKeySelective(ConsumeLog record);

    int updateByPrimaryKey(ConsumeLog record);

}