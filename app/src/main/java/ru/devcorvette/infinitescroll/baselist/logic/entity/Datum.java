
package ru.devcorvette.infinitescroll.baselist.logic.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("topRated")
    @Expose
    private boolean topRated;
    @SerializedName("workingHours")
    @Expose
    private Object workingHours;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("shortDescription")
    @Expose
    private Object shortDescription;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("isRecommended")
    @Expose
    private boolean isRecommended;
    @SerializedName("organization")
    @Expose
    private Organization organization;
    @SerializedName("coverInfo")
    @Expose
    private CoverInfo[] coverInfo = null;
    @SerializedName("contact_info")
    @Expose
    private ContactInfo[] contactInfo = null;
    @SerializedName("geo_info")
    @Expose
    private GeoInfo[] geoInfo = null;
    @SerializedName("vkPostId")
    @Expose
    private Object vkPostId;
    @SerializedName("fbPostId")
    @Expose
    private Object fbPostId;
    @SerializedName("totalLikes")
    @Expose
    private Object totalLikes;
    @SerializedName("userLikes")
    @Expose
    private boolean userLikes;
    @SerializedName("reposts")
    @Expose
    private Object[] reposts = null;
    @SerializedName("birthdayDiscount")
    @Expose
    private boolean birthdayDiscount;
    @SerializedName("certificates")
    @Expose
    private boolean certificates;
    @SerializedName("giftCertificates")
    @Expose
    private boolean giftCertificates;
    @SerializedName("forMan")
    @Expose
    private boolean forMan;
    @SerializedName("forWoman")
    @Expose
    private boolean forWoman;
    @SerializedName("forBoy")
    @Expose
    private boolean forBoy;
    @SerializedName("forGirl")
    @Expose
    private boolean forGirl;

    public boolean isTopRated() {
        return topRated;
    }

    public void setTopRated(boolean topRated) {
        this.topRated = topRated;
    }

    public Object getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(Object workingHours) {
        this.workingHours = workingHours;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Object getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(Object shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isIsRecommended() {
        return isRecommended;
    }

    public void setIsRecommended(boolean isRecommended) {
        this.isRecommended = isRecommended;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public CoverInfo[] getCoverInfo() {
        return coverInfo;
    }

    public void setCoverInfo(CoverInfo[] coverInfo) {
        this.coverInfo = coverInfo;
    }

    public ContactInfo[] getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo[] contactInfo) {
        this.contactInfo = contactInfo;
    }

    public GeoInfo[] getGeoInfo() {
        return geoInfo;
    }

    public void setGeoInfo(GeoInfo[] geoInfo) {
        this.geoInfo = geoInfo;
    }

    public Object getVkPostId() {
        return vkPostId;
    }

    public void setVkPostId(Object vkPostId) {
        this.vkPostId = vkPostId;
    }

    public Object getFbPostId() {
        return fbPostId;
    }

    public void setFbPostId(Object fbPostId) {
        this.fbPostId = fbPostId;
    }

    public Object getTotalLikes() {
        return totalLikes;
    }

    public void setTotalLikes(Object totalLikes) {
        this.totalLikes = totalLikes;
    }

    public boolean isUserLikes() {
        return userLikes;
    }

    public void setUserLikes(boolean userLikes) {
        this.userLikes = userLikes;
    }

    public Object[] getReposts() {
        return reposts;
    }

    public void setReposts(Object[] reposts) {
        this.reposts = reposts;
    }

    public boolean isBirthdayDiscount() {
        return birthdayDiscount;
    }

    public void setBirthdayDiscount(boolean birthdayDiscount) {
        this.birthdayDiscount = birthdayDiscount;
    }

    public boolean isCertificates() {
        return certificates;
    }

    public void setCertificates(boolean certificates) {
        this.certificates = certificates;
    }

    public boolean isGiftCertificates() {
        return giftCertificates;
    }

    public void setGiftCertificates(boolean giftCertificates) {
        this.giftCertificates = giftCertificates;
    }

    public boolean isForMan() {
        return forMan;
    }

    public void setForMan(boolean forMan) {
        this.forMan = forMan;
    }

    public boolean isForWoman() {
        return forWoman;
    }

    public void setForWoman(boolean forWoman) {
        this.forWoman = forWoman;
    }

    public boolean isForBoy() {
        return forBoy;
    }

    public void setForBoy(boolean forBoy) {
        this.forBoy = forBoy;
    }

    public boolean isForGirl() {
        return forGirl;
    }

    public void setForGirl(boolean forGirl) {
        this.forGirl = forGirl;
    }
}
