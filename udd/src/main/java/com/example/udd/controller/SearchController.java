package com.example.udd.controller;

import com.example.udd.Model.RequiredHighlight;
import com.example.udd.Model.ResultData;
import com.example.udd.Model.SearchModel;
import com.example.udd.Model.SearchType;
import com.example.udd.search.QueryBuilder;
import com.example.udd.search.ResultRetriever;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private ResultRetriever resultRetriever;

    @PostMapping(value="/search/phrase", consumes="application/json")
    public ResponseEntity<List<ResultData>> searchPhrase(@RequestBody SearchModel searchModel) throws Exception {
        List<ResultData> results = new ArrayList<>();
        System.out.println(searchModel.getKeywords());
        List<RequiredHighlight> rh = new ArrayList<RequiredHighlight>();
        BoolQueryBuilder builder = QueryBuilders.boolQuery();
        if(!searchModel.getTitle().equals("")) {
            org.elasticsearch.index.query.QueryBuilder query= QueryBuilder.buildQuery(SearchType.phrase, "title", searchModel.getTitle());
            if(searchModel.getOperation().equalsIgnoreCase("OR")) {
                builder.should(query);
            } else {
                builder.must(query);
            }
            rh.add(new RequiredHighlight("title", searchModel.getTitle()));
          //  results.add(resultRetriever.getResults(query,rh));
            results = resultRetriever.getResults(query, rh);
        }  if (!searchModel.getKeywords().equals("")) {
          //  System.out.println(searchModel.getSearchType());
            org.elasticsearch.index.query.QueryBuilder query= QueryBuilder.buildQuery(SearchType.phrase, "keywords", searchModel.getKeywords());
            if(searchModel.getOperation().equalsIgnoreCase("OR")) {
                builder.should(query);
            } else {
                builder.must(query);
            }
            rh.add(new RequiredHighlight("title", searchModel.getKeywords()));
            results = resultRetriever.getResults(query, rh);
        }  if (!searchModel.getGenre().equals("")) {
            org.elasticsearch.index.query.QueryBuilder query= QueryBuilder.buildQuery(SearchType.phrase, "genre", searchModel.getGenre());
            if(searchModel.getOperation().equalsIgnoreCase("OR")) {
                builder.should(query);
            } else {
                builder.must(query);
            }
            rh.add(new RequiredHighlight("title", searchModel.getGenre()));
            results = resultRetriever.getResults(query, rh);
        }  if (!searchModel.getName().equals("")) {
            org.elasticsearch.index.query.QueryBuilder query= QueryBuilder.buildQuery(SearchType.phrase, "name", searchModel.getName());
            if(searchModel.getOperation().equalsIgnoreCase("OR")) {
                builder.should(query);
            } else {
                builder.must(query);
            }
            rh.add(new RequiredHighlight("title", searchModel.getName()));
            results = resultRetriever.getResults(query, rh);
        } if (!searchModel.getSurname().equals("")) {
            org.elasticsearch.index.query.QueryBuilder query= QueryBuilder.buildQuery(SearchType.phrase, "surname", searchModel.getSurname());
            if(searchModel.getOperation().equalsIgnoreCase("OR")) {
                builder.should(query);
            } else {
                builder.must(query);
            }
            rh.add(new RequiredHighlight("title", searchModel.getSurname()));
            results = resultRetriever.getResults(query, rh);
        } if (!searchModel.getText().equals("")) {
            org.elasticsearch.index.query.QueryBuilder query= QueryBuilders.queryStringQuery(searchModel.getText());
            if(searchModel.getOperation().equalsIgnoreCase("OR")) {
                builder.should(query);
            } else {
                builder.must(query);
            }
            rh.add(new RequiredHighlight("title", searchModel.getText()));
            results = resultRetriever.getResults(query, rh);
        }
        // org.elasticsearch.index.query.QueryBuilder query= QueryBuilder.buildQuery(SearchType.phrase, simpleQuery.getField(), simpleQuery.getValue());
        results = resultRetriever.getResults(builder, rh);

        return new ResponseEntity<List<ResultData>>(results, HttpStatus.OK);
    }


    @PostMapping("/downloadPdf")
        public StreamingResponseBody getSteamingFile(HttpServletResponse response,@RequestParam(name = "filename") String filename) throws IOException {
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=\"demo.pdf\"");
            InputStream inputStream = new FileInputStream(new File(filename));
            return outputStream -> {
                int nRead;
                byte[] data = new byte[1024];
                while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
                    System.out.println("Writing some bytes..");
                    outputStream.write(data, 0, nRead);
                }
            };
        }


    @PostMapping("/download")
    public ResponseEntity downloadFileFromLocal(@RequestParam(name = "filename") String filename) throws IOException {
        Path path = Paths.get(filename);


        Resource resource = null;
        try {
            resource = new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/pdf"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}
