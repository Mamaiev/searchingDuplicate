package com.semitop7.service;

import java.io.IOException;

public interface FileService {
    void readFiles(String fileName, int fileCount) throws IOException;
}
