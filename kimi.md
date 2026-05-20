
📖 明日预习（Day 11）
明天学习常用API：String、StringBuilder与包装类，这是日常开发最常用的类：
预习思考：
String是不可变的，这意味着什么？str = str + "a"发生了什么？
StringBuilder和StringBuffer有什么区别？为什么拼接字符串要用它们？
int和Integer有什么区别？==和equals比较它们时结果一样吗？
自动装箱和拆箱是什么？有什么陷阱？
今晚作业：尝试用+拼接10000次字符串，观察运行时间；再用StringBuilder做同样的事，对比效率。
明天见！
第11天教程
编辑
复制
分享
包管理 String StringB
今天是 Day 11（5月9日 周五），学习包管理和常用API核心类——String、StringBuilder与包装类。这些是你日常开发最频繁使用的类，也是面试高频考点。
📚 Day 11：包管理、String、StringBuilder与包装类
🎯 今日目标
理解包的作用和导入机制，深入理解String的不可变性和常量池，掌握StringBuilder的高效拼接，理解自动装箱/拆箱及缓存陷阱。
预计学习时间：2小时
今日产出物：StringDemo.java + StringBuilderDemo.java + WrapperDemo.java
1️⃣ 包管理（Package）（15分钟）
为什么需要包？
场景：你写了一个User类，同事也写了一个User类，怎么区分？
解决：用包（package）组织类，类似文件夹管理文件。
语法
java
复制
// 1. 声明包（必须是文件第一行，注释除外）
package com.yourname.project.model;

// 2. 导入其他包的类
import java.util.Scanner;        // 导入单个类
import java.util.*;              // 导入包下所有类（不推荐，影响编译速度）
import java.math.BigDecimal;     // 导入大数运算类

// 3. 静态导入（JDK 5+，导入类的静态成员）
import static java.lang.Math.PI;
import static java.lang.Math.pow;

public class User {
Scanner scanner = new Scanner(System.in);  // 使用导入的类
double result = pow(2, 3);  // 静态导入后可直接使用
}
Java常用包
表格
包名	作用	是否自动导入
java.lang	核心类（String、System、Math、Object等）	✓ 自动导入
java.util	工具类（集合、日期、随机数、Scanner等）	✗ 需手动导入
java.io	输入输出流（文件读写）	✗ 需手动导入
java.math	高精度计算（BigDecimal、BigInteger）	✗ 需手动导入
java.time	新日期时间API（JDK 8+）	✗ 需手动导入
2️⃣ String类（重点）（40分钟）
String的不可变性（Immutability）
核心特性：String对象一旦创建，内容不可改变。任何"修改"操作都会创建新对象。
java
复制
String s = "Hello";
s = s + " World";  // 看起来修改了s，实际创建了新的String对象！
// 此时内存中有两个对象："Hello"（被丢弃）和"Hello World"
为什么不可变？
String类被final修饰（不能被继承）
内部字符数组被final修饰（引用不能变）
字符数组是private的，外部无法直接修改
字符串常量池（String Pool）
概念：JVM为了节省内存，对字符串字面量进行缓存复用。
java
复制
String s1 = "Java";           // 字面量：放入常量池
String s2 = "Java";           // 常量池已有，直接复用
String s3 = new String("Java"); // new：在堆中创建新对象

System.out.println(s1 == s2);  // true！同一常量池对象
System.out.println(s1 == s3);  // false！堆中对象地址不同
内存示意图：
plain
复制
栈内存              堆内存              方法区（常量池）
+------+          +--------+          +--------+
| s1   |--------->| "Java" |<---------| "Java" |
| s2   |----------| (引用) |          | (常量) |
+------+          +--------+          +--------+
| s3   |--------->| "Java" |  （new创建的新对象）
+------+          | (新对象)|
+--------+
== vs equals（面试必考）
表格
比较方式	比较内容	适用场景
==	内存地址	基本类型值比较、判断同一对象
equals()	内容	字符串内容比较
java
复制
String a = new String("Hi");
String b = new String("Hi");

System.out.println(a == b);      // false，不同对象
System.out.println(a.equals(b)); // true，内容相同

// 开发中比较字符串内容，永远用equals！
if ("Java".equals(str)) {  // 推荐：常量放前面，防止NPE
// ...
}
intern()方法
手动将字符串放入常量池：
java
复制
String s1 = new String("Hello").intern();  // 强制入池
String s2 = "Hello";
System.out.println(s1 == s2);  // true
String常用方法（必须掌握）
java
复制
String str = "  Hello Java  ";

str.length();              // 13（字符个数）
str.charAt(0);             // ' '（空格）
str.trim();                // "Hello Java"（去首尾空格）
str.toUpperCase();         // "  HELLO JAVA  "
str.toLowerCase();         // "  hello java  "

str.contains("Java");      // true
str.startsWith("  H");     // true
str.endsWith("va  ");      // true
str.indexOf("Java");       // 8（首次出现位置，-1表示不存在）
str.lastIndexOf("a");      // 11（最后出现位置）

str.substring(2, 7);       // "Hello"（含头不含尾：[2,7)）
str.replace("Java", "World"); // "  Hello World  "

String[] parts = "a,b,c".split(",");  // ["a", "b", "c"]

String.join("-", "2026", "05", "09");  // "2026-05-09"
3️⃣ StringBuilder与StringBuffer（20分钟）
为什么需要它们？
String拼接的性能陷阱：
java
复制
String result = "";
for (int i = 0; i < 10000; i++) {
result = result + i;  // 每次循环都创建新对象！慢！
}
// 循环10000次，创建了10000个临时String对象
StringBuilder原理：内部是可变字符数组，不创建新对象，直接修改内容。
对比
表格
特性	String	StringBuilder	StringBuffer
可变性	不可变	可变	可变
线程安全	安全（只读）	不安全	安全（synchronized）
性能	拼接慢	最快	较快（有同步开销）
使用场景	字符串常量、少量操作	单线程拼接	多线程拼接
语法
java
复制
StringBuilder sb = new StringBuilder();
sb.append("Hello");       // 追加
sb.append(" ").append("World");  // 链式调用

sb.insert(5, ",");        // 在索引5插入","
sb.replace(6, 11, "Java"); // 替换[6,11)的内容
sb.delete(5, 6);          // 删除索引5的字符
sb.reverse();             // 反转

String result = sb.toString();  // 转回String
开发规范：在循环或大量拼接时，必须用StringBuilder。
4️⃣ 包装类（Wrapper Class）（25分钟）
为什么需要包装类？
Java是面向对象语言，但基本类型不是对象。集合（如ArrayList）只能存对象，不能存int。
表格
基本类型	包装类
byte	Byte
short	Short
int	Integer
long	Long
float	Float
double	Double
char	Character
boolean	Boolean
自动装箱与拆箱（JDK 5+）
java
复制
// 自动装箱：基本类型 → 包装类
Integer num = 100;  // 编译器自动转为：Integer.valueOf(100)

// 自动拆箱：包装类 → 基本类型
int n = num;        // 编译器自动转为：num.intValue()
Integer缓存陷阱（面试高频！）
java
复制
Integer a = 100;
Integer b = 100;
System.out.println(a == b);  // true！缓存命中

Integer c = 200;
Integer d = 200;
System.out.println(c == d);  // false！超出缓存范围，创建了不同对象
原因：Integer.valueOf()对-128 ~ 127之间的值有缓存（IntegerCache），超出范围会new Integer()。
正确做法：包装类比较永远用equals！
java
复制
Integer x = 200;
Integer y = 200;
System.out.println(x.equals(y));  // true，内容相同
包装类常用方法
java
复制
// 字符串转数字
int num = Integer.parseInt("123");      // 123
double d = Double.parseDouble("3.14");  // 3.14

// 数字转字符串
String s = String.valueOf(123);         // "123"
String s2 = Integer.toString(123);      // "123"

