package org.example;
import java.util.Scanner;
public class caculate
{
    public void caculate(String[] args)
    {
        System.out.println("请输入第一个数字：");
        Scanner sc=new Scanner(System.in);
        double num1=sc.nextDouble();
        System.out.println("请输入运算符（+ - * /）：");
        char ch=sc.next().charAt(0);
        System.out.println("请输入第二个数字：");
        double num2=sc.nextDouble();
        double result=0;
        boolean valid=true;
        switch (ch) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                if(num2==0){
                    valid=false;
                    System.out.println("除数不能为0");
                    break;
                }else{
                    result = num1 / num2;
                }

                break;
            default:
                valid = false;
                break;
        }
        if (valid){
                System.out.println("结果"+num1+" "+ch+" "+num2+"= "+result);
            }

        }
    }
