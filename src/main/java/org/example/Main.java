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


// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
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

        Animal animal = new Dog("旺财");
        Animal animal1=new Cat("咪咪");
        Animal animal2=new Bird("小燕子");
        animal1.speak();
        animal2.speak();
        animal.speak();
        animal1.eat();
        animal2.eat();
        makeAnimalsSpeak(animal);
        makeAnimalsSpeak(animal1);
        makeAnimalsSpeak(animal2);
        Animal animal3=new Dog("来福");
        Animal animal4=new Cat("小花");
        if (animal3 instanceof Dog){
            Dog dog=(Dog)animal3;
            dog.speak();
        }
        if (animal4 instanceof Dog){
            Dog dog1=(Dog)animal3;
            dog1.speak();
        }
    }
//    public static void prinrtIntroduce(Person p){
//        p.introduce("zhaddd");
//    }
    public static void makeAnimalsSpeak(Animal animal){
        System.out.println(animal.getName()+"说");
        animal.speak();
    }
    }
