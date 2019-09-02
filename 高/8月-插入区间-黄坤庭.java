package algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 算法：插入区间
 * <p>
 * 描述：
 * 给出一个无重叠的 ，按照区间起始端点排序的区间列表。
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 * <p>
 * 思路：
 * 循环待插入区间，比较每个区间和待插入区间。有三种情况，分别处理。
 */
public class InsertRange {

    static class Interval {
        int start, end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "start:" + start + ",end" + end;
        }
    }

    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> newIntervals = new ArrayList<Interval>();
        // 空区间组
        if (intervals.size() == 0) {
            newIntervals.add(newInterval);
        }
        // 循环
        for (int i = 0; i < intervals.size(); i++) {
            // 如果新区间的结束值小于区间开始值，插在这里，后面续上
            if (newInterval.end < intervals.get(i).start) {
                newIntervals.add(newInterval);
                for (int j = i; j < intervals.size(); j++) {
                    newIntervals.add(intervals.get(j));
                }
                break;
            }
            // 如果新区间的开始值大于区间结束值，把当前区间加进去
            else if (newInterval.start > intervals.get(i).end) {
                newIntervals.add(intervals.get(i));
            }
            // 出现交叉，需要合并
            else {
                newInterval.start = Math.min(newInterval.start, intervals.get(i).start);
                newInterval.end = Math.max(newInterval.end, intervals.get(i).end);
            }
            // 最后只剩一个数据了，添加进去
            if (i == intervals.size() - 1) {
                newIntervals.add(newInterval);
            }
        }
        return newIntervals;
    }

    public static void main(String[] args) {
        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(1, 3));
        intervals.add(new Interval(6, 9));
        Interval interval = new Interval(2, 5);
        InsertRange solution = new InsertRange();
        List<Interval> result = solution.insert(intervals, interval);
        if (result != null) {
            for (Interval item : result) {
                System.out.println(item.toString());
            }
        }
    }

}
