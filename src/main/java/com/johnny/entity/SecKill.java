package com.johnny.entity;

import java.util.Date;

/**
 * @author Administrator
 */
public class SecKill {
    private Long secKillId;
    private String name;
    private int number;
    private Date createTime;
    private Date startTime;
    private Date endTime;

    public void setSecKillId(Long secKillId) {
        this.secKillId = secKillId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Long getSecKillId() {
        return secKillId;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    @Override
    public String toString() {
        return "seckill{" +
                "secKillId=" + secKillId +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", createTime=" + createTime +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
