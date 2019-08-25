import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//import java.util.*;
//
//public class Main {
//
//    public static void main(String[] arg) {
//        Scanner in = new Scanner(System.in);
//        Comparator<Integer> comparator = new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o2 - o1;
//            }
//        };
//        while (in.hasNext()) {
//            String inputArray = in.next();
//            String replace = inputArray.replace("[", "");
//            String replaceSecond = replace.replace("]", "");
//            String[] split = replaceSecond.split(",");
//            ArrayList<Integer> mlist = new ArrayList<>();
//            ArrayList<Integer> bigList = new ArrayList<>();
//            ArrayList<Integer> slist = new ArrayList<>();
//            StringBuilder builder = new StringBuilder();
//            for (int i = 0; i < split.length; i++) {
//                int timeNum = Integer.valueOf(split[i]);
//                if (timeNum > 9) System.out.println("invalid");
//                mlist.add(timeNum);
//            }
//            for (int i = 0; i < mlist.size(); i++) {
//                Integer timeNum2 = mlist.get(i);
//                if (timeNum2 > 5) {
//                    bigList.add(timeNum2);
//                    mlist.remove(i);
//                }
//                if (timeNum2 <= 2) {
//                    slist.add(timeNum2);
//                    mlist.remove(i);
//                }
//                Collections.sort(slist, comparator);
//                Collections.sort(bigList, comparator);
//                Collections.sort(mlist, comparator);
//                if (slist.size() < 1 || bigList.size() > 3) {
//                    System.out.println("invalid");
//                }
//                if (slist.get(0) == 2) {
//                    int second = 0;
//                    for (int j = 0; j < mlist.size(); j++) {
//                        Integer integer = mlist.get(j);
//                        if (integer <= 4) {
//                            second = integer;
//                            mlist.remove(j);
//                            break;
//                        }
//                    }
//                    builder.append("2").append(second + ":");
//                    slist.remove(0)
//                } else if(bigList.size()>0){
//                    builder.append(slist.get(0)).append(bigList.get(0) + ":");
//                    slist.remove(0);
//                    bigList.remove(0);
//                }else {
//                    builder.append(slist.get(0)).append(mlist.get(0) + ":");
//                    slist.remove(0);
//                    mlist.remove(0);
//                }
//                if(mlist.size()>0 && bigList.size()>0){
//                    builder.append(mlist.get(0)).append(bigList.get(0) + ":");
//                }else if ()
//
//
//            }
//        }
//
//
//    }
//
//
//}
//
public class Main {
    public int NumberOf1(int n) {
        int count = 0;
        if (n >= 0) {
            while (n != 0) {
                if (n % 2 == 1) {
                    count++;
                }
                n = n / 2;
            }
            return count;
        } else {
            while (n != 0) {
                n = n & (n - 1);
                count++;
            }
            return count;
        }
    }

    public static void main(String[] args) {
        int[] array;

        ArrayList<Integer> list = new ArrayList<>();
        list.toArray();
//        System.arraycopy();
        Main main = new Main();
        int i = main.NumberOf1(-60000);
        System.out.println(i);
    }
    public void reOrderArray(int [] array) {
        List<Integer> oddlist = new ArrayList<>();
        List<Integer> evenlist = new ArrayList<>();
        for (int i : array) {
            if((i&1)==0){
                evenlist.add(i);
            }else
                oddlist.add(i);
        }
        int[] odd = oddlist.stream().mapToInt(Integer::valueOf).toArray();
        int[] even = evenlist.stream().mapToInt(Integer::valueOf).toArray();
        System.arraycopy(odd,0,array,0,oddlist.size());
        System.arraycopy(even,0,array,oddlist.size(),evenlist.size());


    }
}