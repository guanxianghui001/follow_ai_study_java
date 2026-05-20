package org.example.day9;

public class StringPractice {
    public StringPractice(){
        StringBuilder sb1=new StringBuilder();
        sb1.append("Hello");
        sb1.append(" ").append("World");
        System.out.println(sb1.toString());
        sb1.insert(5,",");
        sb1.replace(6,11,"java");
        System.out.println(sb1.toString());
        sb1.delete(5,6);
        sb1.reverse();
        System.out.println(sb1.toString());
        Integer a=100;
        Integer b=100;
        System.out.println(a==b);
        Integer c=a+b;
        Integer d=200;
        System.out.println(c==d);
        System.out.println(c.equals(d));
//        字符串转数字
        int num=Integer.parseInt("2341");
        System.out.println(num);
        double num1=Double.parseDouble("2315");
        System.out.println(num1);
        Integer.compare(3,5);
        Integer.compare(5,3);
        System.out.println(Integer.compare(3,5));
        System.out.println(Integer.compare(5,3));
        System.out.println(Integer.max(3,5));
        System.out.println(Integer.min(3,5));
        String s1="java";
        String s2="java";
        System.out.println(s1==s2);
        String s3=new String("java");
        String s4=new String("java").intern();
        System.out.println(s3==s4);
        System.out.println(s1==s4);
        System.out.println(s1==s3);
        System.out.println(s1.toUpperCase());
        String s5="apple,banana,orange";
        String[] parts=s5.split(",");
        for (String part:parts){
            System.out.println(part);

        }
        Long start=System.currentTimeMillis();
        StringBuilder good=new StringBuilder();
        for (int i=0;i<10000;i++){
            good.append(i);
//            System.out.println(good);
        }
        Long end=System.currentTimeMillis();
        System.out.println("StringBuilder拼接10000次耗时:  "+(end-start));
        String query=buildQuery("user",new String[]{"id","name","age"},"age>18");
        System.out.println(query);
    }

    private String buildQuery(String table, String[] columns, String where) {
        StringBuilder query=new StringBuilder("SELECT ");
        for (int i=0;i<columns.length;i++){
            query.append(columns[i]);
            if (i<columns.length-1){
                query.append(",");
            }


            }
        query.append(" FROM ").append(table);
        if (where !=null && !where.isEmpty()){
            query.append(" WHERE ").append(where);
        }
        return query.toString();
    }

}
