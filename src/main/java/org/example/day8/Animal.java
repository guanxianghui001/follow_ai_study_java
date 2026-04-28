package org.example.day8;

public class Animal {
    private String name ="animal";
    public Animal() {
        System.out.println("Animal constructor");
    }
    public Animal(String name) {
        this.name = name;
        System.out.println("Animal's constructor"+name);
    }
    public void speak(){
        System.out.println(name +"Animal speak");
    }

    public String getName() {
        return name;
    }

    public void eat(){
        System.out.println(name +"Animal eat");


    }
    public void showname(){
        System.out.println(name +"Animal showname");
    }
}

