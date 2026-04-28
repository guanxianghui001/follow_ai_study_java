package org.example.day8;

public class Cat extends Animal{
    public String name ="Cat";
    public Cat() {
        System.out.println("Cat constructor");
            
    }
    public Cat(String name) {
        this.name = name;
        System.out.println("Cat's constructor"+name);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void speak() {
        System.out.println(name +"Cat speak");
    }
}
