package com.example.udd.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

@Getter
@Setter
@AllArgsConstructor
public class UploadPdf {
    private String title;

    private String keywords;

    private MultipartFile[] pdfs;

    private String name;

    private String surname;

    private String genre;

    public UploadPdf(MultipartFile[] files, String title, String keywords) {
    }

    public UploadPdf() {

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

    public MultipartFile[] getFiles() {
        return pdfs;
    }

    public void setFiles(MultipartFile[] files) {
        this.pdfs = files;
    }

    @Override
    public String toString() {
        return "UploadModel{" +
                "title='" + title + '\'' +
                ", files=" + Arrays.toString(pdfs) +
                '}';
    }
}
