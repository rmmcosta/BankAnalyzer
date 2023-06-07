package org.rmmcosta;

public interface ICategoriesProcessor {
    void processCategoriesFile(String filePath);
    String getCategory(String entity);
}
