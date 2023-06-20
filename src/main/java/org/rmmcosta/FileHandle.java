package org.rmmcosta;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class FileHandle {
    static String getFileContent(String filePath) {
        File file = new File(filePath);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            return new String(fileInputStream.readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> getFileLines(String filePath) {
        return List.of(getFileContent(filePath).split("\n"));
    }
}
