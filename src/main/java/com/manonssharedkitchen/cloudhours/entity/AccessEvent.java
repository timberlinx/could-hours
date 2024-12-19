package com.manonssharedkitchen.cloudhours.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;

@Entity
public class AccessEvent {
    public enum Site {
        RESEARCH_8309;
    }

    public enum EventType {
        ENTRY,
        EXIT
    }

    private int accessEventId;
    private LocalDateTime timestamp;
    private Site site;
    private String credential;
    private boolean isManual;
    private String doorId;
    private EventType eventType;
    private String tenantId;
    private String brivoUser;

    public int getAccessEventId() {
        return accessEventId;
    }

    public void setAccessEventId(int accessEventId) {
        this.accessEventId = accessEventId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }

    public boolean isManual() {
        return isManual;
    }

    public void setManual(boolean isManual) {
        this.isManual = isManual;
    }

    public String getDoorId() {
        return doorId;
    }

    public void setDoorId(String doorId) {
        this.doorId = doorId;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getBrivoUser() {
        return brivoUser;
    }

    public void setBrivoUser(String brivoUser) {
        this.brivoUser = brivoUser;
    }
}
