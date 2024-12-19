package com.manonssharedkitchen.cloudhours.model;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Record {
    private static final Pattern p = Pattern.compile("^User Entry: (.+)");
    private String date;
    private String fullName;
    private String credential;
    // While Site is unused, it needs to be present for ICsvBean to work properly
    @SuppressWarnings("unused")
    private String site;
    private String door;

    public Record() {
    }

    // Setters
    public void setDate(String date) {
        this.date = date;
    }

    public void setEvent(String event) {
        Matcher m = p.matcher(event);
        if (m.find()) {
            fullName = m.group(1);
            return;
        }
        fullName = null;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public void setDoor(String door) {
        this.door = door;
    }

    // Getters
    public String getFullName() {
        return fullName;
    }

    public String getDate() {
        return date;
    }

    public String getDoor() {
        return door;
    }

    public String getCredential() {
        return credential;
    }

    public int getRecordId() {
        return Objects.hash(date, fullName, credential, site, door);
    }
}