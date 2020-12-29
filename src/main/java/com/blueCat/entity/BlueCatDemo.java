package com.blueCat.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @author: huangxueting
 * date: 2020/12/25 12:53
 * @description:
 **/
public class BlueCatDemo implements Serializable {

    private static final long serialVersionUID = 4382117146121223331L;

    /**
     * 标题
     */
    private String title;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 删除标志(默认为0未删除，1已删除)
     */
    private Integer delFlag;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlueCatDemo that = (BlueCatDemo) o;
        return Objects.equals(title, that.title) && Objects.equals(createDate, that.createDate) && Objects.equals(delFlag, that.delFlag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, createDate, delFlag);
    }

    @Override
    public String toString() {
        return "BlueCatDemo{" +
                "title='" + title + '\'' +
                ", createDate=" + createDate +
                ", delFlag=" + delFlag +
                '}';
    }
}
