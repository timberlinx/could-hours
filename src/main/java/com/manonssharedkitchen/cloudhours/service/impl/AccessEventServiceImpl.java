package com.manonssharedkitchen.cloudhours.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manonssharedkitchen.cloudhours.model.Record;
import com.manonssharedkitchen.cloudhours.entity.AccessEvent;
import com.manonssharedkitchen.cloudhours.entity.AccessEvent.EventType;
import com.manonssharedkitchen.cloudhours.entity.AccessEvent.Site;
import com.manonssharedkitchen.cloudhours.repository.AccessEventRepository;
import com.manonssharedkitchen.cloudhours.service.AccessEventService;

@Service
public class AccessEventServiceImpl implements AccessEventService {
    private static final Pattern DOOR_ID = Pattern.compile("[0-9]+");
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("M/d/u h:m:s a zzz");

    @Autowired
    private AccessEventRepository accessEventRepository;

    @Override
    public List<AccessEvent> getAllAccessEvents() {
        return accessEventRepository.findAll();
    }

    @Override
    public AccessEvent getAccessEventById(int id) {
        return accessEventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Access event not found"));
    }

    @Override
    public AccessEvent saveAccessEvent(Record record) {
        AccessEvent accessEvent = new AccessEvent();
        accessEvent.setAccessEventId(record.getRecordId());
        accessEvent.setTimestamp(LocalDateTime.parse(record.getDate(), FORMATTER).plusYears(2000));
        accessEvent.setBrivoUser(record.getFullName());
        accessEvent.setSite(Site.RESEARCH_8309);
        accessEvent.setCredential(record.getCredential());

        String door = record.getDoor();
        if (door.contains("Entry")) {
            accessEvent.setEventType(EventType.ENTRY);
        } else if (door.contains("Exit")) {
            accessEvent.setEventType(EventType.EXIT);
        } else {
            // Toggle these two lines when backfilling due to former data corruption
            // this.door = Door.EXIT;
            throw new IllegalArgumentException(String.format("door type [%s] unrecognized", door));
        }

        Matcher matcher = DOOR_ID.matcher(door);
        if (matcher.find()) {
            accessEvent.setDoorId(matcher.group(0));
        } else {
            throw new IllegalArgumentException(String.format("Door ID [%s] unrecognized", door));
        }

        return accessEventRepository.save(accessEvent);
    }

    @Override
    public void deleteAccessEvent(int id) {
        accessEventRepository.deleteById(id);
    }
}
