
package bak.mateusz.quiz.models.quiz;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Question extends RealmObject {

    @SerializedName("image")
    @Expose
    public Image image;
    @SerializedName("answers")
    @Expose
    public RealmList<Answer> answers;
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
    public Integer order;

    public RealmList<Answer> getAnswers() {
        return answers;
    }

    public String getText() {
        return text;
    }

    public String getAnswer() {
        return answer;
    }

    public Integer getOrder() {
        return order;
    }
}
