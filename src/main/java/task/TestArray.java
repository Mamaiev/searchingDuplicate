package task;

public class TestArray {

//    public static void main(String[] args) {
//        int[] array = new Random().ints(5, -100, 100).toArray();
//        for (int i = 0; i < array.length; i++) {
//            System.out.print(array[i] + " ");
//        }
//        int maxValue;
//        int secondMaxValue;
//        if (array.length > 0){
//            maxValue = array[0];
//            secondMaxValue = array[0];
//        } else {
//            return;
//        }
//        for (int num : array) {
//            if ( num > maxValue){
//                secondMaxValue = maxValue;
//                maxValue = num;
//            }
//        }
//        System.out.println();
//        System.out.println("max " + maxValue);
//        System.out.println("sec " + secondMaxValue);
//    }

//    public static void main(String[] args) {
//        int[] array = new Random().ints(3, -100, 100).toArray();
//        if (array == null || array.length == 0) {
//            return;
//        }
//        Arrays.sort(array);
//        for (int num : array) {
//            System.out.print(num + " ");
//        }
//        System.out.println();
//        System.out.println(getSecondMaxValue(array));
//
//    }
//
//    static int getSecondMaxValue(int[] array) {
//        if (array.length >= 2) {
//            return array[array.length - 2];
//        }
//        if (array.length == 1) {
//            return array[array.length - 1];
//        }
//        return 0;
//    }

    //    public static void main(String[] args) {
//        int[] array = new Random().ints(5, -100, 100).toArray();
//        for (int num : array) {
//            System.out.print(num + " ");
//        }
//        int max = array[1];
//        int prevMax = array[0];
//        for (int i = 0; i < array.length; i++) {
//            if (array[i] > max) {
//                prevMax = max;
//                max = array[i];
//            }
//        }
//        System.out.println();
//        System.out.println("max = " + max);
//        System.out.println("prevMax = " + prevMax);
//    }
//    public static void main(String[] args) {
////        char[] array = {'{', '{', '{', '}'};
////        char[] array = {'{', '{', '}', '}'};
////        char[] array = {'}', '{', '}', '}'};
//        char[] array = {};
//
//        System.out.println(checkArray(array));
//    }
//
//    static boolean checkArray(char[] array) {
//        int count = 0;
//        for (char one : array) {
//            if (one == '{') {
//                count++;
//            } else {
//                count--;
//                if (count < 0) {
//                    return false;
//                }
//            }
//        }
//        if (count != 0) {
//            return false;
//        } else {
//            return true;
//        }
//    }


}
