package com.example.udd.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SearchModel {

    private String title;
    private  String keywords;
    private String text;
    private String name;
    private String surname;
    private String genre;
    private String operation;

}
