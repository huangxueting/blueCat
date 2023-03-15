package com.blueCat.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.io.Serializable;

public class CompanyHasImgModel implements Serializable {

    public CompanyHasImgModel(String name, String companyLogo) {
        this.name = name;
        this.companyLogo = companyLogo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(String companyLogo) {
        this.companyLogo = companyLogo;
    }

    /**
     * 公司名称
     */
    @Excel(name = "公司名称", height = 20, width = 30, isImportField = "true_st")
    private String name;

    @Excel(name = "公司LOGO", type = 2 ,width = 40 , height = 20,imageType = 1)
    private String companyLogo;
}
