
package bak.mateusz.quiz.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuizzesSet {

    @SerializedName("count")
    @Expose
    public Integer count;
    @SerializedName("items")
    @Expose
    public List<Item> items = null;

}
