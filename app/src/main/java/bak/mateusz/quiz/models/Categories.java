
package bak.mateusz.quiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Categories extends RealmObject {

    @SerializedName("uid")
    @Expose
    public Long uid;
    @SerializedName("secondaryCid")
    @Expose
    public String secondaryCid;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("type")
    @Expose
    public String type;

}
