
package bak.mateusz.quiz.models.quiz;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class SponsoredResults extends RealmObject {

    @SerializedName("imageAuthor")
    @Expose
    public String imageAuthor;
    @SerializedName("imageHeight")
    @Expose
    public String imageHeight;
    @SerializedName("imageUrl")
    @Expose
    public String imageUrl;
    @SerializedName("imageWidth")
    @Expose
    public String imageWidth;
    @SerializedName("textColor")
    @Expose
    public String textColor;
    @SerializedName("content")
    @Expose
    public String content;
    @SerializedName("mainColor")
    @Expose
    public String mainColor;
    @SerializedName("imageSource")
    @Expose
    public String imageSource;

}
