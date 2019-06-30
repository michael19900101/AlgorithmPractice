package algorithm;

/**
 * 算法：戳气球
 * 描述：
 * 有 n 个气球，编号为0 到 n-1，每个气球上都标有一个数字，这些数字存在数组 nums 中。
 * 现在要求你戳破所有的气球。每当你戳破一个气球 i 时，你可以获得 nums[left] * nums[i] * nums[right] 个硬币。 这里的 left 和 right 代表和 i 相邻的两个气球的序号。注意当你戳破了气球 i 后，气球 left 和气球 right 就变成了相邻的气球。
 * 求所能获得硬币的最大数量。
 * 说明:
 * 你可以假设 nums[-1] = nums[n] = 1，但注意它们不是真实存在的所以并不能被戳破。 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 * 示例:
 * 输入: [3,1,5,8]
 * 输出: 167
 * 解释: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
 * coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 *
 * 思路：
 * 可以用动态规划解决这个问题。戳破一个气球意味着分离两个子区间(左、右区间)继续去算能获得最大的硬币数量。
 * 左右区间继续递归之前方法，把最大硬币数量算出来
 */
public class MaxCoins {

    public static void main(String[] args) {
        int[] iNums = {3, 1, 5, 8};
        MaxCoins solution = new MaxCoins();
        System.out.println(solution.maxCoins(iNums));
    }

    public int maxCoins(int[] iNums) {
        int[] nums = new int[iNums.length + 2];
        int n = 1;
        for (int x : iNums) {
            if (x > 0) {
                nums[n++] = x;
            }
        }
        // 两侧添加元素1
        nums[0] = nums[n++] = 1;


        int[][] memo = new int[n][n];
        return burst(memo, nums, 0, n - 1);
    }

    public int burst(int[][] memo, int[] nums, int left, int right) {
        if (left + 1 == right) {
            return 0;
        }
        if (memo[left][right] > 0) {
            return memo[left][right];
        }
        int ans = 0;
        for (int i = left + 1; i < right; ++i) {
            ans = Math.max(ans, nums[left] * nums[i] * nums[right]
                    + burst(memo, nums, left, i) + burst(memo, nums, i, right));
        }
        memo[left][right] = ans;
        return ans;
    }
}
