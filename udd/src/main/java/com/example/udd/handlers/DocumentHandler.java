package com.example.udd.handlers;

import com.example.udd.Model.IndexUnit;

import java.io.File;

public abstract class DocumentHandler {
    /**
     * Od prosledjene datoteke se konstruise Lucene Document
     *
     * @param file
     *            datoteka u kojoj se nalaze informacije
     * @return Lucene Document
     */
    public abstract IndexUnit getIndexUnit(File file);
    public abstract String getText(File file);

}
