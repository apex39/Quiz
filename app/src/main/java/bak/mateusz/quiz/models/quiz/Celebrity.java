
package bak.mateusz.quiz.models.quiz;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Celebrity extends RealmObject {

    @SerializedName("result")
    @Expose
    public String result;
    @SerializedName("imageAuthor")
    @Expose
    public String imageAuthor;
    @SerializedName("imageHeight")
    @Expose
    public String imageHeight;
    @SerializedName("imageUrl")
    @Expose
    public String imageUrl;
    @SerializedName("show")
    @Expose
    public Long show;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("imageTitle")
    @Expose
    public String imageTitle;
    @SerializedName("imageWidth")
    @Expose
    public String imageWidth;
    @SerializedName("content")
    @Expose
    public String content;
    @SerializedName("imageSource")
    @Expose
    public String imageSource;

}
