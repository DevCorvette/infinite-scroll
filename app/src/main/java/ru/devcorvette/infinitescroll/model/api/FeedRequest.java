package ru.devcorvette.infinitescroll.model.api;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FeedRequest {

    @SerializedName("cityId")
    @Expose
    private Object cityId;
    @SerializedName("nearToLocation")
    @Expose
    private Object nearToLocation;
    @SerializedName("categories")
    @Expose
    private List<Object> categories = null;
    @SerializedName("userId")
    @Expose
    private Object userId;
    @SerializedName("skip")
    @Expose
    private int skip;
    @SerializedName("take")
    @Expose
    private int take;

    public FeedRequest(int skip, int take) {
        this.skip = skip;
        this.take = take;
    }

    public Object getCityId() {
        return cityId;
    }

    public void setCityId(Object cityId) {
        this.cityId = cityId;
    }

    public Object getNearToLocation() {
        return nearToLocation;
    }

    public void setNearToLocation(Object nearToLocation) {
        this.nearToLocation = nearToLocation;
    }

    public List<Object> getCategories() {
        return categories;
    }

    public void setCategories(List<Object> categories) {
        this.categories = categories;
    }

    public Object getUserId() {
        return userId;
    }

    public void setUserId(Object userId) {
        this.userId = userId;
    }

    public int getSkip() {
        return skip;
    }

    public void setSkip(int skip) {
        this.skip = skip;
    }

    public int getTake() {
        return take;
    }

    public void setTake(int take) {
        this.take = take;
    }

}