package com.example.udd.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data
@AllArgsConstructor
@Setter
@Getter
public class ResultData {

    private String title;
    private String keywords;
    private String location;
    private String highlight;
    private String name;
    private String surname;
    private String genre;

    public ResultData() {
        super();
    }

    public ResultData(String title, String keywords, String location, String highlight) {
        super();
        this.title = title;
        this.keywords = keywords;
        this.location = location;
        this.highlight = highlight;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getHighlight() {
        return highlight;
    }

    public void setHighlight(String highlight) {
        this.highlight = highlight;
    }
}
