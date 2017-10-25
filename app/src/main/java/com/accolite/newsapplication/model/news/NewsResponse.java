
package com.accolite.newsapplication.model.news;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewsResponse {

    @SerializedName("hits")
    @Expose
    private List<Hit> hits = null;
    @SerializedName("nbHits")
    @Expose
    private long nbHits;
    @SerializedName("page")
    @Expose
    private long page;
    @SerializedName("nbPages")
    @Expose
    private long nbPages;
    @SerializedName("hitsPerPage")
    @Expose
    private long hitsPerPage;
    @SerializedName("processingTimeMS")
    @Expose
    private long processingTimeMS;
    @SerializedName("exhaustiveNbHits")
    @Expose
    private boolean exhaustiveNbHits;
    @SerializedName("query")
    @Expose
    private String query;
    @SerializedName("params")
    @Expose
    private String params;

    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<Hit> getHits() {
        return hits;
    }

    public void setHits(List<Hit> hits) {
        this.hits = hits;
    }

    public long getNbHits() {
        return nbHits;
    }

    public void setNbHits(long nbHits) {
        this.nbHits = nbHits;
    }

    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = page;
    }

    public long getNbPages() {
        return nbPages;
    }

    public void setNbPages(long nbPages) {
        this.nbPages = nbPages;
    }

    public long getHitsPerPage() {
        return hitsPerPage;
    }

    public void setHitsPerPage(long hitsPerPage) {
        this.hitsPerPage = hitsPerPage;
    }

    public long getProcessingTimeMS() {
        return processingTimeMS;
    }

    public void setProcessingTimeMS(long processingTimeMS) {
        this.processingTimeMS = processingTimeMS;
    }

    public boolean isExhaustiveNbHits() {
        return exhaustiveNbHits;
    }

    public void setExhaustiveNbHits(boolean exhaustiveNbHits) {
        this.exhaustiveNbHits = exhaustiveNbHits;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

}