// 比较
Integer.compare(10, 20);   // -1（小于）
Integer.max(10, 20);       // 20
Integer.min(10, 20);       // 10
💻 今日代码实战（必须完成）
任务一：String深度测试（StringDemo.java）
java
复制
/**
* String类深度测试：不可变性、常量池、== vs equals
  */
  public class StringDemo {

  public static void main(String[] args) {
  System.out.println("=== 1. 常量池验证 ===");
  String s1 = "Java";
  String s2 = "Java";
  String s3 = new String("Java");
  String s4 = new String("Java").intern();

       System.out.println("s1 == s2: " + (s1 == s2));        // true（常量池复用）
       System.out.println("s1 == s3: " + (s1 == s3));        // false（堆中新对象）
       System.out.println("s1 == s4: " + (s1 == s4));        // true（intern入池）
       System.out.println("s1.equals(s3): " + s1.equals(s3)); // true（内容相同）
       
       System.out.println("\n=== 2. 不可变性验证 ===");
       String str = "Hello";
       String str2 = str;
       str = str + " World";  // 创建新对象，str指向新地址
       System.out.println("str: " + str);     // Hello World
       System.out.println("str2: " + str2);   // Hello（没变！）
       
       System.out.println("\n=== 3. 常用方法 ===");
       String text = "  Hello Java Programming  ";
       System.out.println("原始: '" + text + "'");
       System.out.println("trim: '" + text.trim() + "'");
       System.out.println("长度: " + text.length());
       System.out.println("包含'Java': " + text.contains("Java"));
       System.out.println("转大写: " + text.toUpperCase().trim());
       System.out.println("替换: " + text.trim().replace("Java", "Python"));
       System.out.println("子串: " + text.trim().substring(6, 10));
       
       String[] parts = "apple,banana,orange".split(",");
       System.out.println("分割后: ");
       for (String p : parts) {
           System.out.println("  " + p);
       }
       
       System.out.println("\n=== 4. 拼接陷阱 ===");
       // 错误示范：循环中用+拼接
       long start1 = System.currentTimeMillis();
       String bad = "";
       for (int i = 0; i < 10000; i++) {
           bad += i;
       }
       long end1 = System.currentTimeMillis();
       System.out.println("String拼接10000次耗时: " + (end1 - start1) + "ms");
       
       // 正确示范：用StringBuilder
       long start2 = System.currentTimeMillis();
       StringBuilder good = new StringBuilder();
       for (int i = 0; i < 10000; i++) {
           good.append(i);
       }
       long end2 = System.currentTimeMillis();
       System.out.println("StringBuilder拼接10000次耗时: " + (end2 - start2) + "ms");
  }
  }
  任务二：StringBuilder练习（StringBuilderDemo.java）
  java
  复制
  /**
* StringBuilder常用操作
  */
  public class StringBuilderDemo {

  public static void main(String[] args) {
  System.out.println("=== 1. 基础拼接 ===");
  StringBuilder sb = new StringBuilder();
  sb.append("Java")
  .append(" ")
  .append("Programming");
  System.out.println(sb.toString());

       System.out.println("\n=== 2. 插入与替换 ===");
       sb.insert(4, " Language");  // 在索引4插入
       System.out.println(sb);
       
       sb.replace(0, 4, "Python");  // [0,4)替换
       System.out.println(sb);
       
       System.out.println("\n=== 3. 反转 ===");
       StringBuilder rev = new StringBuilder("Hello");
       System.out.println(rev.reverse());  // olleH
       
       System.out.println("\n=== 4. 删除 ===");
       StringBuilder del = new StringBuilder("Hello World");
       del.delete(5, 11);  // 删除[5,11)，即" World"
       System.out.println(del);
       
       System.out.println("\n=== 5. 链式构建SQL（实战场景）===");
       String query = buildQuery("users", new String[]{"id", "name", "age"}, "age > 18");
       System.out.println(query);
  }

  // 实战：动态构建SQL查询
  public static String buildQuery(String table, String[] columns, String where) {
  StringBuilder sql = new StringBuilder("SELECT ");

       for (int i = 0; i < columns.length; i++) {
           sql.append(columns[i]);
           if (i < columns.length - 1) {
               sql.append(", ");
           }
       }
       
       sql.append(" FROM ").append(table);
       
       if (where != null && !where.isEmpty()) {
           sql.append(" WHERE ").append(where);
       }
       
       return sql.toString();
  }
  }
  任务三：包装类陷阱测试（WrapperDemo.java）
  java
  复制
  /**
* 包装类测试：自动装箱拆箱、缓存陷阱
  */
  public class WrapperDemo {

  public static void main(String[] args) {
  System.out.println("=== 1. 自动装箱与拆箱 ===");
  Integer num = 100;      // 自动装箱
  int n = num;            // 自动拆箱
  System.out.println(num + " + " + n + " = " + (num + n));

       System.out.println("\n=== 2. 缓存陷阱（面试重点）===");
       Integer a = 100;
       Integer b = 100;
       System.out.println("100 == 100: " + (a == b));  // true（缓存内）
       
       Integer c = 200;
       Integer d = 200;
       System.out.println("200 == 200: " + (c == d));  // false（缓存外）
       
       Integer e = -128;
       Integer f = -128;
       System.out.println("-128 == -128: " + (e == f)); // true（缓存边界）
       
       System.out.println("\n=== 3. 正确比较方式 ===");
       System.out.println("200 equals 200: " + c.equals(d));  // true
       
       System.out.println("\n=== 4. 类型转换 ===");
       String str = "12345";
       int numFromStr = Integer.parseInt(str);
       System.out.println("字符串转整数: " + numFromStr);
       
       double dnum = Double.parseDouble("3.14159");
       System.out.println("字符串转小数: " + dnum);
       
       // 异常输入
       try {
           Integer.parseInt("abc");  // 会抛异常！
       } catch (NumberFormatException ex) {
           System.out.println("转换失败: " + ex.getMessage());
       }
       
       System.out.println("\n=== 5. 基本类型 vs 包装类选择 ===");
       // 基本类型：性能高，不能存null，集合不支持
       int primitive = 0;
       
       // 包装类：可以存null，集合支持，有丰富方法
       Integer wrapper = null;  // 可以表示"无值"
       
       // 三目运算符陷阱（自动拆箱NPE）
       Integer x = null;
       // int result = true ? x : 0;  // 运行时报NullPointerException！
       // 因为三目运算符会统一类型，自动拆箱x导致NPE
  }
  }
  ✅ 今日任务清单
  必须完成：
  [ ] 理解包的作用，掌握package声明和import导入语法
  [ ] 重点理解：String的不可变性，任何修改操作都创建新对象
  [ ] 重点理解：字符串常量池机制，字面量复用 vs new创建新对象
  [ ] 掌握==和equals的区别，字符串比较永远用equals
  [ ] 掌握StringBuilder的使用，理解为什么在循环拼接中必须用StringBuilder
  [ ] 理解自动装箱和拆箱，掌握8种基本类型的包装类
  [ ] 重点记忆：Integer缓存范围是-128 ~ 127，超出范围==比较为false
  [ ] 编写并运行StringDemo.java，观察常量池和拼接性能差异
  [ ] 编写并运行StringBuilderDemo.java，练习链式操作
  [ ] 编写并运行WrapperDemo.java，验证缓存陷阱
  [ ] 今日代码提交到Git（提交信息："Day11: 包管理、String、StringBuilder与包装类"）
  重点验证：
  [ ] 修改StringDemo中s1的值，观察s2是否变化（应该不变，因为不可变）
  [ ] 将循环次数改为100000，观察String和StringBuilder耗时差距（可能差几百倍）
  [ ] 测试Integer缓存边界：127 == 127为true，128 == 128为false
  [ ] 尝试Integer x = null; int y = x;，观察NullPointerException
  🐛 常见错误提醒
  字符串比较用==："a" == "b"永远比较地址，内容比较必须用equals
  循环拼接用String：每次+都创建新对象，性能极差，必须用StringBuilder
  Integer缓存陷阱：超出-128~127范围，==返回false，必须用equals
  自动拆箱NPE：包装类为null时自动拆箱会抛NullPointerException
  split正则特殊字符："a.b.c".split(".")结果不对，因为.是正则元字符，需要split("\\.")
  substring索引越界：substring(0, 100)如果字符串不够长会报StringIndexOutOfBoundsException
  📖 明日预习（Day 12）
  明天学习异常处理（Exception）：
  预习思考：
  try-catch-finally的执行顺序是什么？如果catch里有return，finally还会执行吗？
  受检异常（Checked）和运行时异常（Runtime）有什么区别？
  throw和throws有什么区别？
  如何自定义异常？什么时候需要自定义？
  今晚作业：尝试运行int result = 10 / 0;，观察报错信息，思考如何优雅处理这个错误。
  明天见！
  第12天学习
  编辑
  复制
  分享
  Java异常处理完整教程详解
  今天是 Day 12（5月12日 周一），学习异常处理（Exception）。异常机制是Java保证程序健壮性的核心特性，也是日常开发和面试的高频考点。
  📚 Day 12：异常处理、异常体系与自定义异常
  🎯 今日目标
  理解Java异常体系结构，掌握try-catch-finally的用法和执行顺序，区分受检异常和运行时异常，掌握throw和throws的区别，能够自定义异常并合理使用。
  预计学习时间：2小时
  今日产出物：ExceptionDemo.java + CustomExceptionDemo.java
  1️⃣ 为什么需要异常处理？（10分钟）
  问题场景：没有异常处理的代码
  java
  复制
  public static void main(String[] args) {
  Scanner scanner = new Scanner(System.in);
  System.out.print("请输入被除数：");
  int a = scanner.nextInt();
  System.out.print("请输入除数：");
  int b = scanner.nextInt();

  int result = a / b;  // 如果b=0，程序直接崩溃！
  System.out.println("结果：" + result);

  System.out.println("程序正常结束");  // 如果上面崩溃，这行永远不会执行
  }
  问题：
  用户输入0，程序抛出ArithmeticException，直接终止
  没有任何提示，用户体验极差
  关键资源（如文件、数据库连接）可能没来得及释放
  异常处理的作用
  "将正常业务逻辑与错误处理逻辑分离，保证程序遇到问题时能优雅处理，而不是直接崩溃。"
  2️⃣ Java异常体系（15分钟）
  异常类层次结构
  plain
  复制
  Throwable（所有异常和错误的根类）
  ├── Error（错误）：系统级问题，程序无法处理
  │   ├── OutOfMemoryError（内存溢出）
  │   ├── StackOverflowError（栈溢出）
  │   └── ...
  └── Exception（异常）：程序可以处理的问题
  ├── RuntimeException（运行时异常）：Unchecked，不强制处理
  │   ├── NullPointerException（空指针）
  │   ├── ArrayIndexOutOfBoundsException（数组越界）
  │   ├── ArithmeticException（算术异常，如除零）
  │   ├── NumberFormatException（数字格式错误）
  │   └── ClassCastException（类型转换错误）
  └── 其他Exception（受检异常）：Checked，必须处理
  ├── IOException（IO异常）
  ├── SQLException（数据库异常）
  ├── FileNotFoundException（文件未找到）
  └── ...
  受检异常 vs 运行时异常（面试必考）
  表格
  特性	受检异常（Checked）	运行时异常（Unchecked）
  继承	直接继承Exception	继承RuntimeException
  编译检查	编译器强制要求处理	编译器不检查
  处理要求	必须try-catch或throws	可以不处理
  代表	IOException、SQLException	NullPointerException、IllegalArgumentException
  含义	外部问题，调用者应该预知	编程错误，应该避免
  3️⃣ try-catch-finally（30分钟）
  基本语法
  java
  复制
  try {
  // 可能抛出异常的代码
  int result = 10 / 0;
  } catch (ArithmeticException e) {
  // 异常处理逻辑
  System.out.println("除数不能为0！");
  }
  多种异常处理
  java
  复制
  try {
  // 可能抛出多种异常的代码
  String str = null;
  System.out.println(str.length());  // NullPointerException

  int[] arr = new int[3];
  System.out.println(arr[5]);        // ArrayIndexOutOfBoundsException
  } catch (NullPointerException e) {
  System.out.println("空指针异常：" + e.getMessage());
  } catch (ArrayIndexOutOfBoundsException e) {
  System.out.println("数组越界：" + e.getMessage());
  } catch (Exception e) {  // 父类放最后，捕获所有其他异常
  System.out.println("其他异常：" + e.getMessage());
  }
  注意：多个catch时，子类异常放前面，父类异常放后面。如果反过来，子类catch永远执行不到（编译错误）。
  JDK 7+ 多异常合并
  java
  复制
  try {
  // ...
  } catch (NullPointerException | IllegalArgumentException e) {
  // 两种异常统一处理
  System.out.println("参数错误：" + e.getMessage());
  }
  finally块（面试重点）
  作用：无论是否发生异常，finally中的代码一定会执行。
  java
  复制
  try {
  System.out.println("打开文件...");
  int result = 10 / 0;  // 异常
  System.out.println("这行不会执行");
  } catch (Exception e) {
  System.out.println("捕获异常：" + e.getMessage());
  } finally {
  System.out.println("关闭文件...（一定会执行）");
  }
  // 输出顺序：打开文件 → 捕获异常 → 关闭文件
  ⚠️ 经典面试题：finally和return
  java
  复制
  public static int test() {
  try {
  return 1;  // 先执行到这里，但不会立即返回
  } catch (Exception e) {
  return 2;
  } finally {
  return 3;  // finally中的return会覆盖try中的return！
  }
  }
  // 结果：3
  规则：
  finally在return之前执行
  如果finally中有return，会覆盖try/catch中的return
  如果finally中修改了基本类型变量，不影响try中的return值（因为return的值已确定）
  如果finally中修改了引用类型变量内容，会影响返回结果
  4️⃣ throw 与 throws（20分钟）
  throws：声明方法可能抛出的异常
  java
  复制
  // 方法声明时，用throws告诉调用者：我可能抛这些异常
  public void readFile(String path) throws FileNotFoundException, IOException {
  FileReader reader = new FileReader(path);  // 可能抛FileNotFoundException
  reader.read();                              // 可能抛IOException
  }
  规则：
  方法可以声明多个异常，用逗号分隔
  子类重写方法时，声明的异常不能比父类更宽泛
  throw：主动抛出异常
  java
  复制
  public void withdraw(double amount) {
  if (amount < 0) {
  throw new IllegalArgumentException("取款金额不能为负数");  // 主动抛出
  }
  if (amount > balance) {
  throw new RuntimeException("余额不足");
  }
  this.balance -= amount;
  }
  throw vs throws 对比
  表格
  关键字	位置	作用
  throw	方法内部	创建并抛出一个异常对象
  throws	方法声明	声明方法可能抛出的异常类型
  5️⃣ 自定义异常（20分钟）
  为什么需要自定义异常？
  业务逻辑错误（如"余额不足"、"库存不够"）没有对应的JDK异常
  自定义异常名能清晰表达业务含义
  可以携带额外的业务信息
  自定义异常语法
  java
  复制
  // 1. 继承Exception（受检异常，调用者必须处理）
  public class InsufficientBalanceException extends Exception {
  private double currentBalance;
  private double requiredAmount;

  public InsufficientBalanceException(String message, double current, double required) {
  super(message);
  this.currentBalance = current;
  this.requiredAmount = required;
  }

  // 提供额外信息
  public String getDetail() {
  return "当前余额：" + currentBalance + "，需要：" + requiredAmount
  + "，差额：" + (requiredAmount - currentBalance);
  }
  }

