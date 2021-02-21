package com.example.udd.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import com.fasterxml.jackson.annotation.JsonFormat;


@Getter
@Setter
@AllArgsConstructor
@Document(indexName = IndexUnit.INDEX_NAME)
public class IndexUnit {
    public static final String INDEX_NAME = "digitallibrary";
    public static final String DATE_PATTERN = "yyyy-MM-dd";



    @Id
    @Field(type = FieldType.Keyword,  store = true)
    private String filename;
    @Field(type = FieldType.Text,  store = true,analyzer = "serbian")
    private String title;
    @Field(type = FieldType.Text,  store = true,analyzer = "serbian")
    private String keywords;
    @Field(type = FieldType.Text,  store = true,analyzer = "serbian")
    private String genre;
    @Field(type = FieldType.Text, store = true,analyzer = "serbian")
    private String name;
    @Field(type = FieldType.Text,  store = true,analyzer = "serbian")
    private String surname;
    @Field(type = FieldType.Text, store = true,analyzer = "serbian")
    private String text;


    public IndexUnit() {

    }
}
