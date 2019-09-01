package com.semitop7;

import com.semitop7.service.file.generator.GeneratorImpl;
import com.semitop7.info.HeapInfo;

import java.io.IOException;

public class Run {
    private static final int FILE_COUNT = 5;
    private static final int GB = 1024 * 1024 * 1024;
    private static final int CHAR_COUNT = 8;
    private static HeapInfo heapInfo = HeapInfo.getInstance();

    public static void main(String[] args) throws IOException {
        heapInfo.printMaxHeapSize();
//        TODO uncomment for generation test files
//        generator.generateFiles(FILE_COUNT, GB, CHAR_COUNT);

    }
}