// 2. 继承RuntimeException（运行时异常，不强制处理）
public class InvalidParameterException extends RuntimeException {
public InvalidParameterException(String message) {
super(message);
}
}
💻 今日代码实战（必须完成）
任务一：异常基础测试（ExceptionDemo.java）
java
复制
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
* 异常处理基础演示
  */
  public class ExceptionDemo {

  public static void main(String[] args) {
  System.out.println("=== 1. 基本try-catch ===");
  tryCatchBasic();

 System.out.println("\n=== 2. 多种异常捕获 ===");
 multipleCatch();
 
 System.out.println("\n=== 3. finally执行顺序 ===");
 finallyTest();
 
 System.out.println("\n=== 4. return与finally ===");
 System.out.println("testReturn结果：" + testReturn());
 
 System.out.println("\n=== 5. throws声明 ===");
 try {
     readFile("不存在的文件.txt");
 } catch (IOException e) {
     System.out.println("读取失败：" + e.getMessage());
 }
 
 System.out.println("\n=== 6. throw主动抛出 ===");
 try {
     divide(10, 0);
 } catch (ArithmeticException e) {
     System.out.println("计算错误：" + e.getMessage());
 }
 
 System.out.println("\n=== 7. 异常信息获取 ===");
 printExceptionInfo();
  }

  // 1. 基本try-catch
  public static void tryCatchBasic() {
  Scanner scanner = new Scanner(System.in);
  System.out.print("请输入一个数字：");

       try {
           String input = scanner.nextLine();
           int num = Integer.parseInt(input);  // 可能抛NumberFormatException
           System.out.println("你输入的是：" + num);
       } catch (NumberFormatException e) {
           System.out.println("输入格式错误！请输入有效的整数。");
       }
       
       System.out.println("tryCatchBasic方法正常结束");
  }

  // 2. 多种异常
  public static void multipleCatch() {
  try {
  int[] arr = {1, 2, 3};
  // 测试不同异常，每次放开一行
  // int x = arr[5];           // ArrayIndexOutOfBoundsException
  // String s = null; s.length(); // NullPointerException
  int result = 10 / 0;         // ArithmeticException
  } catch (ArrayIndexOutOfBoundsException e) {
  System.out.println("数组越界：" + e.getMessage());
  } catch (NullPointerException e) {
  System.out.println("空指针：" + e.getMessage());
  } catch (ArithmeticException e) {
  System.out.println("算术错误：" + e.getMessage());
  } catch (Exception e) {
  System.out.println("其他异常：" + e.getClass().getName());
  }
  }

  // 3. finally测试
  public static void finallyTest() {
  try {
  System.out.println("尝试执行...");
  int result = 10 / 2;
  System.out.println("结果：" + result);
  return;  // 有return，finally仍会执行！
  } catch (Exception e) {
  System.out.println("异常：" + e.getMessage());
  } finally {
  System.out.println("finally：清理资源（一定会执行）");
  }
  }

  // 4. return与finally（面试题）
  public static int testReturn() {
  int x = 10;
  try {
  x = 20;
  return x;  // 此时return值已确定为20，但先执行finally
  } finally {
  x = 30;    // 修改x，但不会影响return的值（基本类型）
  System.out.println("finally中x=" + x);
  }
  // 返回20，不是30！
  }

  // 5. throws声明
  public static void readFile(String path) throws IOException {
  FileReader reader = null;
  try {
  reader = new FileReader(path);  // 可能抛FileNotFoundException
  char[] buffer = new char[1024];
  reader.read(buffer);             // 可能抛IOException
  System.out.println(new String(buffer).trim());
  } finally {
  if (reader != null) {
  reader.close();  // 关闭资源
  }
  }
  }

  // 6. throw主动抛出
  public static int divide(int a, int b) {
  if (b == 0) {
  throw new ArithmeticException("除数不能为0（自定义消息）");
  }
  return a / b;
  }

  // 7. 异常信息打印
  public static void printExceptionInfo() {
  try {
  String s = null;
  s.length();
  } catch (NullPointerException e) {
  System.out.println("异常类型：" + e.getClass().getName());
  System.out.println("异常消息：" + e.getMessage());
  System.out.println("异常位置：");
  e.printStackTrace();  // 打印完整堆栈跟踪
  }
  }
  }
  任务二：自定义异常实战（CustomExceptionDemo.java）
  java
  复制
  /**
* 自定义异常实战：银行账户业务异常
  */
  public class CustomExceptionDemo {

  public static void main(String[] args) {
  BankAccount account = new BankAccount("张三", 1000);

       System.out.println("=== 1. 正常取款 ===");
       try {
           account.withdraw(500);
           System.out.println("取款后余额：" + account.getBalance());
       } catch (InsufficientBalanceException e) {
           System.out.println(e.getMessage() + " - " + e.getDetail());
       }
       
       System.out.println("\n=== 2. 余额不足 ===");
       try {
           account.withdraw(800);  // 余额只有500了
       } catch (InsufficientBalanceException e) {
           System.out.println("错误：" + e.getMessage());
           System.out.println("详情：" + e.getDetail());
       }
       
       System.out.println("\n=== 3. 非法参数（运行时异常）===");
       try {
           account.deposit(-100);  // 负数存款
       } catch (InvalidParameterException e) {
           System.out.println("参数错误：" + e.getMessage());
       }
       
       System.out.println("\n=== 4. 转账 ===");
       BankAccount target = new BankAccount("李四", 500);
       try {
           account.transfer(target, 200);
           System.out.println("转账成功");
           System.out.println("张三余额：" + account.getBalance());
           System.out.println("李四余额：" + target.getBalance());
       } catch (InsufficientBalanceException e) {
           System.out.println("转账失败：" + e.getMessage());
       }
  }
  }

// ========== 自定义异常1：余额不足（受检异常） ==========
class InsufficientBalanceException extends Exception {
private double currentBalance;
private double requiredAmount;

    public InsufficientBalanceException(String message, double current, double required) {
        super(message);
        this.currentBalance = current;
        this.requiredAmount = required;
    }
    
    public String getDetail() {
        return String.format("当前余额：%.2f，需要：%.2f，差额：%.2f",
                currentBalance, requiredAmount, requiredAmount - currentBalance);
    }
}

// ========== 自定义异常2：非法参数（运行时异常） ==========
class InvalidParameterException extends RuntimeException {
public InvalidParameterException(String message) {
super(message);
}
}

// ========== 银行账户类 ==========
class BankAccount {
private String owner;
private double balance;

