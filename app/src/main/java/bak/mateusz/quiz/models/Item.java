
package bak.mateusz.quiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Item extends RealmObject {
    @SerializedName("buttonStart")
    @Expose
    public String buttonStart;
    @SerializedName("shareTitle")
    @Expose
    public String shareTitle;
    @SerializedName("questions")
    @Expose
    public Integer questions;
    @SerializedName("createdAt")
    @Expose
    public String createdAt;
    @SerializedName("sponsored")
    @Expose
    public Boolean sponsored;
    @SerializedName("categories")
    @Expose
    public RealmList<Categories> categories = null;
    @SerializedName("id")
    @Expose
    public long id;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("content")
    @Expose
    public String content;
    @SerializedName("mainPhoto")
    @Expose
    public MainPhoto mainPhoto;
    @SerializedName("category")
    @Expose
    public Category category;
    @SerializedName("tags")
    @Expose
    public RealmList<Tag> tags = null;

    public String getButtonStart() {
        return buttonStart;
    }

    public String getShareTitle() {
        return shareTitle;
    }

    public Integer getQuestions() {
        return questions;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public Boolean getSponsored() {
        return sponsored;
    }

    public RealmList<Categories> getCategories() {
        return categories;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getContent() {
        return content;
    }

    public MainPhoto getMainPhoto() {
        return mainPhoto;
    }

    public Category getCategory() {
        return category;
    }

    public RealmList<Tag> getTags() {
        return tags;
    }

}
