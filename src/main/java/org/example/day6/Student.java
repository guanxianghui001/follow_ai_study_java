package org.example.day6;

public class Student {
    public String name;
    public int age;
    String address;
    double score;
    public Student(){
        System.out.println("Student constructor 无参构造器");
    }
    public Student(String name){
        this.name = name;
        System.out.println("部分参数构造器"+name);
    }
    public Student(String name, int age, String address, double score) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.score = score;
        System.out.println("创建有参构造器"+name+age+address+score);
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public int getAge(){
        return age;
    }
    public void setAge(int age){
        if(age>=0&&age<=130){
            this.age = age;

        }
        else if(age>=130){
            System.out.println("年龄输入不合理");
        }
    }
    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public double getScore(){
        return score;
    }
    public void setScore(double score){
        if (score>0 && score<=150){
            this.score = score;
        }else {
            System.out.println("成绩必须在0-150之间");
        }
    }

    public void study(String subject){
        System.out.println(name+"正在学习"+subject);
    }
    public void introduce(){
        System.out.println("我叫"+name+",今年"+age+"了");
    }
    public boolean isExcellent(){
        return score>90;
    }
    @Override
    public String toString(){
        return "Student{name='"+name+"',age="+age+",address='"+address+"',score="+score+"}";
    }
}
