package org.example.day4;

public class BubbleSort {
    private BubbleSort(){}
    public  static void sort(int[] arr) {
        int n=arr.length;
        for (int i=0;i<n-1;i++){
            boolean flag=true;
            for (int j=0;j<n-i-1;j++){
                if (arr[j]>arr[j+1]){
                    int temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                    flag=false;
                }

            }
            if (flag){
                break;
            }

        }
        for (int i:arr){
            System.out.println(i);
        }

    }


}
