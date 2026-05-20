//package org.example;
package org.example;
import org.example.day4.BinarySearch;
import org.example.day4.BubbleSort;
import org.example.day5.Fibonaci;
import org.example.day6.Student;
import org.example.day7.Person;
import org.example.day7.Teacher;
import org.example.day8.Animal;
import org.example.day8.Bird;
import org.example.day8.Cat;
import org.example.day8.Dog;
import org.example.funny.Solution;
import org.example.day9.*;
import org.example.day10.*;

import java.io.IOException;

import static org.example.day10.ExceptionDemo.*;

// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        System.out.println("=== 1. 基本try-catch ===");
        tryCatchBasic();

        System.out.println("\n=== 2. 多种异常捕获 ===");
        mutipleCatch();

        System.out.println("\n=== 3. finally执行顺序 ===");
        finallyTest();

        System.out.println("\n=== 4. return与finally ===");
        System.out.println("testReturn结果：" + testReturn());

        System.out.println("\n=== 5. throws声明 ===");
        try {
            readFile("不存在的文件.txt");
        } catch (IOException e) {
            System.out.println("读取失败：" + e.getMessage());
        }

        System.out.println("\n=== 6. throw主动抛出 ===");
        try {
            divide(10, 0);
        } catch (ArithmeticException e) {
            System.out.println("计算错误：" + e.getMessage());
        }

        System.out.println("\n=== 7. 异常信息获取 ===");
        printException();
//        StringPractice stringPractice = new StringPractice();
//        caculate caculate=new caculate();
//        caculate.caculate(args);
//        scores scores=new scores();
//        scores.score(args);
//      冒泡排序
//        int [] scores= {66,89,95, 345};
//        BubbleSort.sort(scores);

//        BinarySearch bs=new BinarySearch();
//        int index=bs.Search(scores,95);
////
//        System.out.println(index);
//        Fibonaci fibonaci = new Fibonaci();
//         int result=fibonaci.fibonacci(3);
//        System.out.println(result);
//        //面向对象
//        Student student1=new Student();
//        student1.name="guagua";
//        student1.age=23;
//        student1.introduce();
////        使用无参构造
//        Student student2=new Student();
//        student2.setName("zhangsan");
//        student2.setAge(23);
//        student2.setScore(120);
//        System.out.println("是否优秀"+student2.isExcellent());
//        使用有参构造

//        Student student3=new Student("feifei",23,"北京",134);
//        student3.introduce();
//        System.out.println("是否优秀"+student3.isExcellent());
//        Student student4=student3;
//        student4.setName("baibai");
//        System.out.println(student3.name);
//        System.out.println(student3);
//        student4.setAge(200);
//        prinrtIntroduce(new Person());
//        prinrtIntroduce(new Teacher());

//        Animal animal = new Dog("旺财");
//        Animal animal1=new Cat("咪咪");
//        Animal animal2=new Bird("小燕子");
//        animal1.speak();
//        animal2.speak();
//        animal.speak();
//        animal1.eat();
//        animal2.eat();
//        makeAnimalsSpeak(animal);
//        makeAnimalsSpeak(animal1);
//        makeAnimalsSpeak(animal2);
//        Animal animal3=new Dog("来福");
//        Animal animal4=new Cat("小花");
//        if (animal3 instanceof Dog){
//            Dog dog=(Dog)animal3;
//            dog.speak();
//        }
//        if (animal4 instanceof Dog){
//            Dog dog1=(Dog)animal3;
//            dog1.speak();
//        }
//    }
//        //两数之和
        Solution solution=new Solution();
//        int[] nums=new int[]{2,7,11,15,56,99,12};
//        int target=127;
//        int[] result = solution.twoSum(nums, target);
//        System.out.println(result[0]+" "+result[1]);

//    public static void prinrtIntroduce(Person p){
//        p.introduce("zhaddd");

//        回文数
//       boolean result= solution.isPalindrome(0);
//       System.out.println(result);
//       String s= "LVIII";
//       int total=solution.romanToInt(s);
//       System.out.println(total);

    }



    public static void makeAnimalsSpeak(Animal animal){
        System.out.println(animal.getName()+"说");
        animal.speak();
    }
    }
