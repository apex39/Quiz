
package bak.mateusz.quiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Tag extends RealmObject {

    @SerializedName("uid")
    @Expose
    public String uid;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("type")
    @Expose
    public String type;

}