    public BankAccount(String owner, double balance) {
        this.owner = owner;
        this.balance = balance;
    }
    
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new InvalidParameterException("存款金额必须大于0，当前：" + amount);
        }
        this.balance += amount;
        System.out.println(owner + "存款" + amount + "，当前余额：" + balance);
    }
    
    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount <= 0) {
            throw new InvalidParameterException("取款金额必须大于0");
        }
        if (amount > balance) {
            throw new InsufficientBalanceException(
                "余额不足", balance, amount);
        }
        this.balance -= amount;
        System.out.println(owner + "取款" + amount + "，当前余额：" + balance);
    }
    
    public void transfer(BankAccount target, double amount) throws InsufficientBalanceException {
        if (target == null) {
            throw new InvalidParameterException("目标账户不能为空");
        }
        if (target == this) {
            throw new InvalidParameterException("不能转账给自己");
        }
        // 先扣款
        this.withdraw(amount);
        // 再存入
        target.deposit(amount);
    }
    
    public double getBalance() {
        return balance;
    }
}
✅ 今日任务清单
必须完成：
[ ] 理解异常体系：Throwable → Error/Exception → RuntimeException/其他
[ ] 掌握try-catch-finally语法，理解执行顺序
[ ] 重点区分：受检异常（Checked，必须处理）vs 运行时异常（Unchecked，可不处理）
[ ] 掌握throw（方法内抛出异常对象）和throws（方法声明可能抛出的异常）
[ ] 重点理解：finally一定会执行，即使有return；但finally中的return会覆盖前面的return
[ ] 理解自定义异常的意义，能写出继承Exception或RuntimeException的自定义异常
[ ] 编写并运行ExceptionDemo.java，测试各种异常场景
[ ] 编写并运行CustomExceptionDemo.java，体会自定义异常在业务中的价值
[ ] 今日代码提交到Git（提交信息："Day12: 异常处理、异常体系与自定义异常"）
重点验证：
[ ] 在finallyTest中把10/2改成10/0，观察finally是否仍然执行（会执行）
[ ] 在testReturn中把int x改成int[] arr = {10}，在finally中修改arr[0]，观察返回值是否变化（会变化，因为引用类型）
[ ] 删除withdraw方法的throws声明，观察编译错误（因为抛出了受检异常）
[ ] 尝试catch块中子类异常放父类后面，观察编译错误
🐛 常见错误提醒
空catch块：catch(Exception e) {} → 吞掉了异常，程序静默失败，难以调试
异常顺序错误：catch(Exception e)放在catch(NullPointerException e)前面 → 编译错误，子类永远捕获不到
finally中抛异常：如果finally中抛出异常，会覆盖try中的异常
忽略受检异常：方法抛出IOException但不处理也不声明 → 编译错误
滥用运行时异常：所有异常都用RuntimeException → 失去了编译期检查的保护
异常信息不明确：throw new Exception() → 没有消息，难以定位问题
📖 明日预习（Day 13）
明天学习集合框架入门（List、ArrayList）：
预习思考：
数组长度固定，如果想动态增删元素怎么办？
ArrayList和数组有什么区别？它是怎么实现自动扩容的？
List和ArrayList是什么关系？（接口和实现类）
ArrayList是线程安全的吗？
今晚作业：尝试用数组实现一个能自动扩容的动态数组（模拟ArrayList的核心逻辑），体会集合框架存在的意义。
明天见！
13天学习
编辑
复制
分享
今天是 Day 13（5月14日 周四），进入集合框架学习，这是Java最核心的API之一。今天先掌握List接口和ArrayList实现类，理解动态数组的原理。
📚 Day 13：集合框架入门 — List与ArrayList
🎯 今日目标
理解集合框架体系，掌握List接口常用方法，深入理解ArrayList底层原理（数组+扩容），能够熟练使用ArrayList完成增删改查操作。
预计学习时间：2小时
今日产出物：ArrayListDemo.java + MyArrayList.java（手写简化版）
1️⃣ 为什么需要集合？（10分钟）
数组的局限性
表格
数组缺点	集合解决方案
长度固定，创建后不能改变	动态扩容，自动增长
只能存同一种类型（严格）	支持泛型，灵活类型控制
增删元素需要手动移动元素	封装了增删改查方法
没有现成的方法（排序、查找等）	提供丰富的API
集合框架体系（核心接口）
plain
复制
Collection（根接口）
├── List（有序、可重复、有索引）
│   ├── ArrayList（数组实现，查询快）
│   ├── LinkedList（链表实现，增删快）
│   └── Vector（线程安全，已过时）
├── Set（无序、不重复）
│   ├── HashSet（哈希表实现）
│   ├── TreeSet（红黑树排序）
│   └── LinkedHashSet（保持插入顺序）
└── Map（键值对，不属于Collection）
├── HashMap（数组+链表+红黑树）
├── TreeMap（红黑树排序）
└── LinkedHashMap（保持插入顺序）
2️⃣ List接口（15分钟）
List特点
有序：元素按插入顺序排列，有索引
可重复：允许存储重复元素
有索引：可以通过索引访问元素
常用方法
java
复制
List<String> list = new ArrayList<>();

// 增
list.add("Java");           // 末尾添加
list.add(0, "Python");      // 指定位置插入
list.addAll(anotherList);   // 批量添加

// 删
list.remove("Java");        // 删除指定元素（第一个匹配）
list.remove(0);             // 删除指定索引
list.clear();               // 清空

// 改
list.set(0, "Go");          // 修改指定位置

// 查
list.get(0);                // 获取指定位置
list.size();                // 元素个数
list.contains("Java");       // 是否包含
list.indexOf("Java");        // 第一次出现位置
list.isEmpty();              // 是否为空

// 遍历
for (int i = 0; i < list.size(); i++) { ... }     // 索引遍历
for (String s : list) { ... }                      // 增强for
list.forEach(s -> System.out.println(s));          // Lambda（JDK 8+）
3️⃣ ArrayList深度解析（35分钟）
底层结构：Object数组
java
复制
// ArrayList核心源码简化版
public class ArrayList<E> {
private Object[] elementData;  // 存储元素的数组
private int size;                // 实际元素个数（不是数组长度）

    // 默认初始容量
    private static final int DEFAULT_CAPACITY = 10;
    
    // 扩容因子
    private static final double GROWTH_FACTOR = 1.5;
}
扩容机制（面试高频）
触发条件：add元素时，如果size == elementData.length（数组满了），触发扩容。
扩容过程：
创建新数组，长度为原数组的1.5倍（JDK 8+是1.5倍，JDK 7是1.5倍，JDK 6是1.5倍，早期版本是2倍）
用Arrays.copyOf将旧数组元素复制到新数组
用新数组替换旧数组引用
java
复制
// 模拟扩容逻辑
private void grow() {
int oldCapacity = elementData.length;
int newCapacity = oldCapacity + (oldCapacity >> 1);  // 1.5倍（位运算优化）
elementData = Arrays.copyOf(elementData, newCapacity);
}
性能影响：
扩容需要创建新数组 + 复制元素，耗时O(n)
建议：如果知道大概数量，用构造器指定初始容量，避免频繁扩容
java
复制
// 已知要存1000个元素，直接指定容量
List<String> list = new ArrayList<>(1000);  // 避免多次扩容
ArrayList的优缺点
表格
特性	说明
查询	O(1)，直接通过索引访问数组
尾部增删	O(1)，不移动元素
中间增删	O(n)，需要移动后续所有元素
内存	连续存储，有预留空间（可能浪费）
线程安全	不安全，多线程需要外部同步
4️⃣ 泛型（Generics）（20分钟）
为什么需要泛型？
问题：集合默认存Object，取出时需要强转，容易出错。
java
复制
// 没有泛型（JDK 5之前）
List list = new ArrayList();
list.add("Java");
list.add(123);      // 编译通过！但运行时可能出错
String s = (String) list.get(1);  // ClassCastException！
解决：泛型指定集合存储的类型，编译期检查类型安全。
java
复制
// 有泛型
List<String> list = new ArrayList<>();  // 只能存String
list.add("Java");
// list.add(123);  // 编译错误！类型不匹配

String s = list.get(0);  // 不需要强转，直接是String
泛型通配符（了解）
java
复制
// ? extends T：上界通配符，只能读不能写
List<? extends Number> list = new ArrayList<Integer>();
Number n = list.get(0);  // 可以读
// list.add(1);  // 编译错误！不能确定具体类型

// ? super T：下界通配符，只能写不能读（读出来是Object）
List<? super Integer> list2 = new ArrayList<Number>();
list2.add(1);  // 可以写
// Integer i = list2.get(0);  // 编译错误！可能是Number或Object
PECS原则：Producer Extends, Consumer Super（生产者用上界，消费者用下线）
💻 今日代码实战（必须完成）
任务一：ArrayList基础操作（ArrayListDemo.java）
java
复制
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
* ArrayList基础操作与原理演示
  */
  public class ArrayListDemo {

  public static void main(String[] args) {
  System.out.println("=== 1. 创建与添加 ===");
  List<String> fruits = new ArrayList<>();
  fruits.add("苹果");
  fruits.add("香蕉");
  fruits.add("橙子");
  System.out.println("初始列表：" + fruits);
  System.out.println("大小：" + fruits.size());

       fruits.add(1, "葡萄");  // 索引1插入
       System.out.println("插入后：" + fruits);
       
       System.out.println("\n=== 2. 查询与修改 ===");
       System.out.println("索引2的元素：" + fruits.get(2));
       fruits.set(2, "芒果");  // 修改索引2
       System.out.println("修改后：" + fruits);
       System.out.println("包含'苹果'？" + fruits.contains("苹果"));
       System.out.println("'芒果'的位置：" + fruits.indexOf("芒果"));
       
       System.out.println("\n=== 3. 删除 ===");
       fruits.remove("葡萄");       // 按元素删除
       System.out.println("删除葡萄后：" + fruits);
       fruits.remove(0);            // 按索引删除
       System.out.println("删除索引0后：" + fruits);
       
       System.out.println("\n=== 4. 遍历方式 ===");
       // 方式1：索引遍历（可以修改元素）
       System.out.println("索引遍历：");
       for (int i = 0; i < fruits.size(); i++) {
           System.out.println("  [" + i + "] " + fruits.get(i));
       }
       
       // 方式2：增强for（简洁，但不能修改集合结构）
       System.out.println("增强for：");
       for (String fruit : fruits) {
           System.out.println("  " + fruit);
       }
       
       // 方式3：Lambda（JDK 8+）
       System.out.println("Lambda遍历：");
       fruits.forEach(f -> System.out.println("  " + f));
       
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
       System.out.println("数组：" + Arrays.toString(arr));
  }
  }
  任务二：手写简化版ArrayList（MyArrayList.java）
  java
  复制
  import java.util.Arrays;

