package com.eastwind.backend.service;

import com.eastwind.backend.model.ConsumeLog;
import com.eastwind.backend.util.PageRequest;

public interface ConsumeLogService {
    int insertRecord(ConsumeLog consumeLog);

    PageRequest<ConsumeLog> queryRecordsByPage(ConsumeLog consumeLog, PageRequest<ConsumeLog> pageRequest);
}
