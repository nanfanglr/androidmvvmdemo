package com.rui.retrofit2.basemodel;

/**
 * Created by rui on 2018/3/9.
 */
public class BaseResultModel extends BaseModel {


    /**
     * url : null
     * pageCount : 0
     * relative_url : null
     * success : true
     * msg : null
     * rows : null
     * total : 0
     * error : 0
     * page : 0
     */
    public static int PAGE_LIMIT = 10;
    private String url;
    private int pageCount;
    private String relative_url;
    private boolean success;
    private String msg;
    private String rows;
    private int total;
    private int error;
    private int error_code;
    private int page;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getUrl() {
        return url == null ? "" : url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getRelative_url() {
        return relative_url == null ? "" : relative_url;
    }

    public void setRelative_url(String relative_url) {
        this.relative_url = relative_url;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg == null ? "" : msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getRows() {
        return rows == null ? "" : rows;
    }

    public void setRows(String rows) {
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSumPage() {
        return getSumPage(PAGE_LIMIT);
    }

    public int getSumPage(int limitPage) {
        return total % limitPage == 0 ? total / limitPage : total / limitPage + 1;
    }

}
