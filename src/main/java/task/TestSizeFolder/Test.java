package task.TestSizeFolder;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Test {

    static int countOfNameOfFileOutput;

    public static void main(String[] args) throws IOException, InterruptedException {
        long start = System.currentTimeMillis();
//        appendAllFiles();
        checkDuplicate(start);

        System.out.println("Running time is " + (System.currentTimeMillis() - start) + "ms");
    }


    static void appendAllFiles() throws IOException {
        String[] nameOfFiles = {"/Users/admin/Downloads/1/test1.txt",
                "/Users/admin/Downloads/1/test2.txt",
                "/Users/admin/Downloads/1/test3.txt",
                "/Users/admin/Downloads/1/test4.txt",
                "/Users/admin/Downloads/1/test5.txt"};
        PrintWriter fileOutPut = new PrintWriter("/Users/admin/Downloads/1/testOutPut.txt");
        for (int i = 0; i < nameOfFiles.length; i++) {
            File file = new File(nameOfFiles[i]);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line = bufferedReader.readLine();
            while (line != null) {
                fileOutPut.println(line);
                line = bufferedReader.readLine();

            }
            bufferedReader.close();
        }
        fileOutPut.flush();
        fileOutPut.close();
    }

    static void checkDuplicate(long start) throws IOException, InterruptedException {
        Map<String, Integer> map = new HashMap();
        File file = new File("/Users/admin/Downloads/1/testZZZ.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line = bufferedReader.readLine();
        List<String> nameOfLists = new ArrayList<>();
//        int count = 1;
        int countSize = 1;
        while (line != null) {
            if (map.containsKey(line)) {
                map.put(line, map.get(line) + 1);
            } else {
                map.put(line, 1);
            }
            line = bufferedReader.readLine();
//            count++;
            countSize++;
            if (countSize > 500) {
                if (((checkMemory() / 1024) / 1024) < 100 && map.size() > 1500000) {
                    checkMemory();
                    nameOfLists.add("/Users/admin/Downloads/1/out/run/" + writeToFile(sortByValue(map), 1));
                    map.clear();
                    System.gc();
                }
                countSize = 0;
            }
        }
        System.out.println();
        map.clear();
        System.gc();

        bufferedReader.close();
        countOfNameOfFileOutput = 0;
        checkDuplicateInResult(nameOfLists);
    }

    static void checkDuplicateInResult(List<String> names) throws IOException {
        HashMap<String, Integer> map = new HashMap<>();
        int countSize = 0;
        for (int i = 0; names.size() > i; i++) {
            File file = new File(names.get(i));
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line = bufferedReader.readLine();
            while (line != null) {
                map.put(line.substring(line.lastIndexOf(" "), line.length() - 1), Integer.valueOf(line.substring(0, line.indexOf(" "))));
                line = bufferedReader.readLine();
            }
            for (Map.Entry<String, Integer> value : map.entrySet()) {
                for (int k = 0; names.size() + 1 > k; k++) {
                    File fileSec = new File(names.get(k));
                    BufferedReader brSec = new BufferedReader(new FileReader(fileSec));
                    String lineSec = brSec.readLine();
                    if (value.getValue().equals(lineSec.lastIndexOf(" "))) {
                        value.setValue(value.getValue() + Integer.valueOf(lineSec.substring(0, lineSec.indexOf(" "))));
                    }
                }
            }
            countSize++;
            if (countSize > 500) {
                if (((checkMemory() / 1024) / 1024) < 100 && map.size() > 1500000) {
                    checkMemory();
                    writeToFile(sortByValue(map), 2);
                    map.clear();
                    System.gc();
                }
                countSize = 0;
            }
        }

    }

    static String writeToFile(Map<String, Integer> map, Integer number) throws IOException {
        String pref;
        if (number == 1){
            pref = "run";
        } else {
            pref = "result";
        }
        File file = new File("/Users/admin/Downloads/1/out/" + pref + "/fileOutput" + countOfNameOfFileOutput + ".txt");
        PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8")), false);
        for (Map.Entry<String, Integer> word : map.entrySet()) {
            writer.println(word.getValue() + " - " + word.getKey());
        }
        writer.close();
        countOfNameOfFileOutput++;
        return file.getName();
    }

    public static Map<String, Integer> sortByValue(final Map<String, Integer> wordCounts) {
        return wordCounts.entrySet()
                .stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    public static String formatSize(long v) {
        if (v < 1024) return v + " B";
        int z = (63 - Long.numberOfLeadingZeros(v)) / 10;
        return String.format("%.1f %sB", (double) v / (1L << (z * 10)), " KMGTPE".charAt(z));
    }

    static long checkMemory() {
        System.out.print("\r" + (
                formatSize(Runtime.getRuntime().maxMemory()) + " " +
                        formatSize(Runtime.getRuntime().totalMemory()) + " " +
                        formatSize(Runtime.getRuntime().freeMemory())));
        return Runtime.getRuntime().freeMemory();
    }

}
