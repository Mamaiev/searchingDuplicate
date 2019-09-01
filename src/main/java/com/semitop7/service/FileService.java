package com.semitop7.service;

import com.semitop7.file.generator.Generator;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.TreeMap;

public class FileService {
    private static final String FILE_NAME = "src/main/resources/file%d.txt";
    private static FileService instance;
    private Generator generator;

    private FileService() {
        generator = Generator.getInstance();
    }

    public static FileService getInstance() {
        if (instance == null) {
            instance = new FileService();
        }
        return instance;
    }

    private void readFiles(int fileCount) throws IOException {
        String[] filePaths = generator.generateFilePaths(fileCount, FILE_NAME);
        readFiles(filePaths);
    }

    private void readFiles(String... filePaths) throws IOException {
        for (String filePath : filePaths) {
            try (RandomAccessFile file = new RandomAccessFile(filePath, "rw")) {
                readFile(file);
            }
        }
    }

    private void readFile(RandomAccessFile file) {
        TreeMap<String, Byte> duplicates = new TreeMap<>();
    }
}
