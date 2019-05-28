package algorithm;

import java.util.HashSet;
import java.util.Set;

/**
 * 算法：最长连续序列
 * 描述：
 * 给定一个未排序的整数数组，找出最长连续序列的长度。
 * 要求算法的时间复杂度为 O(n)。
 * 示例 1:
 * 输入: [100, 4, 200, 1, 3, 2]
 * 输出: 4
 * 解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。
 *
 * 思路：
 * 最简单直接的想法是:将数组排序，然后扫一遍排好序的序列，从中找出最长的即可，这样的话时间是O(nlogn)+O(n)，显然不符合题目要求。
 * 我们自己找的话，看到一个数例如4，我们会下意识的去找有没有3和5，有3那么就会继续找2，以此类推，直到找不到为止，找过的数以后我就不会再去找了
 * 1. 用hashset记录num集合
 * 2. 遍历hashset，移除num，以num为中间点，统计连续小于和连续大于num的数目（sum），并从hashset中移除
 * 3. 用sum和上一次的最大长度max做比较，得出本次最大的max
 * 4. 遍历完hashset集合的元素，即可得到最长连续序列的长度max
 */
public class LongestConsecutive {

    public static int longestConsecutive(int[] nums) {
        if(nums == null || nums.length == 0) return 0;

        Set<Integer> set = new HashSet<>();

        for(int num: nums) {
            set.add(num);
        }

        int max = 1;
        for(int num: nums) {
            // 移除num
            if(set.remove(num)) {
                int val = num;
                int sum = 1;
                // 用sum记录统计连续小于num的数目
                while(set.remove(val-1)) {
                    val--;
                }
                sum += num - val;

                val = num;
                // 用sum记录统计连续大于num的数目
                while(set.remove(val+1)) {
                    val++;
                }
                sum += val - num;

                // 用sum和上一次的最大长度max做比较，得出本次最大的max
                max = Math.max(max, sum);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] array = new int[]{100, 4, 200, 1, 3, 2};
        int max = longestConsecutive(array);
        System.out.println("最长连续序列长度:" + max);
    }
}
