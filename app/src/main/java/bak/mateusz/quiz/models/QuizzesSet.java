
package bak.mateusz.quiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QuizzesSet {

    @SerializedName("count")
    @Expose
    public Integer count;
    @SerializedName("items")
    @Expose
    public List<Item> items;

    public Integer getCount() {
        return count;
    }

    public List<Item> getItems() {
        return items;
    }

}
