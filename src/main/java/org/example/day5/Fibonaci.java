package org.example.day5;
//递归
public class Fibonaci {
    public static  int fibonacci(int n){
        if(n<=2){
            return 1;
        }
        return fibonacci(n-1)+fibonacci(n-2);
    }
}