/**
* 手写简化版ArrayList，理解底层原理
  */
  public class MyArrayList<E> {

  private Object[] elementData;  // 存储元素的数组
  private int size;               // 实际元素个数
  private static final int DEFAULT_CAPACITY = 10;

  // 无参构造
  public MyArrayList() {
  this.elementData = new Object[DEFAULT_CAPACITY];
  }

  // 指定初始容量
  public MyArrayList(int initialCapacity) {
  if (initialCapacity < 0) {
  throw new IllegalArgumentException("容量不能为负数");
  }
  this.elementData = new Object[initialCapacity];
  }

  // 添加元素到末尾
  public boolean add(E e) {
  ensureCapacity(size + 1);  // 确保容量足够
  elementData[size++] = e;
  return true;
  }

  // 指定位置插入
  public void add(int index, E e) {
  checkIndexForAdd(index);
  ensureCapacity(size + 1);
  // 移动后续元素（从index开始到末尾，往后挪一位）
  System.arraycopy(elementData, index, elementData, index + 1, size - index);
  elementData[index] = e;
  size++;
  }

  // 获取指定位置元素
  @SuppressWarnings("unchecked")
  public E get(int index) {
  checkIndex(index);
  return (E) elementData[index];
  }

  // 修改指定位置元素
  @SuppressWarnings("unchecked")
  public E set(int index, E e) {
  checkIndex(index);
  E oldValue = (E) elementData[index];
  elementData[index] = e;
  return oldValue;
  }

  // 删除指定位置元素
  @SuppressWarnings("unchecked")
  public E remove(int index) {
  checkIndex(index);
  E oldValue = (E) elementData[index];
  // 移动后续元素（往前挪一位，覆盖被删除的元素）
  int moveNum = size - index - 1;
  if (moveNum > 0) {
  System.arraycopy(elementData, index + 1, elementData, index, moveNum);
  }
  elementData[--size] = null;  // 末尾置空，帮助GC
  return oldValue;
  }

  // 删除指定元素（第一个匹配）
  public boolean remove(Object o) {
  for (int i = 0; i < size; i++) {
  if (o.equals(elementData[i])) {
  remove(i);
  return true;
  }
  }
  return false;
  }

  // 元素个数
  public int size() {
  return size;
  }

  // 是否为空
  public boolean isEmpty() {
  return size == 0;
  }

  // 清空
  public void clear() {
  for (int i = 0; i < size; i++) {
  elementData[i] = null;
  }
  size = 0;
  }

  // 是否包含
  public boolean contains(Object o) {
  return indexOf(o) >= 0;
  }

  // 查找元素位置
  public int indexOf(Object o) {
  for (int i = 0; i < size; i++) {
  if (o.equals(elementData[i])) {
  return i;
  }
  }
  return -1;
  }

  // 扩容机制（核心！）
  private void ensureCapacity(int minCapacity) {
  if (minCapacity > elementData.length) {
  int oldCapacity = elementData.length;
  int newCapacity = oldCapacity + (oldCapacity >> 1);  // 1.5倍扩容
  if (newCapacity < minCapacity) {
  newCapacity = minCapacity;
  }
  elementData = Arrays.copyOf(elementData, newCapacity);
  System.out.println("【扩容】 " + oldCapacity + " -> " + newCapacity);
  }
  }

  // 索引检查
  private void checkIndex(int index) {
  if (index < 0 || index >= size) {
  throw new IndexOutOfBoundsException("索引越界：" + index + "，当前大小：" + size);
  }
  }

  private void checkIndexForAdd(int index) {
  if (index < 0 || index > size) {
  throw new IndexOutOfBoundsException("索引越界：" + index + "，当前大小：" + size);
  }
  }

  @Override
  public String toString() {
  if (size == 0) return "[]";
  StringBuilder sb = new StringBuilder("[");
  for (int i = 0; i < size; i++) {
  sb.append(elementData[i]);
  if (i < size - 1) sb.append(", ");
  }
  return sb.append("]").toString();
  }

  // 测试
  public static void main(String[] args) {
  System.out.println("=== MyArrayList测试 ===");
  MyArrayList<String> list = new MyArrayList<>(3);  // 小容量，触发扩容

       System.out.println("添加元素：");
       list.add("Java");
       list.add("Python");
       list.add("Go");
       System.out.println(list);
       
       System.out.println("\n触发扩容：");
       list.add("Rust");   // 容量3已满，触发扩容到4（实际JDK是到4，演示用）
       list.add("C++");    // 再次扩容
       System.out.println(list);
       
       System.out.println("\n指定位置插入：");
* list.add(1, "JavaScript");
  System.out.println(list);

        System.out.println("\n查询与修改：");
        System.out.println("索引2：" + list.get(2));
        list.set(2, "TypeScript");
        System.out.println("修改后：" + list);
        
        System.out.println("\n删除：");
        list.remove(0);
        System.out.println("删除索引0后：" + list);
        list.remove("C++");
        System.out.println("删除C++后：" + list);
        
        System.out.println("\n其他方法：");
        System.out.println("大小：" + list.size());
        System.out.println("是否包含Go：" + list.contains("Go"));
        System.out.println("Go的位置：" + list.indexOf("Go"));
        
        list.clear();
        System.out.println("清空后：" + list + "，是否为空：" + list.isEmpty());
  }
  }
  ✅ 今日任务清单
  必须完成：
  [ ] 理解集合框架体系，Collection → List → ArrayList的继承关系
  [ ] 掌握ArrayList常用方法：add/remove/get/set/size/contains/indexOf
  [ ] 重点理解：ArrayList底层是Object[]数组，通过扩容实现动态增长
  [ ] 重点记忆：扩容机制是1.5倍（oldCapacity + (oldCapacity >> 1)），需要数组拷贝
  [ ] 掌握泛型语法List<E>，理解泛型带来的类型安全和免强转好处
  [ ] 理解ArrayList查询快O(1)、中间增删慢O(n)的特点
  [ ] 编写并运行ArrayListDemo.java，测试所有常用方法
  [ ] 编写并运行MyArrayList.java，理解底层数组操作和扩容逻辑
  [ ] 今日代码提交到Git（提交信息："Day13: List与ArrayList，手写简化版ArrayList"）
  重点验证：
  [ ] 创建new ArrayList<>(3)，添加第4个元素，观察扩容（通过MyArrayList的打印）
  [ ] 在遍历过程中删除元素，观察ConcurrentModificationException（明天讲）
  [ ] 比较指定容量和不指定容量的性能差异（时间差可能几十倍）
  [ ] 尝试list.get(100)当size只有3时，观察IndexOutOfBoundsException
  🐛 常见错误提醒
  索引越界：get(5)但size只有3 → IndexOutOfBoundsException
  遍历中修改结构：增强for或迭代器中add/remove → ConcurrentModificationException
  泛型类型不匹配：List<String>但add了Integer → 编译错误
  Arrays.asList的陷阱：返回的List不能add/remove，底层是固定大小数组
  容量误解：new ArrayList<>(100)创建的是空列表，容量是100，但size是0
  对象比较用==：list.remove(obj)实际调用equals，需要确保对象正确重写了equals
  📖 明日预习（Day 14）
  明天学习LinkedList与Set入门：
  预习思考：
  LinkedList和ArrayList有什么区别？各适合什么场景？
  Set和List的核心区别是什么？（有序vs无序，可重复vs不可重复）
  HashSet如何保证元素不重复？（提示：hashCode + equals）
  什么时候用ArrayList，什么时候用LinkedList？
  今晚作业：思考如何用链表结构实现一个List（每个节点存数据和下一个节点的地址），对比数组实现的优缺点。
  明天见！
  第14天学习
  编辑
  复制
  分享
  今天是 Day 14（5月15日 周五），学习LinkedList与Set入门。理解不同数据结构的特性，能够根据场景选择合适的集合类型。
  📚 Day 14：LinkedList、Set与集合选择
  🎯 今日目标
  理解LinkedList的链表结构和适用场景，掌握Set接口的特点和HashSet的基本使用，能够对比ArrayList与LinkedList的性能差异，合理选择集合类型。
  预计学习时间：2小时
  今日产出物：LinkedListDemo.java + SetDemo.java + ListPerformanceTest.java
  1️⃣ LinkedList（30分钟）
  底层结构：双向链表
  plain
  复制
  ArrayList: 数组结构（连续内存）
  [0] [1] [2] [3] [4]  ←  查询快（直接索引），增删慢（需移动元素）

LinkedList: 双向链表（离散内存）
null ← [prev|数据|next] ↔ [prev|数据|next] ↔ [prev|数据|next] → null
↑                                              ↑
head（首节点）                                tail（尾节点）

每个节点：Node { E item; Node<E> next; Node<E> prev; }
LinkedList的特点
表格
操作	时间复杂度	说明
头部/尾部增删	O(1)	直接操作head/tail引用
中间增删	O(n)	需要先遍历到指定位置
查询（按索引）	O(n)	从头或尾遍历到目标位置
查询（按值）	O(n)	遍历整个链表
适用场景：频繁在头部/尾部进行增删操作（如队列、栈的实现）。
独特方法（Deque接口）
LinkedList实现了List和Deque（双端队列）接口，有丰富的方法：
java
复制
LinkedList<String> list = new LinkedList<>();

