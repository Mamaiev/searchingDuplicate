package com.semitop7.service.file.generator;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import static com.semitop7.constant.FileConstant.*;
import static java.lang.String.format;

@Service
public class GeneratorImpl implements Generator {
    private static Logger LOGGER = LoggerFactory.getLogger(GeneratorImpl.class);

    @Override
    public void generateFiles(String fileName,
                              int fileCount,
                              int fileSize,
                              int stringLength) throws IOException {
        int count = 0;
        fileName = StringUtils.isBlank(fileName) ? DEFAULT_FILE_NAME : fileName;
        while (count < fileCount) {
            String name = format(fileName, ++count);
            StopWatch watch = StopWatch.createStarted();
            writeToFile(name, fileSize, stringLength);
            watch.stop();
            LOGGER.info(format("File: %s, time: %d c\n", name, watch.getTime(TimeUnit.SECONDS)));
        }
    }

    @Override
    public String[] generateFilePaths(int count, String filePathFormat) {
        String[] filePaths = new String[count];
        for (int i = 1; i <= count; i++) {
            filePaths[i] = String.format(filePathFormat, count);
        }
        return filePaths;
    }

    @Override
    public void writeToFile(String fileName, int fileSize, int stringLength) throws IOException {
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