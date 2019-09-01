package com.semitop7.file.generator;

import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import static java.lang.String.format;

public class Generator {
    private static Logger LOGGER = LoggerFactory.getLogger(Generator.class);
    private static volatile Generator instance;
    private static final int RANGE = 'z' - 'a';
    private static final int START = 'a';
    private static final String FILE_NAME = "src/main/resources/file%d.txt";

    private Generator() {
    }

    public static Generator getInstance() {
        if (instance == null) {
            instance = new Generator();
        }
        return instance;
    }

    public void generateFiles(int fileCount, int fileSize, int stringLength) throws IOException {
        int count = 0;
        while (count < fileCount) {
            String name = format(FILE_NAME, ++count);
            StopWatch watch = StopWatch.createStarted();
            writeToFile(name, fileSize, stringLength);
            watch.stop();
            LOGGER.info(format("File: %s, time: %d c\n", name, watch.getTime(TimeUnit.SECONDS)));
        }
    }

    public String[] generateFilePaths(int count, String filePathFormat) {
        String[] filePaths = new String[count];
        for(int i = 1; i<= count; i++) {
            filePaths[i] = String.format(filePathFormat, count);
        }
        return filePaths;
    }

    private void writeToFile(String fileName, int fileSize, int stringLength) throws IOException {
        File newFile = new File(fileName);
        if (!newFile.exists()) {
            Files.copy(createStream(fileSize, stringLength), Paths.get(newFile.toURI()));
        }
    }

    private InputStream createStream(int fileSize, int stringLength) {
        return new InputStream() {
            private int size = 0;
            private int charCount = 0;

            @Override
            public int read() {
                if (++size < fileSize) {
                    if (stringLength < ++charCount) {
                        charCount = 0;
                        return '\n';
                    } else {
                        return (int) (Math.random() * RANGE + START);
                    }
                } else {
                    return -1;
                }
            }
        };
    }
}