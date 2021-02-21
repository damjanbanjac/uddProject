package com.example.udd.controller;

import com.example.udd.Model.IndexUnit;
import com.example.udd.Model.UploadPdf;
import com.example.udd.handlers.Indexer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.PathParam;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.ResourceBundle;

@Controller
public class IndexerController {

    private static String DATA_DIR_PATH;

    static {
        ResourceBundle rb=ResourceBundle.getBundle("application");
        DATA_DIR_PATH=rb.getString("dataDir");
    }

    @Autowired
    private Indexer indexer;


    @GetMapping("/reindex")
    public ResponseEntity<String> index() throws IOException {
        File dataDir = getResourceFilePath(DATA_DIR_PATH);
        long start = new Date().getTime();
        int numIndexed = indexer.index(dataDir);
        long end = new Date().getTime();
        String text = "Indexing " + numIndexed + " files took "
                + (end - start) + " milliseconds";
        return new ResponseEntity<String>(text, HttpStatus.OK);
    }

    @PostMapping("/index/add/{title}/{keywords}/{name}/{surname}/{genre}")
    public ResponseEntity<String> multiUploadFileModel(@RequestParam("files") MultipartFile[] files,@PathVariable String title,@PathVariable String keywords,@PathVariable String name,@PathVariable String surname,@PathVariable String genre) throws Exception {
        System.out.println(files.length + " ovo je duzina files");
        System.out.println(title);
        System.out.println(keywords);
        UploadPdf model = new UploadPdf();
        model.setFiles(files);
        model.setKeywords(keywords);
        model.setTitle(title);
        model.setName(name);
        model.setSurname(surname);
        model.setGenre(genre);
//        System.out.println(model.getFiles().length);
        System.out.println(model.getKeywords());
//       System.out.println("ima ih " + model.getFiles().length);

        try {

            indexUploadedFile(model);

        } catch (IOException e) {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<String>("Successfully uploaded!", HttpStatus.OK);

    }


    //save file
    private String saveUploadedFile(MultipartFile file) throws IOException {
        String retVal = null;
        if (! file.isEmpty()) {
            byte[] bytes = file.getBytes();
            Path path = Paths.get("C:\\Users\\Damjan\\Desktop\\Fakultet\\Master\\uddProject\\udd\\src\\main\\resources\\files" + File.separator + file.getOriginalFilename());
            Files.write(path, bytes);
            retVal = path.toString();
        }
        return retVal;
    }

    private File getResourceFilePath(String path) {
        System.out.println(path + " ovo je path");

        URL url = this.getClass().getClassLoader().getResource(path);
        System.out.println(url);
        File file = null;
        try {
            file = new File(url.toURI());
        } catch (URISyntaxException e) {
            file = new File(url.getPath());
        }
        return file;
    }


    private void indexUploadedFile(UploadPdf model) throws IOException{

        for (MultipartFile file : model.getFiles()) {

            if (file.isEmpty()) {
                continue; //next please
            }
            String fileName = saveUploadedFile(file);
            if(fileName != null){
                IndexUnit indexUnit = indexer.getHandler(fileName).getIndexUnit(new File(fileName));
                indexUnit.setTitle(model.getTitle());
                indexUnit.setKeywords(model.getKeywords());
                indexUnit.setGenre(model.getGenre());
                indexUnit.setName(model.getName());
                indexUnit.setSurname(model.getSurname());
                indexer.add(indexUnit);
            }
        }
    }

}
