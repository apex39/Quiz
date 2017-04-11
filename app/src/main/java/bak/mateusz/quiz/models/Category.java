
package bak.mateusz.quiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Category extends RealmObject {

    @SerializedName("id")
    @Expose
    public long id;
    @SerializedName("name")
    @Expose
    public String name;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
