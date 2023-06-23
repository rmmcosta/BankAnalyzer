package org.rmmcosta;

import java.util.HashMap;
import java.util.Map;

import static org.rmmcosta.FileHandle.getFileLines;

public class CategoriesProcessor implements ICategoriesProcessor {
    private final Map<String, String> categories;

    public CategoriesProcessor(String filePath) {
        categories = new HashMap<>();
        processCategoriesFile(filePath);
    }

    private void processCategoriesFile(String filePath) {
        getFileLines(filePath).forEach(line -> {
            String[] lineSplit = line.split(",");
            categories.put(lineSplit[0].trim(), lineSplit[1].trim());
        });
    }

    public String getCategory(String entity) {
        return categories.get(entity);
    }

}
