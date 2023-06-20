package org.rmmcosta;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileHandleTest {
    FileHandle fileHandle;

    @BeforeEach
    void setUp() {
        fileHandle = new FileHandle();
    }

    @Test
    void testCorrectFileContent() {
        String fileContent = fileHandle.getFileContent("src\\test\\resources\\TestFile.csv");
        String expectedFileContent = "Cenas,Coisas\nTal,Coiso";

        // Normalize the strings by removing whitespace and newline characters
        expectedFileContent = expectedFileContent.replaceAll("\\s+", "");
        fileContent = fileContent.replaceAll("\\s+", "");
        assertEquals(expectedFileContent, fileContent);
    }

    @Test
    void testCorrectFileLines() {
        List<String> fileLines = fileHandle.getFileLines("src\\test\\resources\\TestFile.csv");
        String expectedLine1 = "Cenas,Coisas";
        String expectedLine2 = "Tal,Coiso";
        assertEquals(expectedLine1, fileLines.get(0).replaceAll("\\s+", ""));
        assertEquals(expectedLine2, fileLines.get(1).replaceAll("\\s+", ""));
    }
}