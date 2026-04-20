package org.example.day4;

public class BinarySearch {
    public static int Search(int[] arr,int key){
        int low=0,high=arr.length-1;
        while (low<=high){
            int mid=(low+high)/2;
            if(arr[mid]==key){
                return mid;

            }else if(arr[mid]>key){
                high=mid-1;

            }else  if(arr[mid]<key){
                low=mid+1;

            }
        }
        return -1;
    }
}
