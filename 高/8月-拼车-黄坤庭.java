package algorithm;

import java.util.*;

/**
 * 算法：拼车
 * <p>
 * 描述：
 * 假设你是一位顺风车司机，车上最初有 capacity 个空座位可以用来载客。由于道路的限制，车只能向一个方向行驶（也就是说，不允许掉头或改变方向，你可以将其想象为一个向量）。
 * 这儿有一份行程计划表 trips[][]，其中 trips[i] = [num_passengers, start_location, end_location] 包含了你的第 i 次行程信息：
 * 必须接送的乘客数量； 乘客的上车地点； 以及乘客的下车地点。 这些给出的地点位置是从你的 初始 出发位置向前行驶到这些地点所需的距离（它们一定在你的行驶方向上）。
 * 请你根据给出的行程计划表和车子的座位数，来判断你的车是否可以顺利完成接送所用乘客的任务（当且仅当你可以在所有给定的行程中接送所有乘客时，返回 true，否则请返回 false）。
 * <p>
 * 思路：
 * 1 将所有行程按照上车地点从小到大排序
 * 2 遍历所有行程，模拟车子行驶，使用TreeMap记录有某个下车地点下车人数，按照以下逻辑处理
 * 2.1 如果当前没有乘客在车上（TreeMap为空），则判断本次行程是否能够让乘客都坐下，若是将本次的行程的下车地点为key，乘客数目为value存入到TreeMap中；否则直接返回false
 * 2.2 如果当前有乘客（TreeMap不为空），则检查所有上车乘客是否存在需要下车的人（TreeMap中的key下车地点小于或等于本次行程的上车地点）并将其移除。
 * 然后判断车子当前是否有足够座位载客（TreeMap的value之和加上本次行程的乘客数是否不大于车子总座位数），
 * 如果足够则加入本次行程（TreeMap记录下来，如果存在相同下车地点，则人数相加），否则返回false
 */
public class CarPooling {
    /**
     * @param trips    行程计划表 trips[i] = [num_passengers, start_location, end_location]
     * @param capacity 座位数
     * @return
     */
    public boolean carPooling(int[][] trips, int capacity) {
        boolean flag = true;
        // 根据上车地点从小到大排序
        Arrays.sort(trips, Comparator.comparingInt(o -> o[1]));
        // key为下车地点，value为乘客数目
        TreeMap<Integer, Integer> capacityMap = new TreeMap<>();
        for (int i = 0; i < trips.length; i++) {
            // 乘客数量
            int numPassengers = trips[i][0];
            // 上车地点
            int startLocation = trips[i][1];
            // 下车地点
            int endLocation = trips[i][2];
            if (!capacityMap.isEmpty()) {
                Set<Integer> locationSet = new TreeSet<>();
                locationSet.addAll(capacityMap.keySet());
                Iterator<Integer> it = locationSet.iterator();
                while (it.hasNext()) {
                    Integer lastEndLocation = it.next();
                    if (lastEndLocation <= startLocation) { // 到达终点，乘客下车
                        capacityMap.remove(lastEndLocation);
                    }
                }
                // 计算当前总乘客数
                int totalCap = capacityMap.values().stream().mapToInt(Integer::intValue).sum() + numPassengers;
                if (totalCap > capacity) { // 车子座位不足
                    flag = false;
                    break;
                }
                if (capacityMap.containsKey(endLocation)) { // 是否存在同一个下车地点的乘客
                    capacityMap.put(endLocation, capacityMap.get(endLocation) + numPassengers);
                } else {
                    capacityMap.put(endLocation, numPassengers);
                }
            } else {
                if (numPassengers > capacity) { // 车子座位不足
                    flag = false;
                    break;
                }
                capacityMap.put(endLocation, numPassengers);
            }
        }
        return flag;
    }

    /**
     * 输入：trips = [[2,1,5],[3,3,7]], capacity = 4
     * 输出：false
     *
     * @param args
     */
    public static void main(String[] args) {
        int capacity = 4;
        int[][] trips = new int[2][3];
        trips[0][0] = 2;
        trips[0][1] = 1;
        trips[0][2] = 5;
        trips[1][0] = 3;
        trips[1][1] = 3;
        trips[1][2] = 7;
        CarPooling solution = new CarPooling();
        boolean result = solution.carPooling(trips, capacity);
        System.out.println("result:" + result);
    }
}
