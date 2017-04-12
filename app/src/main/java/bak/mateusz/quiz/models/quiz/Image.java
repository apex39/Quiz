
package bak.mateusz.quiz.models.quiz;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Image extends RealmObject {

    @SerializedName("author")
    @Expose
    public String author;
    @SerializedName("width")
    @Expose
    public String width;
    @SerializedName("mediaId")
    @Expose
    public String mediaId;
    @SerializedName("source")
    @Expose
    public String source;
    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("height")
    @Expose
    public String height;

}
