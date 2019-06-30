package algorithm;

import java.util.Stack;

/**
 * 算法：最大矩形面积
 * 描述：给定一个仅包含 0 和 1 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 * 输入:
 * [
 *   ["1","0","1","0","0"],
 *   ["1","0","1","1","1"],
 *   ["1","1","1","1","1"],
 *   ["1","0","0","1","0"]
 * ]
 * 输出: 6
 *
 * 思路：
 * 1.首先定义一个一维数组height[]，默认值取二维数组matrix[0][]的第一行数据{'1','0','1','0','0'}，计算出最大面积为0
 * 2.然后更新一维数组，取二维数组的第二行数据{'1','0','1','1','1'}，如果matrix[1][i] 等于0，设置高度height[i] = 0，否则高度height[i] += 1;
 * 第二行数据计算完之后，可以得出height[]为{'2','0','2','1','1'}，计算出最大面积为3
 * 3.按照步骤1和2算出最后的一维数组height[]为{'4','0','0','3','0'}，最大面积为6
 *
 */
public class MaximalRectangle {

    public static void main(String[] args) {
        char[][] matrix =
                {{'1', '0', '1', '0', '0'},
                 {'1', '0', '1', '1', '1'},
                 {'1', '1', '1', '1', '1'},
                 {'1', '0', '0', '1', '0'}};

        MaximalRectangle solution = new MaximalRectangle();
        int result = solution.maximalRectangle(matrix);
        System.out.println(result);
    }

    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;

        int[] height = new int[matrix[0].length];
        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[0][i] == '1') {
                height[i] = 1;
            }
        }
        int result = largestInLine(height);
        for (int i = 1; i < matrix.length; i++) {
            resetHeight(matrix, height, i);
            int tempResult = largestInLine(height);
            result = Math.max(result, tempResult);
        }

        return result;
    }


    public void resetHeight(char[][] matrix, int[] height, int idx) {
        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[idx][i] == '1') {
                height[i] += 1;
            } else {
                height[i] = 0;
            }
        }
    }

    public int largestInLine(int[] height) {
        if (height == null || height.length == 0) return 0;
        int len = height.length;
        Stack<Integer> s = new Stack<>();
        int maxArea = 0;
        for (int i = 0; i <= len; i++) {
            int h = (i == len ? 0 : height[i]);
            if (s.isEmpty() || h >= height[s.peek()]) {
                s.push(i);
            } else {
                int tp = s.pop();
                maxArea = Math.max(maxArea, height[tp] * (s.isEmpty() ? i : i - 1 - s.peek()));
                i--;
            }
        }
        return maxArea;
    }
}
