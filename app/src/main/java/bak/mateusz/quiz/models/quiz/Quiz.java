
package bak.mateusz.quiz.models.quiz;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import bak.mateusz.quiz.models.Categories;
import bak.mateusz.quiz.models.Category;
import bak.mateusz.quiz.models.MainPhoto;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Quiz extends RealmObject {

    @SerializedName("celebrity")
    @Expose
    public Celebrity celebrity;
    @SerializedName("rates")
    @Expose
    public RealmList<Rates> rates = null;
    @SerializedName("questions")
    @Expose
    public RealmList<Question> questions = null;
    @SerializedName("createdAt")
    @Expose
    public String createdAt;
    @SerializedName("sponsored")
    @Expose
    public Boolean sponsored;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("content")
    @Expose
    public String content;
    @SerializedName("buttonStart")
    @Expose
    public String buttonStart;
    @SerializedName("shareTitle")
    @Expose
    public String shareTitle;
    @SerializedName("categories")
    @Expose
    public RealmList<Categories> categories = null;
    @SerializedName("id")
    @Expose
    @PrimaryKey
    public Long id;
    @SerializedName("scripts")
    @Expose
    public String scripts;
    @SerializedName("mainPhoto")
    @Expose
    public MainPhoto mainPhoto;
    @SerializedName("category")
    @Expose
    public Category category;
    @SerializedName("isBattle")
    @Expose
    public Boolean isBattle;
    @SerializedName("created")
    @Expose
    public Long created;
    @SerializedName("latestResults")
    @Expose
    public RealmList<LatestResults> latestResults = null;
    @SerializedName("avgResult")
    @Expose
    public Float avgResult;
    @SerializedName("resultCount")
    @Expose
    public Long resultCount;
    @SerializedName("cityAvg")
    @Expose
    public Float cityAvg;
    @SerializedName("cityTime")
    @Expose
    public Float cityTime;
    @SerializedName("cityCount")
    @Expose
    public Integer cityCount;
    @SerializedName("userBattleDone")
    @Expose
    public Boolean userBattleDone;
    @SerializedName("sponsoredResults")
    @Expose
    public SponsoredResults sponsoredResults;
    public int correctAnswers = 0;
    public int currentQuestion = 0;

    public Long getId() {
        return id;
    }

    public int getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(int currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

    public Integer getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(Integer correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public int getResultPercentage() {
        Integer correctAnswersNumber = this.correctAnswers;
        Integer questionsNumber = this.questions.size();
        return Math.round((float) correctAnswersNumber / (float) questionsNumber * 100);
    }

    public String getRate() {
        RealmList<Rates> rates = this.getRates();
        int resultPercentage = this.getResultPercentage();
        for (Rates rate : rates) {
            if (resultPercentage >= rate.getFrom() && resultPercentage <= rate.getTo()) {
                return rate.getContent();
            }
        }
        return "";
    }

    public RealmList<Rates> getRates() {
        return rates;
    }

    public RealmList<Question> getQuestions() {
        return questions;
    }

    public String getTitle() {
        return title;
    }
}
