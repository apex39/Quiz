
package bak.mateusz.quiz.models.quiz;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class LatestResults extends RealmObject {

    @SerializedName("city")
    @Expose
    public Long city;
    @SerializedName("end_date")
    @Expose
    public String endDate;
    @SerializedName("result")
    @Expose
    public Float result;
    @SerializedName("resolveTime")
    @Expose
    public Long resolveTime;

}