// 头部操作
list.addFirst("A");      // 头部添加
list.removeFirst();      // 头部删除
list.getFirst();         // 获取头部

// 尾部操作（等效于Queue）
list.addLast("B");       // 尾部添加（等效add）
list.removeLast();       // 尾部删除
list.getLast();          // 获取尾部

// 栈操作（LIFO）
list.push("C");          // 等效addFirst
list.pop();              // 等效removeFirst

// 队列操作（FIFO）
list.offer("D");         // 等效addLast
list.poll();             // 等效removeFirst
2️⃣ Set接口（25分钟）
Set的特点
无序：不保证元素顺序（LinkedHashSet除外）
不重复：基于hashCode()和equals()判断重复
无索引：不能通过位置访问元素
HashSet原理（面试重点）
java
复制
// HashSet底层是HashMap（明天详细讲）
// 只使用Key，Value是一个固定的Object占位
private static final Object PRESENT = new Object();

public boolean add(E e) {
return map.put(e, PRESENT) == null;  // HashMap的put返回旧值
}
去重机制：
计算元素的hashCode()
找到对应的桶（bucket）
如果桶为空，直接放入
如果桶有元素，用equals()比较：
相等：不添加（重复）
不相等：放入链表/红黑树（冲突解决）
⚠️ 重要：存入HashSet的对象必须正确重写hashCode()和equals()！
3️⃣ ArrayList vs LinkedList（20分钟）
表格
对比项	ArrayList	LinkedList
底层结构	动态数组	双向链表
内存占用	连续，有预留空间	离散，每个节点额外开销（两个引用）
随机访问	O(1) ✅	O(n) ❌
尾部增删	O(1)（均摊）	O(1) ✅
头部增删	O(n) ❌	O(1) ✅
中间增删	O(n)	O(n)（但常数更小，不需复制数组）
适用场景	查询多、随机访问多	频繁头部/尾部操作、实现队列/栈
选择原则：
默认用ArrayList：大多数场景查询多于增删，且缓存友好（数组连续存储）
用LinkedList：需要频繁在两端操作，或需要实现队列/栈
💻 今日代码实战（必须完成）
任务一：LinkedList操作（LinkedListDemo.java）
java
复制
import java.util.LinkedList;

/**
* LinkedList操作与特性演示
  */
  public class LinkedListDemo {

  public static void main(String[] args) {
  System.out.println("=== 1. 基础操作 ===");
  LinkedList<String> list = new LinkedList<>();
  list.add("Java");
  list.add("Python");
  list.add("Go");
  System.out.println("初始：" + list);

       // 头部操作
       list.addFirst("C++");    // 头部添加
       System.out.println("头部添加C++：" + list);
       
       // 尾部操作
       list.addLast("Rust");    // 尾部添加
       System.out.println("尾部添加Rust：" + list);
       
       System.out.println("第一个：" + list.getFirst());
       System.out.println("最后一个：" + list.getLast());
       
       System.out.println("\n=== 2. 栈操作（LIFO） ===");
       LinkedList<String> stack = new LinkedList<>();
       stack.push("第一本书");   // push = addFirst
       stack.push("第二本书");
       stack.push("第三本书");
       System.out.println("栈内容：" + stack);
       System.out.println("弹出：" + stack.pop());   // pop = removeFirst
       System.out.println("剩余：" + stack);
       
       System.out.println("\n=== 3. 队列操作（FIFO） ===");
       LinkedList<String> queue = new LinkedList<>();
       queue.offer("顾客A");     // offer = addLast
       queue.offer("顾客B");
       queue.offer("顾客C");
       System.out.println("队列：" + queue);
       System.out.println("服务：" + queue.poll());   // poll = removeFirst
       System.out.println("剩余：" + queue);
       
       System.out.println("\n=== 4. 删除操作 ===");
       list.removeFirst();      // 删头部
       list.removeLast();       // 删尾部
       System.out.println("删除首尾后：" + list);
       
       System.out.println("\n=== 5. 与ArrayList的内存对比 ===");
       // LinkedList每个节点有额外开销（next + prev引用）
       // 适合：频繁两端操作
       // 不适合：大量随机访问
  }
  }
  任务二：Set入门（SetDemo.java）
  java
  复制
  import java.util.HashSet;
  import java.util.LinkedHashSet;
  import java.util.Set;
  import java.util.TreeSet;

/**
* Set接口演示：HashSet、LinkedHashSet、TreeSet
  */
  public class SetDemo {

  public static void main(String[] args) {
  System.out.println("=== 1. HashSet基础 ===");
  Set<String> fruits = new HashSet<>();
  fruits.add("苹果");
  fruits.add("香蕉");
  fruits.add("橙子");
  fruits.add("苹果");        // 重复，不会添加
  System.out.println("HashSet：" + fruits);  // 无序输出
  System.out.println("大小：" + fruits.size());
  System.out.println("包含'香蕉'？" + fruits.contains("香蕉"));

       System.out.println("\n=== 2. HashSet去重原理 ===");
       // 自定义对象去重（必须重写hashCode和equals）
       Set<<Student> students = new HashSet<>();
       students.add(new Student("张三", 20, "001"));
       students.add(new Student("李四", 21, "002"));
       students.add(new Student("张三", 20, "001"));  // 学号相同，应视为重复
       
       System.out.println("学生数量（应去重为2）：" + students.size());
       for (Student s : students) {
           System.out.println("  " + s);
       }
       
       System.out.println("\n=== 3. LinkedHashSet（保持插入顺序） ===");
       Set<String> ordered = new LinkedHashSet<>();
       ordered.add("第三");
       ordered.add("第一");
       ordered.add("第二");
       System.out.println("LinkedHashSet：" + ordered);  // 按插入顺序
       
       System.out.println("\n=== 4. TreeSet（自动排序） ===");
       Set<Integer> sorted = new TreeSet<>();
       sorted.add(30);
       sorted.add(10);
       sorted.add(50);
       sorted.add(20);
       System.out.println("TreeSet（排序）：" + sorted);
       
       System.out.println("\n=== 5. Set遍历 ===");
       // 方式1：增强for
       for (String fruit : fruits) {
           System.out.println("  " + fruit);
       }
       
       // 方式2：Lambda
       fruits.forEach(f -> System.out.println("  Lambda: " + f));
  }
  }

// 必须重写hashCode和equals才能正确去重
class Student {
private String name;
private int age;
private String studentId;  // 用学号判断是否重复

    public Student(String name, int age, String studentId) {
        this.name = name;
        this.age = age;
        this.studentId = studentId;
    }
    
