

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: netty_lecture
 * @description:
 * @author: dzg
 * @create: 2019-04-01 22:22
 **/
public class Solution {
    /**
     * 交换数组里n和0的位置
     *
     * @param array 数组
     * @param len   数组长度
     * @param n     和0交换的数
     */
    // 不要修改以下函数内容
    public void swapWithZero(int[] array, int len, int n) {
//        Main.SwapWithZero(array, len, n);
    }
    // 不要修改以上函数内容


    /**
     * 通过调用swapWithZero方法来排
     *
     * @param array 存储有[0,n)的数组
     * @param len   数组长度
     */
    public void sort(int[] array, int len) {
        // 完成这个函数
        if (array.length != len || array.length == 0) {
            throw new RuntimeException("输入长度不正确");
        }
        while (len >= 1) {
            swapWithZero(array, len, array[len - 1]);
            swapWithZero(array, len, len - 1);
            len--;
        }


    }

    public int[] findMax(int[] array, int len) {
        swapWithZero(array, len, len - 1);
        int[] newArray = new int[array.length - 1];
        for (int i : array) {
            if (i==len-1) break;
            newArray[i] = i;
        }
        return newArray;


    }
}
