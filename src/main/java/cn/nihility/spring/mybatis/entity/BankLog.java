package cn.nihility.spring.mybatis.entity;

import java.util.Date;

/**
 * Spring Mybatis Bank_log entity
 * @author muscari
 * @date 2019-06-01 13:20
 */
public class BankLog {
    private int id;
    private String name;
    private String status;
    private Date logTime;

    public BankLog() { }

    public BankLog(int id, String name, String status, Date logTime) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.logTime = logTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getLogTime() {
        return logTime;
    }

    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }

    @Override
    public String toString() {
        return "BankLog{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", log_time=" + logTime +
                '}';
    }
}
