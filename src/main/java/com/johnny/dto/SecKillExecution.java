package com.johnny.dto;

import com.johnny.entity.SuccessKilled;
import com.johnny.enums.SecKillStateEnum;

import java.util.List;

/*封装秒杀执行结果*/
public class SecKillExecution {
    private long secKillId;
    private int state;
    private String stateInfo;
    private List<SuccessKilled> successKilled;

    public long getSecKillId() {
        return secKillId;
    }

    public void setSecKillId(long secKillId) {
        this.secKillId = secKillId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public List<SuccessKilled> getSuccessKilled() {
        return successKilled;
    }

    public void setSuccessKilled(List<SuccessKilled> successKilled) {
        this.successKilled = successKilled;
    }

    public SecKillExecution(long secKillId, SecKillStateEnum stateEnum, List<SuccessKilled> successKilled) {
        this.secKillId = secKillId;
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.successKilled = successKilled;
    }
    public SecKillExecution(long secKillId,SecKillStateEnum stateEnum){
        this.secKillId = secKillId;
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    @Override
    public String toString() {
        return "SecKillExecution{" +
                "secKillId=" + secKillId +
                ", state=" + state +
                ", stateInfo='" + stateInfo + '\'' +
                ", successKilled=" + successKilled +
                '}';
    }
}