    // 重写equals：学号相同即视为同一学生
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return java.util.Objects.equals(studentId, student.studentId);
    }
    
    // 重写hashCode：与equals逻辑一致
    @Override
    public int hashCode() {
        return java.util.Objects.hash(studentId);
    }
    
    @Override
    public String toString() {
        return "Student{name='" + name + "', age=" + age + ", id='" + studentId + "'}";
    }
}
任务三：性能对比测试（ListPerformanceTest.java）
java
复制
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
* ArrayList vs LinkedList 性能对比
  */
  public class ListPerformanceTest {

  private static final int COUNT = 100000;

  public static void main(String[] args) {
  System.out.println("数据量：" + COUNT);

       testAddTail();
       testAddHead();
       testRandomAccess();
       testRemoveMiddle();
  }

  // 测试尾部添加
  public static void testAddTail() {
  System.out.println("\n=== 尾部添加 ===");

       List<Integer> arrayList = new ArrayList<>();
       long start = System.currentTimeMillis();
       for (int i = 0; i < COUNT; i++) {
           arrayList.add(i);
       }
       long end = System.currentTimeMillis();
       System.out.println("ArrayList: " + (end - start) + "ms");
       
       List<Integer> linkedList = new LinkedList<>();
       start = System.currentTimeMillis();
       for (int i = 0; i < COUNT; i++) {
           linkedList.add(i);
       }
       end = System.currentTimeMillis();
       System.out.println("LinkedList: " + (end - start) + "ms");
  }

  // 测试头部添加（LinkedList优势场景）
  public static void testAddHead() {
  System.out.println("\n=== 头部添加（LinkedList优势） ===");

       List<Integer> arrayList = new ArrayList<>();
       long start = System.currentTimeMillis();
       for (int i = 0; i < COUNT; i++) {
           arrayList.add(0, i);  // 每次都要移动所有元素
       }
       long end = System.currentTimeMillis();
       System.out.println("ArrayList: " + (end - start) + "ms");
       
       List<Integer> linkedList = new LinkedList<>();
       start = System.currentTimeMillis();
       for (int i = 0; i < COUNT; i++) {
           linkedList.add(0, i);  // 只需修改head引用
       }
       end = System.currentTimeMillis();
       System.out.println("LinkedList: " + (end - start) + "ms");
  }

  // 测试随机访问（ArrayList优势场景）
  public static void testRandomAccess() {
  System.out.println("\n=== 随机访问（ArrayList优势） ===");

       List<Integer> arrayList = new ArrayList<>();
       List<Integer> linkedList = new LinkedList<>();
       for (int i = 0; i < COUNT; i++) {
           arrayList.add(i);
           linkedList.add(i);
       }
       
       long start = System.currentTimeMillis();
       for (int i = 0; i < COUNT; i++) {
           arrayList.get(i);
       }
       long end = System.currentTimeMillis();
       System.out.println("ArrayList: " + (end - start) + "ms");
       
       start = System.currentTimeMillis();
       for (int i = 0; i < COUNT; i++) {
           linkedList.get(i);  // 每次都要从头遍历
       }
       end = System.currentTimeMillis();
       System.out.println("LinkedList: " + (end - start) + "ms");
  }

  // 测试中间删除
  public static void testRemoveMiddle() {
  System.out.println("\n=== 中间删除 ===");

       List<Integer> arrayList = new ArrayList<>();
       List<Integer> linkedList = new LinkedList<>();
       for (int i = 0; i < COUNT; i++) {
           arrayList.add(i);
           linkedList.add(i);
       }
       
       long start = System.currentTimeMillis();
       for (int i = COUNT / 2; i > 0; i--) {
           arrayList.remove(i);  // 需要移动后续元素
       }
       long end = System.currentTimeMillis();
       System.out.println("ArrayList: " + (end - start) + "ms");
       
       start = System.currentTimeMillis();
       for (int i = COUNT / 2; i > 0; i--) {
           linkedList.remove(i);  // 只需修改前后节点的引用
       }
       end = System.currentTimeMillis();
       System.out.println("LinkedList: " + (end - start) + "ms");
  }
  }
  ✅ 今日任务清单
  必须完成：
  [ ] 理解LinkedList的双向链表结构，掌握头部/尾部操作方法
  [ ] 理解LinkedList作为栈（push/pop）和队列（offer/poll）的使用
  [ ] 理解Set的核心特点：无序、不重复、无索引
  [ ] 重点理解：HashSet去重依赖hashCode()和equals()，自定义对象必须重写
  [ ] 掌握LinkedHashSet（保序）和TreeSet（排序）的特性
  [ ] 重点对比：ArrayList vs LinkedList的底层结构和性能差异
  [ ] 编写并运行LinkedListDemo.java，测试栈和队列操作
  [ ] 编写并运行SetDemo.java，验证自定义对象的去重（重写hashCode/equals）
  [ ] 编写并运行ListPerformanceTest.java，观察两种List在不同场景的性能差异
  [ ] 今日代码提交到Git（提交信息："Day14: LinkedList、Set与集合性能对比"）
  重点验证：
  [ ] 删除Student的hashCode/equals，观察HashSet中重复学生是否被去重（不会被去重）
  [ ] 在HashSet中添加null，观察是否允许（允许，且只能有一个）
  [ ] 运行性能测试，记录ArrayList和LinkedList在头部添加的差距（可能几十倍）
  [ ] 尝试TreeSet存自定义对象，观察是否需要实现Comparable（会报错，需要实现）
  🐛 常见错误提醒
  Set用索引访问：set.get(0) → 编译错误，Set没有索引
  自定义对象未重写hashCode/equals：导致HashSet无法正确去重
  修改对象后hashCode变化：对象存入Set后修改参与hashCode计算的属性，导致无法找到（禁忌！）
  LinkedList随机访问：用get(i)遍历LinkedList → O(n²)性能灾难，应该用迭代器
  Arrays.asList转Set：new HashSet<>(Arrays.asList(arr))可以，但Arrays.asList返回的List本身不能增删
  📖 明日预习（Day 15）
  明天学习Map与HashMap——最重要的集合之一：
  预习思考：
  Map和Collection有什么关系？（没有继承关系，是独立的顶层接口）
  HashMap的底层结构是什么？（提示：数组+链表+红黑树）
  什么是哈希冲突？HashMap如何解决？
  put和get的时间复杂度是多少？
  HashMap是线程安全的吗？
  今晚作业：思考如何用数组+链表实现一个简单的HashMap（拉链法解决冲突）。
  明天见！
  第15天学习计划
  编辑
  复制
  分享
  今天是 Day 15（5月18日 周一），学习Map与HashMap——Java集合框架中最重要、面试最高频的类。理解HashMap的底层原理是中级Java开发的必备技能。
  📚 Day 15：Map与HashMap深度解析
  🎯 今日目标
  理解Map接口的特点和常用方法，深入掌握HashMap的底层结构（数组+链表+红黑树），理解哈希冲突解决和扩容机制，能够手写简化版HashMap。
  预计学习时间：2.5小时
  今日产出物：HashMapDemo.java + MyHashMap.java（手写简化版）
  1️⃣ Map接口基础（20分钟）
  Map的特点
  键值对（Key-Value）存储，Key唯一，Value可重复
  Key和Value都可以为null（但Key只能有一个null）
  不属于Collection接口，是独立的顶层接口
  常用方法
  java
  复制
  Map<String, Integer> scores = new HashMap<>();

// 增/改
scores.put("张三", 90);        // 添加或覆盖
scores.putIfAbsent("李四", 85); // Key不存在才添加

// 查
scores.get("张三");              // 获取Value，不存在返回null
scores.getOrDefault("王五", 0);  // 不存在返回默认值
scores.containsKey("张三");      // 是否包含Key
scores.containsValue(90);        // 是否包含Value

// 删
scores.remove("张三");           // 按Key删除
scores.remove("张三", 90);      // Key和Value都匹配才删除

// 遍历
scores.keySet();     // 所有Key的Set
scores.values();     // 所有Value的Collection
scores.entrySet();   // 所有键值对的Set（推荐遍历方式）

// 其他
scores.size();       // 键值对数量
scores.isEmpty();    // 是否为空
scores.clear();      // 清空
2️⃣ HashMap底层原理（40分钟）
核心结构：数组 + 链表 + 红黑树（JDK 8+）
plain
复制
数组（Node[] table）：哈希桶数组
索引0:  null
索引1:  Node("张三", 90) → null
索引2:  null
索引3:  Node("李四", 85) → Node("王五", 78) → null  （链表，冲突）
索引4:  TreeNode(...) → TreeNode(...) → ...         （红黑树，冲突多）
...
Node结构：
java
复制
class Node<K, V> {
int hash;      // 哈希值
K key;         // 键
V value;       // 值
Node<K, V> next;  // 下一个节点（链表）
}
put方法执行流程（面试必考）
plain
复制
1. 计算Key的hash值：hash = (h = key.hashCode()) ^ (h >>> 16)
2. 计算索引：index = (n - 1) & hash  （等价于 hash % n，但位运算更快）
3. 检查table[index]：
    - 为空：直接放入新Node
    - 不为空：遍历链表/树
        - Key已存在：覆盖Value
        - Key不存在：追加到链表尾部
4. 检查链表长度：
    - ≥ 8且数组长度 ≥ 64：链表转红黑树
    - 否则保持链表
5. 检查元素数量：
    - size > threshold（容量 × 负载因子0.75）：触发扩容
      扩容机制（resize）
      触发条件：元素数量 > 数组长度 × 0.75（负载因子）
      扩容过程：
      数组长度变为原来的2倍
      所有元素重新计算索引位置（rehash）
      JDK 8优化：如果原索引是i，新索引只可能是i或i+oldCap（不需要重新计算hash）
      核心参数
      表格
      参数	默认值	说明
      初始容量	16	必须是2的幂（便于位运算）
      负载因子	0.75	空间与时间的平衡
      树化阈值	8	链表长度≥8，考虑转红黑树
      反树化阈值	6	树节点数≤6，转回链表
      最小树化容量	64	数组长度<<64时优先扩容，不树化
      3️⃣ hashCode与equals（15分钟）
      为什么必须同时重写？
      HashMap判断Key相等的逻辑：
      先比较hashCode()，不同则认为是不同Key
      hashCode相同，再用equals()确认
      如果只重写equals，不重写hashCode：
      java
      复制
      // 两个内容相同的对象，hashCode不同
      Student s1 = new Student("001", "张三");
      Student s2 = new Student("001", "张三");

map.put(s1, 90);
map.get(s2);  // null！因为s1和s2的hashCode不同，找到不同的桶
正确做法：
java
复制
@Override
public int hashCode() {
return Objects.hash(studentId);  // 用业务唯一标识计算hash
}

@Override
public boolean equals(Object o) {
if (this == o) return true;
if (o == null || getClass() != o.getClass()) return false;
Student student = (Student) o;
return Objects.equals(studentId, student.studentId);
}
💻 今日代码实战（必须完成）
任务一：HashMap基础操作（HashMapDemo.java）
java
复制
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
* HashMap基础操作与特性演示
  */
  public class HashMapDemo {

  public static void main(String[] args) {
  System.out.println("=== 1. 基础操作 ===");
  Map<String, Integer> scores = new HashMap<>();
  scores.put("张三", 90);
  scores.put("李四", 85);
  scores.put("王五", 78);
  System.out.println("初始Map：" + scores);

       // 覆盖
       scores.put("张三", 95);
       System.out.println("覆盖后：" + scores);
       
       // 不存在才添加
       scores.putIfAbsent("李四", 100);  // 不会覆盖
       scores.putIfAbsent("赵六", 88);
       System.out.println("putIfAbsent后：" + scores);
       
       System.out.println("\n=== 2. 查询 ===");
       System.out.println("张三的成绩：" + scores.get("张三"));
       System.out.println("不存在的Key：" + scores.get("不存在"));  // null
       System.out.println("默认值：" + scores.getOrDefault("不存在", 0));
       System.out.println("包含Key'李四'？" + scores.containsKey("李四"));
       
       System.out.println("\n=== 3. 删除 ===");
       scores.remove("王五");
       System.out.println("删除王五后：" + scores);
       scores.remove("李四", 100);  // Value不匹配，不删除
       System.out.println("尝试删除李四(100)后：" + scores);
       
       System.out.println("\n=== 4. 遍历方式（重点） ===");
       // 方式1：KeySet（不推荐，需要二次查找Value）
       System.out.println("KeySet遍历：");
       for (String key : scores.keySet()) {
           System.out.println("  " + key + " = " + scores.get(key));
       }
       
       // 方式2：EntrySet（推荐！一次遍历拿到Key和Value）
       System.out.println("EntrySet遍历：");
       for (Map.Entry<String, Integer> entry : scores.entrySet()) {
           System.out.println("  " + entry.getKey() + " = " + entry.getValue());
       }
       
       // 方式3：Lambda（JDK 8+，最简洁）
       System.out.println("Lambda遍历：");
       scores.forEach((k, v) -> System.out.println("  " + k + " = " + v));
       
       System.out.println("\n=== 5. 自定义对象作为Key ===");
       Map<<Student, String> studentMap = new HashMap<>();
       Student s1 = new Student("001", "张三");
       Student s2 = new Student("002", "李四");
       Student s3 = new Student("001", "张三");  // 学号相同，应视为同一学生
       
       studentMap.put(s1, "一班");
       studentMap.put(s2, "二班");
       studentMap.put(s3, "三班");  // 覆盖s1，因为学号相同
       
       System.out.println("学生Map大小（应为2）：" + studentMap.size());
       studentMap.forEach((k, v) -> System.out.println("  " + k + " -> " + v));
       
       System.out.println("\n=== 6. 统计字符出现次数（经典应用） ===");
       String text = "hello world hello java";
       Map<<Character, Integer> charCount = new HashMap<>();
       for (char c : text.toCharArray()) {
           if (c != ' ') {
               charCount.put(c, charCount.getOrDefault(c, 0) + 1);
           }
       }
       System.out.println("字符统计：" + charCount);
       
       System.out.println("\n=== 7. 扩容观察 ===");
       // 指定初始容量避免扩容
       Map<Integer, String> map = new HashMap<>(100);
       for (int i = 0; i < 100; i++) {
           map.put(i, "value" + i);
       }
       System.out.println("指定容量后添加100个元素，无扩容开销");
  }
  }

