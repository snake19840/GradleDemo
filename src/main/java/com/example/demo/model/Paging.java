package com.example.demo.model;

import javax.persistence.Entity;


public class Paging {
    private int page; //第几页，1表示第二页
    private int size; //每页多少条
    private String sortObj; // 排序对象
private boolean dir; // 是否正向排序,1表示正向,0表示逆向

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getSortObj() {
        return sortObj;
    }

    public void setSortObj(String sortObj) {
        this.sortObj = sortObj;
    }

    public boolean isDir() {
        return dir;
    }

    public void setDir(boolean dir) {
        this.dir = dir;
    }

    public Paging() {
        super();
    }

}
