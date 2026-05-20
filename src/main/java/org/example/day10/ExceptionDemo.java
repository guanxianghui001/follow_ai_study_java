package org.example.day10;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
public class ExceptionDemo {

    public static void tryCatchBasic(){
        Scanner scanner= new Scanner(System.in);
        try {
            String input=scanner.nextLine();
            int num = Integer.parseInt(input);
            System.out.println("你输入的数字是:"+num);

        }catch (NumberFormatException e){
            System.out.println("输入的格式错误!");
        }
        System.out.println("tryCatchBasic 方法正常结束！");
    }
    public static void mutipleCatch(){
        try{
            int[] arr={1,2,3};
            int x = arr[5];

        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("数组越界"+e.getMessage());
        }catch (ArithmeticException e){
            System.out.println("算数错误"+e.getMessage());
        }catch (NullPointerException e){
            System.out.println("空指针"+e.getMessage());

        }catch(Exception e){
            System.out.println("其他异常"+e.getMessage());
        }
    }
    public static void finallyTest(){
        try{
            System.out.println("尝试执行。。。");
            int result=10/2;
            System.out.println("结果"+result);
            return;

        }catch(Exception e){
            System.out.println("异常"+e.getMessage());

        }finally{
            System.out.println("finally 一定会进行执行！");
        }
    }
    public static int testReturn(){
        int x=10;
        try {
            x=20;
            return x;
        }finally{
            x=30;
            System.out.println("finally中x="+x);
        }
    }
    public static void readFile(String path)throws IOException {
        FileReader reader=null;
        try{
            reader=new FileReader(path);
            char[] buffer = new char[1024];
            reader.read(buffer);
            System.out.println(new String(buffer).trim());
        }finally{
            if (reader!=null){
                reader.close();
            }
        }

    }
    public static int divide(int a,int b){
        if(b==0){
            throw new ArithmeticException("除数不能为0");


        }
        return a/b;
    }
    public static void printException(){
        try {
            String s=null;
            s.length();

        }catch (NullPointerException e){
            System.out.println("异常类型："+e.getClass().getName());
            System.out.println("异常消息："+e.getMessage());
            System.out.println("异常位置");
            e.printStackTrace();
        }
    }
}
