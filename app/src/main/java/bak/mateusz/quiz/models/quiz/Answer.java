
package bak.mateusz.quiz.models.quiz;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Answer extends RealmObject {

    @SerializedName("image")
    @Expose
    public Image image;
    @SerializedName("order")
    @Expose
    public Long order;
    @SerializedName("text")
    @Expose
    public String text;
    @SerializedName("isCorrect")
    @Expose
    public Integer isCorrect;

    public Image getImage() {
        return image;
    }

    public Long getOrder() {
        return order;
    }

    public String getText() {
        return text;
    }

    public Boolean getIsCorrect() {
        return isCorrect != null && isCorrect == 1;
    }

}
