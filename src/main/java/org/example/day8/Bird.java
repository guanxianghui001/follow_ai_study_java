package org.example.day8;

public class Bird extends Animal{
    public String name ="Bird";
    public Bird() {
        System.out.println("Bird constructor");
    }
    public Bird(String name) {
        this.name = name;
        System.out.println("Bird's constructor"+name);
    }
    @Override
    public void eat() { 
        System.out.println(name +"Bird eat");
    }
    @Override
    public void speak() {
        System.out.println(name +"Bird 叫渣渣");
    }
    
}
