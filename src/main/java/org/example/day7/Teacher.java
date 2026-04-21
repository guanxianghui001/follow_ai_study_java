package org.example.day7;

public class Teacher extends Person{
    private String subject;
    private int score;
    public void teacher(String name,int age,String subject){
        super.person(name,age);
        this.subject=subject;
    }
    @Override
    public void show(){
        super.show();
        System.out.println("subject:"+subject);
    }
    public void study(){
        super.show();
        System.out.println("study");
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Teacher teacher = (Teacher) obj;
        return subject.equals(teacher.subject);
    }
@Override
    public void introduce(String name){
        System.out.println("name:"+name);
    }
}
