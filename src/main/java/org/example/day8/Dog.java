package org.example.day8;

public class Dog extends Animal{
    public String name ="Dog";
    public Dog() {
        System.out.println("Dog constructor");
    }
    public Dog(String name) {
        this.name = name;
        System.out.println("Dog's constructor"+name);
    }
    public void eat(){
        System.out.println(name +"Dog eat");
    }
    public void speak(){
        System.out.println(name +"Dog speak");
    }

    @Override
    public String getName() {
        return name;
    }
}
