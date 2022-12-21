package com.example.cyerbpopMarket.page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@ApiModel(description = "封装分页对象")
public class PageList {
    @ApiModelProperty(value = "当前页")
    private int page;
    @ApiModelProperty(value = "总行数")
    private int totalNumRows;
    @ApiModelProperty(value = "总页数")
    private int pages;
    @ApiModelProperty(value = "数据集合")
    private List list=new ArrayList();

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List getList() {
        if(list==null){
            list=new ArrayList();
        }
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public int getTotalRows() {
        return totalNumRows;
    }

    public void setTotalRows(int totalNumRows) {
        this.totalNumRows = totalNumRows;
    }
}
