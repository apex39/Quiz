
package bak.mateusz.quiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class MainPhoto extends RealmObject {

    @SerializedName("author")
    @Expose
    public String author;
    @SerializedName("width")
    @Expose
    public Integer width;
    @SerializedName("source")
    @Expose
    public String source;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("height")
    @Expose
    public Integer height;

}
