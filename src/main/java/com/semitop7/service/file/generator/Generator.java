package com.semitop7.service.file.generator;

import java.io.IOException;

public interface Generator {
    void generateFiles(String fileName,
                       int fileCount,
                       int fileSize,
                       int stringLength) throws IOException;

    String[] generateFilePaths(int count, String filePathFormat);

    void writeToFile(String fileName, int fileSize, int stringLength) throws IOException;
}
