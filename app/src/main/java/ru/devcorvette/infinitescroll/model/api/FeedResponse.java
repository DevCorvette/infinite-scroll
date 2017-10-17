
package ru.devcorvette.infinitescroll.model.api;

import java.util.List;
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
    private List<Datum> data = null;
    @SerializedName("categories")
    @Expose
    private List<Category> categories = null;

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

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

}
