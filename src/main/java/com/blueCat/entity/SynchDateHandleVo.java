package com.blueCat.entity;

/**
 * 工资发放情况对象 REP_ENG_SALARY_PAYMENT
 *
 * @author huangxueting
 * @date 2024-01-11
 */
public class SynchDateHandleVo {
    /** 月份的第一天 */
    private String firstDate;

    /** 月份最后一天 */
    private String lastDate;

    /** 年 */
    private Integer year;

    /** 月 */
    private Integer month;

    public String getFirstDate() {
        return firstDate;
    }

    public void setFirstDate(String firstDate) {
        this.firstDate = firstDate;
    }

    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    @Override
    public String toString() {
        return "SynchDateHandleVo{" +
                "firstDate='" + firstDate + '\'' +
                ", lastDate='" + lastDate + '\'' +
                ", year=" + year +
                ", month=" + month +
                '}';
    }
}
