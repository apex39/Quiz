
package bak.mateusz.quiz.models.quiz;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Questions extends RealmObject {

    @SerializedName("image")
    @Expose
    public Image image;
    @SerializedName("answers")
    @Expose
    public RealmList<Answer> answers = null;
    @SerializedName("text")
    @Expose
    public String text;
    @SerializedName("answer")
    @Expose
    public String answer;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("order")
    @Expose
    public Long order;

}