class Student {
private String studentId;
private String name;

    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
    }
    
    // 必须重写！
    @Override
    public int hashCode() {
        return java.util.Objects.hash(studentId);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return java.util.Objects.equals(studentId, student.studentId);
    }
    
    @Override
    public String toString() {
        return "Student{id='" + studentId + "', name='" + name + "'}";
    }
}
任务二：手写简化版HashMap（MyHashMap.java）
java
复制
import java.util.ArrayList;
import java.util.List;

/**
* 手写简化版HashMap，理解底层原理
* 只实现核心功能：put、get、remove
  */
  public class MyHashMap<K, V> {

  // 节点类
  static class Node<K, V> {
  final int hash;
  final K key;
  V value;
  Node<K, V> next;

       Node(int hash, K key, V value, Node<K, V> next) {
           this.hash = hash;
           this.key = key;
           this.value = value;
           this.next = next;
       }
  }

  private static final int DEFAULT_CAPACITY = 16;     // 默认容量
  private static final float LOAD_FACTOR = 0.75f;      // 负载因子

  private Node<K, V>[] table;   // 哈希桶数组
  private int size;              // 元素数量
  private int threshold;         // 扩容阈值

  @SuppressWarnings("unchecked")
  public MyHashMap() {
  this.threshold = (int) (DEFAULT_CAPACITY * LOAD_FACTOR);
  }

  // 计算hash（简化版）
  private int hash(Object key) {
  if (key == null) return 0;
  int h = key.hashCode();
  return h ^ (h >>> 16);  // 高16位与低16位异或，减少冲突
  }

  // 计算索引
  private int indexFor(int hash, int length) {
  return hash & (length - 1);  // 等价于 hash % length，但位运算更快
  }

  // 扩容
  @SuppressWarnings("unchecked")
  private void resize() {
  Node<K, V>[] oldTable = table;
  int oldCap = (oldTable == null) ? 0 : oldTable.length;
  int newCap = oldCap == 0 ? DEFAULT_CAPACITY : oldCap << 1;  // 2倍扩容

       System.out.println("【扩容】 " + oldCap + " -> " + newCap);
       
       Node<K, V>[] newTable = (Node<K, V>[]) new Node[newCap];
       table = newTable;
       threshold = (int) (newCap * LOAD_FACTOR);
       
       // 迁移旧数据（简化版：全部重新put）
       if (oldTable != null) {
           for (Node<K, V> node : oldTable) {
               while (node != null) {
                   put(node.key, node.value);  // 重新put会放到新table
                   node = node.next;
               }
           }
       }
  }

  // 添加/修改
  public V put(K key, V value) {
  // 首次put，初始化table
  if (table == null) {
  resize();
  }

       int hash = hash(key);
       int index = indexFor(hash, table.length);
       
       // 遍历该位置的链表
       for (Node<K, V> p = table[index]; p != null; p = p.next) {
           if (p.hash == hash && (p.key == key || key.equals(p.key))) {
               V oldVal = p.value;
               p.value = value;  // Key已存在，覆盖Value
               return oldVal;
           }
       }
       
       // Key不存在，头插法插入新节点（JDK 8是尾插法，这里简化）
       table[index] = new Node<>(hash, key, value, table[index]);
       size++;
       
       // 检查扩容
       if (size > threshold) {
           resize();
       }
       return null;
  }

  // 查询
  public V get(K key) {
  if (table == null) return null;

       int hash = hash(key);
       int index = indexFor(hash, table.length);
       
       for (Node<K, V> p = table[index]; p != null; p = p.next) {
           if (p.hash == hash && (p.key == key || key.equals(p.key))) {
               return p.value;
           }
       }
       return null;
  }

  // 删除
  public V remove(K key) {
  if (table == null) return null;

       int hash = hash(key);
       int index = indexFor(hash, table.length);
       
       Node<K, V> prev = null;
       for (Node<K, V> p = table[index]; p != null; p = p.next) {
           if (p.hash == hash && (p.key == key || key.equals(p.key))) {
               V oldVal = p.value;
               if (prev == null) {
                   table[index] = p.next;  // 删除头节点
               } else {
                   prev.next = p.next;      // 删除中间/尾部节点
               }
               size--;
               return oldVal;
           }
           prev = p;
       }
       return null;
  }

  public int size() {
  return size;
  }

  public boolean isEmpty() {
  return size == 0;
  }

  // 打印内部结构（调试用）
  public void printStructure() {
  System.out.println("=== HashMap结构（大小：" + size + "）===");
  if (table == null) {
  System.out.println("  空表");
  return;
  }
  for (int i = 0; i < table.length; i++) {
  System.out.print("  桶[" + i + "]: ");
  Node<K, V> p = table[i];
  if (p == null) {
  System.out.println("null");
  } else {
  while (p != null) {
  System.out.print("(" + p.key + "=" + p.value + ")");
  if (p.next != null) System.out.print(" -> ");
  p = p.next;
  }
  System.out.println();
  }
  }
  }

  // 测试
  public static void main(String[] args) {
  System.out.println("=== MyHashMap测试 ===");
  MyHashMap<String, Integer> map = new MyHashMap<>();

       System.out.println("\n1. 添加元素：");
       map.put("张三", 90);
       map.put("李四", 85);
       map.put("王五", 78);
       map.printStructure();
       
       System.out.println("\n2. 覆盖：");
       map.put("张三", 95);
       map.printStructure();
       
       System.out.println("\n3. 查询：");
       System.out.println("张三=" + map.get("张三"));
       System.out.println("不存在=" + map.get("不存在"));
       
       System.out.println("\n4. 触发扩容（添加多个元素）：");
       for (int i = 0; i < 20; i++) {
           map.put("key" + i, i);
       }
       System.out.println("总大小：" + map.size());
       map.printStructure();
       
       System.out.println("\n5. 删除：");
       map.remove("李四");
       System.out.println("删除李四后，李四=" + map.get("李四"));
       map.printStructure();
       
       System.out.println("\n6. hash冲突测试：");
       // 构造两个hash相同但equals不同的key（很难构造，这里演示概念）
       MyHashMap<Integer, String> intMap = new MyHashMap<>();
       intMap.put(1, "A");
       intMap.put(17, "B");  // 1和17在容量16时，indexFor结果相同（都是1）
       intMap.printStructure();
  }
  }
  ✅ 今日任务清单
  必须完成：
  [ ] 理解Map的键值对存储模型，掌握put/get/remove/containsKey等核心方法
  [ ] 重点理解：HashMap底层是数组+链表+红黑树，JDK 8优化了链表过长的问题
  [ ] 重点记忆：put流程——计算hash → 计算索引 → 检查冲突 → 覆盖或追加 → 检查扩容
  [ ] 重点记忆：扩容是2倍，负载因子0.75，链表长度≥8且数组长度≥64时转红黑树
  [ ] 理解为什么重写equals必须重写hashCode（HashMap判断Key相等的逻辑）
  [ ] 掌握EntrySet遍历（推荐）和Lambda遍历
  [ ] 编写并运行HashMapDemo.java，测试所有操作和自定义Key
  [ ] 编写并运行MyHashMap.java，理解底层数组、链表、hash计算、扩容逻辑
  [ ] 今日代码提交到Git（提交信息："Day15: Map与HashMap，手写简化版HashMap"）
  重点验证：
  [ ] 删除自定义Student的hashCode/equals，观察HashMap中相同学号学生是否被覆盖（不会被覆盖，变成两个条目）
  [ ] 在MyHashMap中添加大量元素，观察扩容触发时机和打印信息
  [ ] 比较keySet遍历和entrySet遍历的效率差异（entrySet少一次查找，更快）
  [ ] 尝试用null作为Key和Value，观察是否允许（HashMap允许一个null Key，多个null Value）
  🐛 常见错误提醒
  遍历中修改结构：for遍历entrySet时remove → ConcurrentModificationException，应用迭代器的remove
  自定义Key修改后hash变化：Key存入Map后修改参与hashCode的属性 → 再也找不到这个Key（禁忌！）
  HashMap线程不安全：多线程并发put可能导致死循环或数据丢失，多线程用ConcurrentHashMap
  用可变对象作为Key：List、StringBuilder等可变对象不适合做Key
  只重写equals不重写hashCode：导致HashMap行为异常，相同对象映射到不同桶
  容量设置不当：频繁扩容影响性能，应根据预估数据量设置初始容量
  📖 明日预习（Day 16）
  明天学习IO流基础：
  预习思考：
  什么是字节流和字符流？有什么区别？
  InputStream/OutputStream和Reader/Writer的区别？
  FileInputStream和FileReader分别适合读什么文件？
  为什么需要BufferedReader/BufferedWriter？（提示：缓冲减少系统调用）
  今晚作业：尝试用程序读取一个文本文件的内容并打印到控制台，思考需要哪些步骤。
  明天见！
