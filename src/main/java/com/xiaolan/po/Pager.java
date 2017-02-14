package com.xiaolan.po;

import com.xiaolan.staticconfig.StaticConfig;

/**
 * Created by itsxun on 2016/8/9.
 */
public class Pager {
    private Integer totalPages;//一共多少页

    private Integer curPage;//当前页数

    private Integer rows;//每页显示的数量

    private Integer header;//当前页的开始行数

    private Integer footer;//当前页的结束行数

    private Integer totalRows;//一共多少条结果

    public Pager() {
    }

    /**
     * 获取总页数
     *
     * @return
     */
    public Integer getTotalPages() {
        return totalPages;
    }

    /**
     * 设置总页数
     *
     * @param totalRows 查询结果的总列数
     * @param rows      每页显示的列数
     */
    public void setTotalPages(Integer totalRows, Integer rows) {
        this.totalPages = (totalRows / rows) + (totalRows % rows == 0 ? 0 : 1);
    }

    public void setTotalPages(Integer totalRows) {
        this.totalPages = totalRows;
    }

    /**
     * 获取当前页
     *
     * @return
     */
    public Integer getCurPage() {
        return curPage;
    }

    /**
     * 设置当前页
     *
     * @param curPage
     */
    public void setCurPage(Integer curPage) {
        this.curPage = curPage;
    }

    /**
     * 获取每页要显示的列数
     *
     * @return
     */
    public Integer getRows() {
        return this.getRows();
    }

    /**
     * 设置每一页的列数
     *
     * @param rows
     */
    public void setRows(int rows) {
        this.rows = StaticConfig.page_size;
    }

    /**
     * 获取每一页第一列的索引
     *
     * @return
     */
    public Integer getHeader() {
        return header;
    }

    /**
     * 设置页面的第一列数据索引
     *
     * @param curPage 当前页数
     * @param rows    每一页显示的行数
     */
    public void setHeader(Integer curPage, Integer rows) {
        this.header = (curPage - 1) * rows + 1;
    }

    public void setHeader(Integer header) {
        this.header = header;
    }

    /**
     * 获取此页面最后一列的索引
     *
     * @return
     */
    public Integer getFooter() {
        return footer;
    }

    /**
     * 设置页面的最后一行数据索引
     *
     * @param header 页面的第一行索引
     */
    public void setFooter(Integer header) {
        this.footer = header + rows;
    }

//    public void setFooter(Integer footer) {
//        this.footer = footer;
//    }

    /**
     * 返回查询结果总共的列数
     *
     * @return
     */
    public Integer getTotalRows() {
        return totalRows;
    }

    /**
     * 设置查询结果总共的列数
     *
     * @param totalRows
     */
    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }
}
