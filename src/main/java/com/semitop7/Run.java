package com.semitop7;

import com.semitop7.file.generator.Generator;
import com.semitop7.info.HeapInfo;

import java.io.IOException;

public class Run {
    private static final int FILE_COUNT = 5;
    private static final int GB = 1024 * 1024 * 1024;
    private static final int CHAR_COUNT = 8;
    private static HeapInfo heapInfo = HeapInfo.getInstance();
    private static Generator generator = Generator.getInstance();

    public static void main(String[] args) throws IOException {
        heapInfo.printMaxHeapSize();
        generator.generateFiles(FILE_COUNT, GB, CHAR_COUNT);
    }
}
