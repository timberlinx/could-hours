package com.manonssharedkitchen.cloudhours.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Record {
    private static final Pattern p = Pattern.compile("^User Entry: (.+)");
    private String Date;
    private String Event;
    private String Credential;
    // While Site is unused, it needs to be present for ICsvBean to work properly
    @SuppressWarnings("unused")
    private String Site;
    private String Door;

    public Record() {
    }

    // Setters
    public void setDate(String date) {
        Date = date;
    }

    public void setEvent(String event) {
        Matcher m = p.matcher(event);
        if (m.find()) {
            Event = m.group(1);
            return;
        }
        Event = null;
    }

    public void setCredential(String credential) {
        Credential = credential;
    }

    public void setSite(String site) {
        Site = site;
    }

    public void setDoor(String door) {
        Door = door;
    }

    // Getters
    String getFullName() {
        return Event;
    }

    String getDate() {
        return Date;
    }

    String getDoor() {
        return Door;
    }

    String getCredential() {
        return Credential;
    }
}