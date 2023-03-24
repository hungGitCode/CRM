package com.cybersoft.crm.model;

public class QualitiesModel {
    private String statusName;
    private float statusCount = 0;
    private float statusPercent = 0;


    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public float getStatusCount() {
        return statusCount;
    }

    public void setStatusCount(float statusCount) {
        this.statusCount = statusCount;
    }

    public float getStatusPercent() {
        return statusPercent;
    }

    public void setStatusPercent(float statusPercent) {
        this.statusPercent = statusPercent;
    }
}
