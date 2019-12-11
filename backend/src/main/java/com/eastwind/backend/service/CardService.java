package com.eastwind.backend.service;

import com.eastwind.backend.model.HyCards;
import com.eastwind.backend.util.PageRequest;

@Deprecated
public interface CardService {
    int insertRecord(HyCards hyCards);

    PageRequest<HyCards> queryRecordsByPage(HyCards hyCards, PageRequest<HyCards> pageRequest);
}
