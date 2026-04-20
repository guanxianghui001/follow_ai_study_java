package org.example;

public class scores {
    public void score(String[] args)
    {
        int [] scores= {66,89,76,89,100};
//        for (int i=0;i<scores.length;i++){
//            System.out.println("第"+(i+1)+"学生成绩为"+scores[i]);
//        }
        double sum=0;
        double max=scores[0];
        double min= scores[0];
        for (int i=0;i<scores.length;i++){
            sum +=scores[i];
            if (scores[i]>max){
                max=scores[i];

            }
            if (scores[i]<min){
                min=scores[i];

            }

        }
        System.out.println(sum);
        double mean=sum/scores.length;
        System.out.println(mean);
        System.out.println("最大值"+max);
        System.out.println("最小值"+min);
    }
}
