package com.semitop7.info;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.Runtime.getRuntime;
import static java.lang.String.format;

public class HeapInfo {
    private static HeapInfo instance;
    private static Logger LOGGER = LoggerFactory.getLogger(HeapInfo.class);

    private HeapInfo() {
    }

    public static HeapInfo getInstance() {
        if (instance == null) {
            instance = new HeapInfo();
        }
        return instance;
    }

    public void printMaxHeapSize() {
        LOGGER.info(format("JVM Max Heap Size: %s", formatSize(getRuntime().maxMemory())));
    }

    public void printFreeHeapSize() {
        LOGGER.info(format("JVM Free Heap Size: %s", formatSize(getRuntime().freeMemory())));
    }

    private String formatSize(long v) {
        if (v < 1024) return v + " B";
        int z = (63 - Long.numberOfLeadingZeros(v)) / 10;
        return format("%.1f %sB", (double) v / (1L << (z * 10)), " KMGTPE".charAt(z));
    }
}