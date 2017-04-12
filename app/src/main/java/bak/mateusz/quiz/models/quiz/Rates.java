
package bak.mateusz.quiz.models.quiz;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Rates extends RealmObject {

    @SerializedName("from")
    @Expose
    public Long from;
    @SerializedName("to")
    @Expose
    public Long to;
    @SerializedName("content")
    @Expose
    public String content;

    public Long getFrom() {
        return from;
    }

    public Long getTo() {
        return to;
    }

    public String getContent() {
        return content;
    }
}
