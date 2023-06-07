package org.rmmcosta;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

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

    public static String[] getFileLines(String filePath) {
        return getFileContent(filePath).split("\n");
    }
}
