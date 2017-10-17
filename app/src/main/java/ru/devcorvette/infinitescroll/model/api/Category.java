
package ru.devcorvette.infinitescroll.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("categoryName")
    @Expose
    private String categoryName;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("iconUnchecked")
    @Expose
    private String iconUnchecked;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIconUnchecked() {
        return iconUnchecked;
    }

    public void setIconUnchecked(String iconUnchecked) {
        this.iconUnchecked = iconUnchecked;
    }

}
