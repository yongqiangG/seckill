package com.johnny.entity;

import java.util.Date;

/**
 * @author Administrator
 */
public class SuccessKilled {
    private Long secKillId;
    private Long userPhone;
    private short state;
    private Date createTime;

    @Override
    public String toString() {
        return "successKilled{" +
                "secKillId=" + secKillId +
                ", userPhone=" + userPhone +
                ", state=" + state +
                ", createTime=" + createTime +
                '}';
    }

    public Long getSecKillId() {
        return secKillId;
    }

    public void setSecKillId(Long secKillId) {
        this.secKillId = secKillId;
    }

    public Long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(Long userPhone) {
        this.userPhone = userPhone;
    }

    public short getState() {
        return state;
    }

    public void setState(short state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
