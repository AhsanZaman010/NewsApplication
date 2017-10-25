
package com.accolite.newsapplication.model.news;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Url {

    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("matchLevel")
    @Expose
    private String matchLevel;
    @SerializedName("fullyHighlighted")
    @Expose
    private boolean fullyHighlighted;
    @SerializedName("matchedWords")
    @Expose
    private List<String> matchedWords = null;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMatchLevel() {
        return matchLevel;
    }

    public void setMatchLevel(String matchLevel) {
        this.matchLevel = matchLevel;
    }

    public boolean isFullyHighlighted() {
        return fullyHighlighted;
    }

    public void setFullyHighlighted(boolean fullyHighlighted) {
        this.fullyHighlighted = fullyHighlighted;
    }

    public List<String> getMatchedWords() {
        return matchedWords;
    }

    public void setMatchedWords(List<String> matchedWords) {
        this.matchedWords = matchedWords;
    }

}
