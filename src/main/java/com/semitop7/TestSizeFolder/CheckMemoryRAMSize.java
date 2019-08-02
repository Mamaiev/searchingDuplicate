package com.semitop7.TestSizeFolder;

public class CheckMemoryRAMSize {
    public static void main(String[] args) {
        System.out.println(formatSize(Runtime.getRuntime().maxMemory()));
        System.out.println(formatSize(Runtime.getRuntime().availableProcessors()));
        System.out.println(formatSize(Runtime.getRuntime().freeMemory()));
        System.out.println(formatSize(Runtime.getRuntime().totalMemory()));

    }

    public static String formatSize(long v) {
        if (v < 1024) return v + " B";
        int z = (63 - Long.numberOfLeadingZeros(v)) / 10;
        return String.format("%.1f %sB", (double) v / (1L << (z * 10)), " KMGTPE".charAt(z));
    }
}
