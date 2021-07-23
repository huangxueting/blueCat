package com.blueCat.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;

import java.util.Date;

@ContentRowHeight(20)
public class BlueCatExcelVo {

    /**
     * 标题
     */
    @ColumnWidth(20)
    @ExcelProperty(value = "标题", index = 0)
    private String title;

    /**
     * 创建时间
     */
    @ColumnWidth(20)
    @ExcelProperty(value = "创建时间", index = 1)
    private Date createDate;

    /**
     * 删除标志(默认为0未删除，1已删除)
     */
    @ColumnWidth(20)
    @ExcelProperty(value = "删除标志", index = 2)
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
}
