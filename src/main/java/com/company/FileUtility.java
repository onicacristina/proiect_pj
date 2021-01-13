package com.company;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


public class FileUtility {
    public static void  writeToFile(List<Book> book)
    {
        ObjectMapper mapper= new ObjectMapper();
        ObjectWriter writer=mapper.writer(new DefaultPrettyPrinter());

        File file=new File("src/main/resources/booksList.json");

        try {
            writer.writeValue(file,book);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Book> readFromFile() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            String json = new String(Files.readAllBytes(Paths.get("src/main/resources/booksList.json")));
            List<Book>  books = mapper.readValue(json, new TypeReference<List<Book>>(){});
            return books;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
