package cn.nihility.spring.mybatis.entity;

import java.util.Date;

/**
 * Spring Mybatis Bank Entity to test Transaction
 * @author muscari
 * @date 2019-06-01 13:19
 */
public class Bank {
    private int id;
    private String name;
    private Date dealDate;
    private int amt;

    public Bank() { }

    public Bank(int id, String name, Date dealDate, int amt) {
        this.id = id;
        this.name = name;
        this.dealDate = dealDate;
        this.amt = amt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDealDate(Date dealDate) {
        this.dealDate = dealDate;
    }

    public void setAmt(int amt) {
        this.amt = amt;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getDealDate() {
        return dealDate;
    }

    public int getAmt() {
        return amt;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dealDate=" + dealDate +
                ", amt=" + amt +
                '}';
    }
}
