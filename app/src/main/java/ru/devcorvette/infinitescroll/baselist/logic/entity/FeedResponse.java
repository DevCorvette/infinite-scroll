
package ru.devcorvette.infinitescroll.baselist.logic.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FeedResponse {

    @SerializedName("success")
    @Expose
    private boolean success;
    @SerializedName("info")
    @Expose
    private String info;
    @SerializedName("data")
    @Expose
    private Datum [] data;
    @SerializedName("categories")
    @Expose
    private Category[] categories;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Datum[] getData() {
        return data;
    }

    public void setData(Datum[] data) {
        this.data = data;
    }

    public Category[] getCategories() {
        return categories;
    }

    public void setCategories(Category[] categories) {
        this.categories = categories;
    }

}
