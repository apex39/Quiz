
package bak.mateusz.quiz.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

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
    public List<Categories> categories = null;
    @SerializedName("id")
    @Expose
    public Integer id;
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
    public List<Tag> tags = null;

}
