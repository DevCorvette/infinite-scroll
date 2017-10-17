
package ru.devcorvette.infinitescroll.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CoverInfo {

    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("video")
    @Expose
    private Object video;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("contactLink")
    @Expose
    private Object contactLink;
    @SerializedName("id")
    @Expose
    private String id;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Object getVideo() {
        return video;
    }

    public void setVideo(Object video) {
        this.video = video;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getContactLink() {
        return contactLink;
    }

    public void setContactLink(Object contactLink) {
        this.contactLink = contactLink;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
