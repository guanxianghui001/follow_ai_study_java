package org.example.day7;

public class Person
{
    private String name;
    private int age;


    public void person(String name,int age){
        this.name=name;
        this.age=age;
    }
    public void show(){
        System.out.println("name:"+name+"age:"+age);
    }
    public void introduce(String name){

        System.out.println("name111"+name);
    }

}
