package entity;

import java.sql.Timestamp;

/**
 * @author Administrator
 */
public class secKill {
    private Long secKillId;
    private String name;
    private int number;
    private Timestamp createTime;
    private Timestamp startTime;
    private Timestamp endTime;

    public void setSecKillId(Long secKillId) {
        this.secKillId = secKillId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Timestamp endTime) {
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

    public Timestamp getCreateTime() {
        return createTime;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public Timestamp getEndTime() {
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
