package algorithm;

/**
 * 算法：求最短回文串
 * 概念：“回文串”是一个正读和反读都一样的字符串
 *
 * eg:求abcd的最短回文串
 * 思路：
 * 求“abcd”和反串”dcba“的前缀和后缀最大匹配长度
 * 原始串	 abcd  前缀	a ab abc abcd
 * 反转串	 dcba  后缀	a ba cba dcba
 * 最大匹配 a
 * abcd和dcba的最长匹配为a，一个字符，那么最后的回文串就是 反转串的长度4减去匹配长度1，得到3，
 * 即反转串的前三个字符加上 原始串组成 ”dcbabcd“
 *
 */
public class ShortestPalindrome {

    public static void main(String[] args) {
        String s = "abcd";
        System.out.println(shortestPalindrome(s));
    }


    public static String shortestPalindrome(String s) {
        String r = new StringBuilder(s).reverse().toString();
        String t = s + "#" + r;
        int[] next = new int[t.length()];
        for (int i = 1; i < t.length(); ++i) {
            int j = next[i - 1];
            while (j > 0 && t.charAt(i) != t.charAt(j)){
                j = next[j - 1];
            }
            j += (t.charAt(i) == t.charAt(j)) ? 1 : 0;
            next[i] = j;
        }
        return r.substring(0, s.length() - next[t.length() - 1]) + s;
    }
}