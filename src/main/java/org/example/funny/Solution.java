package org.example.funny;

import java.util.HashMap;
import java.util.Map;

//leetcode 两数之和
public class Solution {

    public int[] twoSum(int[] nums, int target) {
//        HashMap<Integer,Integer> map=new HashMap<>();
//        for(int i=0;i<nums.length;i++){
//            if (map.containsKey(target-nums[i])){
//                return new int[]{i,map.get(target-nums[i])};
//
//            }
//            map.put(nums[i],i);
//        }
//        return new int[0];
//
//    }
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i]+nums[j]==target){
                    return new int[]{i,j};
                }

            }
        }
        return new int[0];
    }
    public boolean isPalindrome(int x){
        if (x<0 || (x % 10 ==0 && x !=0)||x==0){
            return false;
        }
        int reversed = 0;
        while (x>reversed){
            reversed=reversed*10+x%10;
            x=x/10;
        }
        return x==reversed || x==reversed/10;
    }
    Map<Character,Integer> map=new HashMap<Character,Integer>(){
        {
            put('I',1);
            put('V',5);
            put('X',10);
            put('L',50);
            put('C',100);
            put('D',500);
            put('M',1000);
        }
    };
    public int romanToInt(String s) {
        int res=0;
        int lenth=s.length();
        for (int i=0;i<lenth;i++){
            int value=map.getOrDefault(s.charAt(i),0);
            if(i<lenth-1 && value<map.getOrDefault(s.charAt(i+1),0)){
                res -=value;
            }
            else{
                res +=value;
            }
        }
        return res;

    }
}
