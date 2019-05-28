package algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * 算法：情侣牵手
 * 描述：
 * N 对情侣坐在连续排列的 2N 个座位上，想要牵到对方的手。 计算最少交换座位的次数，以便每对情侣可以并肩坐在一起。
 * 一次交换可选择任意两人，让他们站起来交换座位。
 * 人和座位用 0 到 2N-1 的整数表示，情侣们按顺序编号，第一对是 (0, 1)，第二对是 (2, 3)，以此类推，最后一对是 (2N-2, 2N-1)。
 * 这些情侣的初始座位  row[i] 是由最初始坐在第 i 个座位上的人决定的。
 *
 * 思路：
 * 1.新建一个HashMap,key为情侣的顺序编号，value为他们的位置下标
 * 2.遍历一个HashMap（遍历步长为2）
 * 2.1 如果这个人编号为偶数，判断他旁边那个人的编号是否比他大于1（座位是从0开始）
 * 2.1.1 如果编号大于1，则证明他们是情侣，
 * 2.1.2 否则找出他情人的数组下标，情人和旁边的人交换位置
 * 2.2 如果这个人编号为奇数，判断他旁边那个人的编号是否比他小于1（座位是从0开始）
 * 2.2.1 如果编号小于1，则证明他们是情侣，
 * 2.2.2 否则找出他情人的数组下标，情人和旁边的人交换位置
 */
public class MinSwapsCouples {

    public static int minSwapsCouples(int[] array) {
        // 新建一个map,key为情侣的顺序编号，value为他们的位置下标
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            map.put(array[i], i);
        }

        // 交换次数
        int result = 0;

        for (int i = 0; i < array.length; i += 2) {
            // 当前这个人的编号值
            int currentVal = array[i];
            // 当前这个人的相邻的人的编号值
            int nextVal = array[i + 1];
            // 如果这个人的编号是偶数
            if (currentVal % 2 == 0) {
                // 判断与他相邻的人的编号是否比自己的编号大1，是则证明他们是情侣
                if (nextVal == currentVal + 1) {
                    continue;
                }
                // 否则，找出他情侣在map的下标index，交换row[i + 1]和row[index]的位置
                int index = map.get(currentVal + 1);
                array[i + 1] = array[index];
                array[index] = nextVal;
                // 交换之后，更新交换的这两个人在map的下标
                map.put(nextVal, index);
                map.put(array[i + 1], i + 1);
                result++;
            } else {
                // 如果这个人的编号是奇数，判断与他相邻的人的编号是否比自己的编号小1，是则证明他们是情侣
                if (nextVal == currentVal - 1) {
                    continue;
                }
                // 否则，找出他情侣在map的下标index，交换row[i + 1]和row[index]的位置
                int index = map.get(currentVal - 1);
                array[i + 1] = array[index];
                array[index] = nextVal;
                // 交换之后，更新交换的这两个人在map的下标
                map.put(nextVal, index);
                map.put(array[i + 1], i + 1);
                result++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] array = new int[]{0, 2, 3, 1};
        minSwapsCouples(array);
        System.out.println("交换之后的数组:");
        for (int i : array){
            System.out.print(i + " ");
        }
    }
}
