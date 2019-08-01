package task.TestSizeFolder;

import org.apache.commons.lang3.time.StopWatch;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class Generator {
    private static final int GB = 1024 * 1024;
    private static final int FILE_COUNT = 1;
    private static final int CHAR_COUNT = 3 + 1;
    private static final int RANGE = 'z' - 'a';
    private static final int START = 'a';
    private static final String FILE_NAME = "/Users/admin/Downloads/1/file%d.txt";

    public static void main(String[] args) throws IOException {
        writeToFiles();
    }

    private static void writeToFiles() throws IOException {
        int count = 0;
        while (count < FILE_COUNT) {
            String name = String.format(FILE_NAME, ++count);
            StopWatch watch = StopWatch.createStarted();
            writeToFile(name);
            watch.stop();
            System.out.printf("File: %s, time: %d c\n", name, watch.getTime(TimeUnit.SECONDS));
        }
    }

    private static void writeToFile(String fileName) throws IOException {
        File newFile = new File(fileName);
        if (!newFile.exists()) {
            Files.copy(createStream(), Paths.get(newFile.toURI()));
        }
    }

    private static InputStream createStream() {
        return new InputStream() {
            private int size = 0;
            private int charCount = 0;

            @Override
            public int read() {
                if (++size < GB) {
                    if (++charCount == CHAR_COUNT) {
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