package com.semitop7.service;

import com.semitop7.service.file.generator.Generator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.TreeMap;

import static com.semitop7.constant.FileConstant.DEFAULT_FILE_NAME;
import static com.semitop7.constant.FileConstant.FILE_PERMISSIONS;

@Service
public class FileServiceImpl implements FileService {
    private Generator generator;

    @Autowired
    public FileServiceImpl(Generator generator) {
        this.generator = generator;
    }

    @Override
    public void readFiles(String fileName, int fileCount) throws IOException {
        fileName = StringUtils.isBlank(fileName) ? DEFAULT_FILE_NAME : fileName;
        String[] filePaths = generator.generateFilePaths(fileCount, fileName);
        readFiles(filePaths);
    }

    private void readFiles(String... filePaths) throws IOException {
        for (String filePath : filePaths) {
            try (RandomAccessFile file = new RandomAccessFile(filePath, FILE_PERMISSIONS)) {
                readFile(file);
            }
        }
    }

    private void readFile(RandomAccessFile file) {
        TreeMap<String, Byte> duplicates = new TreeMap<>();
    }
}
