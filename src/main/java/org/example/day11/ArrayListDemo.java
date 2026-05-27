package org.example.day11;
import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayListDemo {
    public static void getArray1(){
        System.out.println("=====1、创建与添加===");
        List<String> fruits =new ArrayList<>();
        fruits.add("apple");
        fruits.add("orange");
        fruits.add("葡萄");
        System.out.println("初始化列表："+fruits);
        System.out.println("初始化列表大小："+fruits.size());
        fruits.add(1,"appleOne");
        System.out.println("插入后数据："+fruits);
        System.out.println("索引2元素为："+fruits.get(2));
        fruits.set(2,"appleTwo");
        System.out.println("修改后"+fruits);
        System.out.println("包含orange？"+fruits.contains("orange"));
        System.out.println("appleOne的位置？"+fruits.indexOf("appleOne"));
        System.out.println("===删除操作====");
        fruits.remove("appleTwo");
        System.out.println("删除数据后数据展示"+fruits);
        fruits.remove(0);
        System.out.println("删除0之后的数据"+fruits);

        System.out.println("=====遍历方式======");
        System.out.println("索引遍历");
        for (int i = 0; i < fruits.size(); i++) {
            System.out.println(fruits.get(i));
        }

        for (String fruit : fruits) {
            System.out.println(fruit+" ");
        }

        System.out.println("lambda遍历");
        fruits.forEach(System.out::println);
        System.out.println("\n=== 5. 批量操作 ===");
        List<String> moreFruits = Arrays.asList("西瓜", "草莓", "蓝莓");
        fruits.addAll(moreFruits);
        System.out.println("批量添加后：" + fruits);
        fruits.clear();
        System.out.println("清空后大小：" + fruits.size() + "，是否为空：" + fruits.isEmpty());
        System.out.println("\n=== 6. 指定初始容量（性能优化）===");
        // 已知要存10000个元素，避免扩容开销
        List<Integer> numbers = new ArrayList<>(10000);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            numbers.add(i);
        }
        long end = System.currentTimeMillis();
        System.out.println("指定容量添加10万个元素耗时：" + (end - start) + "ms");

        // 对比：不指定容量
        List<Integer> numbers2 = new ArrayList<>();
        start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            numbers2.add(i);
        }
        end = System.currentTimeMillis();
        System.out.println("默认容量添加10万个元素耗时：" + (end - start) + "ms");

        System.out.println("\n=== 7. 泛型类型安全 ===");
        List<String> strings = new ArrayList<>();
        strings.add("Hello");
        // strings.add(123);  // 编译错误！类型不匹配
        String first = strings.get(0);  // 不需要强转
        System.out.println("泛型确保类型安全：" + first);

        System.out.println("\n=== 8. 集合转数组 ===");
        List<String> list = Arrays.asList("a", "b", "c");
        String[] arr = list.toArray(new String[0]);
        System.out.println("数组输出"+arr[0]);
        System.out.println("数组：" + Arrays.toString(arr));
}



}


