import java.util.*;

/*
 * http://www.leetcode.com/onlinejudge
   3Sum
   Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? 
   Find all unique triplets in the array which gives the sum of zero.
   Note:
   Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
   The solution set must not contain duplicate triplets.
   For example, given array S = {-1 0 1 2 -1 -4},
    A solution set is:
    (-1, 0, 1)
    (-1, -1, 2)
 */
public class SumProblems {
    public void getTwoSum(int[] num, int sum) {
        Set<Integer> set = new HashSet<Integer>();
        for (int i=0; i<num.length; i++) {
            if (set.contains(sum-num[i])) {
                System.out.println(num[i] + "-" + (sum-num[i]));
            }
            set.add(num[i]);
        }
    }

    public ArrayList<ArrayList<Integer>> getThreeSum(int[] num) {
        Set<ArrayList<Integer>> set = new HashSet<ArrayList<Integer>>();
        for (int m=1; m<num.length; m++) {
            int i = 0;
            int j = num.length - 1;
            while(i<m && m<j) {
                int sum = num[i] + num[j] + num[m];
                if (sum == 0) {
                    set.add(new ArrayList<Integer>(Arrays.asList(num[i],num[m],num[j])));
                    i++;
                    j--;
                } else if (sum < 0) {
                    i++;
                } else {
                    j--;
                }
            }
        }
        return new ArrayList<ArrayList<Integer>>(set);
    }

    class Pair {
        int x;
        int y;
        Pair(int i, int j) {
            x = i;
            y = j;
        }
    }

    public ArrayList<ArrayList<Integer>> fourSum(int[] num, int target) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();

        // important! edge case
        if (num.length<4) { return ret; }

        Set<ArrayList<Integer>> set = new HashSet<ArrayList<Integer>>();

        // key-sum, value-index pair
        Map<Integer, List<Pair>> map = new HashMap<Integer, List<Pair>>();

        for (int i=0; i<num.length-1; i++) {
            for (int j=i+1; j<num.length; j++) {
                if (i != j) {
                    int sum = num[i] + num[j];
                    List<Pair> list = (map.containsKey(sum))? 
                        map.get(sum) : new ArrayList<Pair>();
                    list.add(new Pair(i,j));
                    map.put(sum, list);
                }
            }
        }

        for (Map.Entry<Integer, List<Pair>> e : map.entrySet()) {
            Integer key = e.getKey();
            List<Pair> l1 = e.getValue();
            if (map.containsKey(target-key)) {
                List<Pair> l2 = map.get(target-key); 
                for (Pair p1 : l1) {
                    for (Pair p2 : l2) {
                        int i = p1.x;
                        int j = p1.y;
                        int m = p2.x;
                        int n = p2.y;
                        if (i!=m && i!=n && j!=m && j!=n) {
                            //System.out.println(String.format("%s, %s, %s, %s", i,j,m,n));
                            Integer[] array = new Integer[]{num[i],num[j],num[m],num[n]};
                            Arrays.sort(array);
                            set.add(new ArrayList<Integer>(Arrays.asList(array))); 
                        }
                    }
                }
            }
        }

        ret.addAll(set);

        return ret;
    }

    public static void main(String[] args) {
        SumProblems s = new SumProblems();
        System.out.println(s.getThreeSum(new int[]{-1,0,1,2,-1,-4}));
        System.out.println(s.fourSum(new int[]{0,0,0,0}, 0));
    }
}
