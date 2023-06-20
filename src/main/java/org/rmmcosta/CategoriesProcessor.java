package org.rmmcosta;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.rmmcosta.FileHandle.getFileLines;

public class CategoriesProcessor implements ICategoriesProcessor {
    private final Map<String, String> _categories;

    public CategoriesProcessor(String filePath) {
        _categories = new HashMap<>();
        processCategoriesFile(filePath);
    }

    private void processCategoriesFile(String filePath) {
        getFileLines(filePath).forEach(line -> {
            String[] lineSplit = line.split(",");
            _categories.put(lineSplit[0].trim(), lineSplit[1].trim());
        });
    }

    public String getCategory(String entity) {
        return _categories.get(entity);
    }

}
