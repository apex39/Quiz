
package bak.mateusz.quiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;

public class QuizzesSet {

    @SerializedName("count")
    @Expose
    public Integer count;
    @SerializedName("items")
    @Expose
    public RealmList<Item> items;

    public Integer getCount() {
        return count;
    }

    public RealmList<Item> getItems() {
        return items;
    }

}
