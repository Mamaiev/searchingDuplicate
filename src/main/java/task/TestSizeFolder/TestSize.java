package task.TestSizeFolder;

import java.io.*;

public class TestSize {


    public static void main(String... ignored) throws FileNotFoundException, UnsupportedEncodingException {
        //Size in Gbs of my file that I want
        double wantedSize = Double.parseDouble(System.getProperty("size", "0.5"));

        File file = new File("/Users/admin/Downloads/1/testZZZ.txt");
        long start = System.currentTimeMillis();
        PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8")), false);
        int counter = 0;
        while (true) {
            for (int i = 0; i < 100; i++) {
                writer.print(randomAlphaNumeric(8));
            }
            //Check to see if the current size is what we want it to be
            if (++counter == 20000) {
                if (file.length() >= wantedSize * 1e9) {
                    writer.close();
                    break;
                } else {
                    counter = 0;
                }
            }
        }
        long time = System.currentTimeMillis() - start;
        System.out.printf("Took %.1f seconds to create a file of %.3f GB", time / 1e3, file.length() / 1e9);
    }

    private static final String ALPHA_NUMERIC_STRING = "qwertyuioplkjhgfdsazxcvbnm";

    public static String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));

        }
        builder.append("\n");
        return builder.toString();
    }
}