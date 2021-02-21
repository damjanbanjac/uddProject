package com.example.udd.repository;

import com.example.udd.Model.IndexUnit;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface BookIndexRepository extends ElasticsearchRepository<IndexUnit, String> {

    List<IndexUnit> findByTitle(String title);

    IndexUnit findByFilename(String filename);
}
