package com.xiuluo.model.aboutUs;

import java.math.BigDecimal;
import java.util.Date;

public class WorkerGps {
    private Integer gpsid;

    private Integer workerid;

    private BigDecimal longitude;

    private BigDecimal latitude;

    private Date addtime;

    public Integer getGpsid() {
        return gpsid;
    }

    public void setGpsid(Integer gpsid) {
        this.gpsid = gpsid;
    }

    public Integer getWorkerid() {
        return workerid;
    }

    public void setWorkerid(Integer workerid) {
        this.workerid = workerid;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }
}