package algorithm;

import java.util.*;

/**
 * 算法：单词拆分
 * 描述：给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，
 * 在字符串中增加空格来构建一个句子，使得句子中所有的单词都在词典中。返回所有这些可能的句子。
 * 说明：
 * 分隔时可以重复使用字典中的单词。 你可以假设字典中没有重复的单词。
 * 示例 1：
 * 输入:
 * s = "catsanddog"
 * wordDict = ["cat", "cats", "and", "sand", "dog"]
 * 输出:
 * [
 *   "cats and dog",
 *   "cat sand dog"
 * ]
 * 示例 2：
 *
 * 输入:
 * s = "pineapplepenapple"
 * wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 * 输出:
 * [
 *   "pine apple pen apple",
 *   "pineapple pen apple",
 *   "pine applepen apple"
 * ]
 * 解释: 注意你可以重复使用字典中的单词。
 * 示例 3：
 *
 * 输入:
 * s = "catsandog"
 * wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出:
 * []
 *
 * 思路：
 * 1.声明一个全局的cache map集合缓存字符串对应的结果集合
 * 2.遍历字符串s之前，看看cache是否缓存该字符串s的结果，如果是直接返回，否的再继续下面步骤
 * 3.遍历字符串s,每次遍历,将字符串s分割成左子串left和右子串right
 * 4.如果字典dict有左字符串left，并且字典dict有右字符串right的子串（containsSuffix函数）,
 * 递归workBreak函数,找出右子串存在字典中的单词,放进结果集，并用缓存结果到cache map
 * 5.递归结束，返回最终结果
 */
public class WordBreak {

    // 全局的cache map集合缓存字符串对应的结果集合
    private final Map<String, List<String>> cache = new HashMap<>();

    /**
     * 判断子串是否存在字典的单词
     * @param dict 字典
     * @param str 字符串
     * @return
     */
    private boolean containsSuffix(Set<String> dict, String str) {
        for (int i = 0; i < str.length(); i++) {
            if (dict.contains(str.substring(i))) {
                return true;
            }
        }
        return false;
    }

    public List<String> wordBreak(String s, Set<String> dict) {
        if (cache.containsKey(s)) {
            return cache.get(s);
        }
        List<String> result = new LinkedList<>();
        // 如果字典有该字符串s，把s放进结果集
        if (dict.contains(s)) {
            result.add(s);
        }
        // 遍历字符串s,每次遍历,将字符串s分割成左子串left和右子串right
        for (int i = 1; i < s.length(); i++) {
            String left = s.substring(0,i);
            String right = s.substring(i);
            // 如果字典dict有左字符串left，并且字典dict有右字符串right的子串
            if (dict.contains(left) && containsSuffix(dict, right)) {
                // 递归workBreak函数,找出右子串存在字典中的单词,放进结果集
                for (String ss : wordBreak(right, dict)) {
                    result.add(left + " " + ss);
                }
            }
        }
        cache.put(s, result);
        return result;
    }

    public static void main(String[] args) {
//      s = "catsanddog"
//      wordDict = ["cat", "cats", "and", "sand", "dog"]
        String s = "catsanddog";
        Set<String> dict = new HashSet<>();
        dict.add("cat"); dict.add("cats"); dict.add("and"); dict.add("sand"); dict.add("dog");
        WordBreak solution = new WordBreak();
        List<String> result = solution.wordBreak(s, dict);
        System.out.println(result);
    }
}
