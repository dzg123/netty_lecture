import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * @program: netty_lecture
 * @description:
 * @author: dzg
 * @create: 2019-04-16 23:48
 **/
public class Main1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

//        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
//        br.read()
        int stuNum = 0;
        int opNum = 0;
        while (in.hasNext()) {
            stuNum = in.nextInt();
            opNum = in.nextInt();
            int[] score = new int[stuNum];
            for (int i = 0; i < stuNum; i++) {
                score[i] = in.nextInt();
            }
            for (int i = 0; i < opNum; i++) {
                if (in.next().equals("Q")) {
                    int a = in.nextInt();
                    int b = in.nextInt();
                    int max = findMax(score, a, b);
                    System.out.println(max);
                } else {
                    int index = in.nextInt();
                    score[index - 1] = in.nextInt();
                }
            }


        }
    }

    public static int findMax(int[] score, int a, int b) {
        if (a > b) {
            int temp = b;
            b = a;
            a = temp;
        }
        int max = score[a - 1];
        for (int i = 1; i <= b - a; i++) {
            if (max < score[a - 1 + i]) {
                max = score[a - 1 + i];
            }
        }
        return max;
    }
}
