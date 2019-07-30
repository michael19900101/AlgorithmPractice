package algorithm;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 算法：滑动谜题
 * 描述：
 * 在一个 2 x 3 的板上（board）有 5 块砖瓦，用数字 1~5 来表示, 以及一块空缺用 0 来表示.
 * 一次移动定义为选择 0 与一个相邻的数字（上下左右）进行交换.
 * 最终当板 board 的结果是 [[1,2,3],[4,5,0]] 谜板被解开。
 * 给出一个谜板的初始状态，返回最少可以通过多少次移动解开谜板，如果不能解开谜板，则返回 -1 。
 *
 * 示例：
 * 输入：board = [[1,2,3],[4,0,5]]
 * 输出：1
 * 解释：交换 0 和 5 ，1 步完成
 * 输入：board = [[1,2,3],[5,4,0]]
 * 输出：-1
 * 解释：没有办法完成谜板
 * 输入：board = [[4,1,2],[5,0,3]]
 * 输出：5
 * 解释：
 * 最少完成谜板的最少移动次数是 5 ，
 * 一种移动路径:
 * 尚未移动: [[4,1,2],[5,0,3]]
 * 移动 1 次: [[4,1,2],[0,5,3]]
 * 移动 2 次: [[0,1,2],[4,5,3]]
 * 移动 3 次: [[1,0,2],[4,5,3]]
 * 移动 4 次: [[1,2,0],[4,5,3]]
 * 移动 5 次: [[1,2,3],[4,5,0]]
 * 输入：board = [[3,2,4],[1,5,0]]
 * 输出：14
 *
 * 提示：
 * board 是一个如上所述的 2 x 3 的数组. board[i][j] 是一个 [0, 1, 2, 3, 4, 5] 的排列.
 *
 */
public class SlidingPuzzle {
    // 行下标x相对偏移dx个单位
    int[] dx = {0, 0, -1, 1};
    // 列下标y相对偏移dy个单位
    int[] dy = {1, -1, 0, 0};

    class Idx {
        // 0所在的行下标
        int x;
        // 0所在的列下标
        int y;
        // 交换次数
        int cnt;
        // 当前二维数组的线性表示形式
        String board;

        public Idx(int x, int y, int cnt, String board) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.board = board;
        }
    }

    /**
     * 将二维数组用字符串线性显示
     * @param b
     * @return
     */
    public String hax(int[][] b) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < b.length; ++i) {
            for (int j = 0; j < b[i].length; ++j) {
                sb.append(b[i][j]);
            }
        }
        return sb.toString();
    }

    /**
     * 交换下标(x,y)和(xi,yi)的元素位置，重新得到新的线性字符串
     * @param str 原线性字符串
     * @param x 0的行下标
     * @param y 0的列下标
     * @param xi 交换后0的行下标
     * @param yi 交换后0的列下标
     * @param w  列长度
     * @return
     */
    public String swap(String str, int x, int y, int xi, int yi, int w) {
        StringBuilder sb = new StringBuilder(str);
        int f = x * w + y;
        int s = xi * w + yi;
        String c1 = str.substring(f, f + 1);
        String c2 = str.substring(s, s + 1);
        sb.replace(s, s + 1, c1);
        sb.replace(f, f + 1, c2);
        return sb.toString();
    }

    public int slidingPuzzle(int[][] board) {
        // 0所在的行下标
        int x = 0;
        // 0所在的列下标
        int y = 0;
        // 二维数组行的长度
        int n = board.length;
        // 二维数组列的长度
        int m = board[0].length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (i != n - 1 || j != m - 1) {
                    sb.append(i * m + j + 1);
                }
                if (board[i][j] == 0) {
                    x = i;
                    y = j;
                }
            }
        }
        sb.append(0);
        // 将0放在原字符串的最后一位，按顺序排列，也就是目标字符串
        String ans = sb.toString();
        Set<String> vis = new HashSet<>();
        Queue<Idx> queue = new LinkedList<>();
        // 用字符串st将二维数组线性显示
        String st = hax(board);
        queue.add(new Idx(x, y, 0, st));
        vis.add(st);
        while (!queue.isEmpty()) {
            Idx cur = queue.poll();
            if (cur.board.equals(ans)) {
                return cur.cnt;
            }
            for (int i = 0; i < 4; ++i) {
                int xi = cur.x + dx[i];
                int yi = cur.y + dy[i];
                if (xi >= 0 && yi >= 0 && xi < n && yi < m) {
                    String s = swap(cur.board, cur.x, cur.y, xi, yi, m);
                    if (!vis.contains(s)) {
                        queue.add(new Idx(xi, yi, cur.cnt + 1, s));
                        vis.add(s);
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
//        board = [[1,2,3],[4,0,5]]
        int [][] board = new int[2][3];
        board[0][0] = 1;board[0][1] = 2;board[0][2] = 3;
        board[1][0] = 4;board[1][1] = 0;board[1][2] = 5;

        SlidingPuzzle solution = new SlidingPuzzle();
        solution.slidingPuzzle(board);
    }

    /**
     * 解题思路：
     * 首先知道我们需要的目标字符串123450
     *
     *     | [0] [1] [2] y
     * [0] |  1   2   3
     * [1] |  4   0   5       board = 123405（以这个为基准，进行偏移）
     *  x
     *
     *
     *     | [0] [1] [2] y
     * [0] |  1   2   3       0所在坐标偏移[0,1]，交换次数cur = 1
     * [1] |  4   5   0       board = 123450
     *  x
     *
     *
     *     | [0] [1] [2] y
     * [0] |  1   2   3       0所在坐标偏移[0,-1]，交换次数cur = 1
     * [1] |  0   4   5       board = 123045
     *  x
     *
     *
     *     | [0] [1] [2] y
     * [0] |  1   0   3       0所在坐标偏移[-1,0]，交换次数cur = 1
     * [1] |  4   2   5       board = 103425
     *  x
     *
     *
     *     | [0] [1] [2] y
     * [0] |  1   2   3       0所在坐标偏移[1,0],超出x轴范围，不符合
     * [1] |  4   ↓   5
     *  x         0
     *
     *  把上述偏移的字符串放在队列里面，从队列一个一个取出board,
     *  判断board是否等于目标字符串，如果是的话，取出比较次数cnt，否则返回-1
     */
}
