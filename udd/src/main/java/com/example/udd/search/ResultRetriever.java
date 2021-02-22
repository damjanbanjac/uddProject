package com.example.udd.search;

import com.example.udd.Model.IndexUnit;
import com.example.udd.Model.RequiredHighlight;
import com.example.udd.Model.ResultData;
import com.example.udd.handlers.DocumentHandler;
import com.example.udd.handlers.PDFHandler;
import com.example.udd.repository.BookIndexRepository;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.document.SearchDocument;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.core.query.*;
//import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;


import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResultRetriever  {

    @Autowired
    private BookIndexRepository repository;


   // private ElasticsearchTemplate template;

    public ResultRetriever(){
    }

    public List<ResultData> getResults(org.elasticsearch.index.query.QueryBuilder query,
                                       List<RequiredHighlight> requiredHighlights) {
        if (query == null) {
            return null;
        }

        List<ResultData> results = new ArrayList<ResultData>();
        for (IndexUnit indexUnit : repository.search(query)) {
            results.add(new ResultData(indexUnit.getTitle(), indexUnit.getKeywords(), indexUnit.getFilename(), "",indexUnit.getName(),indexUnit.getSurname(),indexUnit.getGenre()));
        }


        return results;
    }

//    public List<ResultData> getHighlihter(org.elasticsearch.index.query.QueryBuilder query,
//                                       List<RequiredHighlight> requiredHighlights) {
//        if (query == null) {
//            return null;
//        }
//
//        List<ResultData> results = new ArrayList<ResultData>();
//
//     //   SearchQuery search = new SearchQuery();
//        SearchQuery searchQuery = new NativeSearchQueryBuilder()
//                .withQuery(query)
//                .withHighlightFields(new HighlightBuilder.Field("title"))
//                .build();
//
//
//
//        List<IndexUnit> indexUnits = template.queryForList(searchQuery, IndexUnit.class);
//
//        for (IndexUnit indexUnit : indexUnits) {
//            results.add(new ResultData(indexUnit.getTitle(), indexUnit.getKeywords(), indexUnit.getFilename(), ""));
//        }
//
//
//        return results;
//    }

    protected DocumentHandler getHandler(String fileName){
        if(fileName.endsWith(".pdf")){
            return new PDFHandler();
        }
        else{
            return null;
        }
    }
}
