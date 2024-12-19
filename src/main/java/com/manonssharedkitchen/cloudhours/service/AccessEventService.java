package com.manonssharedkitchen.cloudhours.service;

import java.util.List;

import com.manonssharedkitchen.cloudhours.model.Record;
import com.manonssharedkitchen.cloudhours.entity.AccessEvent;

public interface AccessEventService {
    List<AccessEvent> getAllAccessEvents();

    AccessEvent getAccessEventById(int id);

    AccessEvent saveAccessEvent(Record record);

    void deleteAccessEvent(int id);
}
