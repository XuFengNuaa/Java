# Java

## 1.  数据类型：基本类型和引用类型

- 数值类型(初始默认为0)和Boolean类型(默认为false)，其他默认为NULL


​			低    ---------------------------------------------------------   高

- 等级：byte、short、char >  int  >long  >  float  >   double


​			**高 --> 低       强制转换**

​			**低 --> 高       自动转换**

**整数类型:**    1字节 = 8 bit（位）

- ​	byte:  1个字节，-128-127  defeat : 0
-    short:  2个字节，-2^15 -- 2^15-1   defeat : 0
- ​     int :    4个字节， -2^31-- 2^31-1   defeat : 0
-  long:      8个字节 ， -2^63 -- 2^63-1  defeat : 0L

**浮点类型：** 

-   float:  4个   默认值是 **0.0f**；
- double:  8个  默认值是 **0.0d**；

**字符类型：**

- char  2个字节   16 位 Unicode 字符；  最小值是 **\u0000**（即为0）；最大值是 **\uffff**（即为65,535）  defeat: 'u0000'

==**A: 65**==

==**a: 97**==

```java
static Integer valueOf(int i)
static Integer valueOf(String s)
static Integer valueOf(String s, int radix)  // radis代表s的进制数，返回一个十进制整数
Integer.parseInt(str.substring(2),16))  //输入 0xA
Integer.parseInt(String s, int radix)方法实现，radix是进制，可以是2（二进制），10（十进制），16（十六进制）
    
    上面两个区别： Integer.parseInt返回时int类型，Integer valueOf时先调用前面这个方法，然后返回Integer封装类
```

**==Scanner :==**

​	next() -String  nextInt-- int   nextDouble()-- double    ----焦点==**不会**==移动到下一行

​	nextLine()    ---- 读取该行所有内容，并把**==焦点移动到下一行==**



**问题：.equal出现空指针异常**

```java
java.lang.NullPointerException
```

解决方法：***一定要遵循 “常量”.equals(变量) 或者 后输入的.equals(之前的)***

或者: ==**objects.equals(a,b==)**来预防参数为null，如果两个参数为null则返回true；其中一个为null返回false；如果两个都不为null，则自动调用  **a.equals(b)**



**==equals和==**==的区别

== ：**运算符**

1. 使用在**基础数据类型**和**引用数据类型**
2. ==**基础数据**==类型中，比较的是两个数的值 （不一定类型要相同） int=int  ，int= double  （类型自动提升，类似于 int+ double）
3. ==**引用类型**==比较的是两个==地址==的值，即两个对象是否指向同一个实体对象。



equals（）**方法**的使用：   

1. 是个方法，只适用==**引用数据类型**==

2.  ==**Object**==中的equals 和 == 是一样的  , 比较两个==**地址**==的值是否一样

   ```java
   public boolean equals(Object obj) {
           return (this == obj);
       }
   ```

3. 像String、Date、File、包装类都重写了Object的equals方法。**重写后比较的不是引用的地址的值，而是两个对象的“对应内部属性”是否相同**

   

```java
int a = 3;
int b = a++; 执行完之后，先给b赋值，a才+1
int c = ++a; 执行完之前，先a+1，再给c赋值
5
3
5
```



==**包装类**== ==**基本数据类型**==  ==**String类**==之间的相互转换：

- 基本数据类型 -->  包装类

  ```java
  int i = 10;
  Integer i2 = new Integer(i);
  //自动装箱  
  Integer i2 = i;
  i2.toString();   ==  10
      
  ```

- 包装类 -->  基本数据类型    调用 xxxValue

```java
Integer i2 = new Integer(i);
//自动拆箱
int i = i2;
i2.intValue()
```



![](I:\KSDownload\stringBuffer.png)



```java


 //判断掩码是否合法   前面全为1，后面全为0 才可以
            boolean isMask=false;
            String[] maskArr=ips[1].split("\\.");
            String binaryMask="";
            //形成掩码二进制字符串
            for(int i=0;i<maskArr.length;i++){
                String binaryStr=Integer.toBinaryString(Integer.parseInt(maskArr[i]));
                //凑成4组8位二进制
                for(int j=0;j<8-binaryStr.length();j++){
                    binaryStr="0"+binaryStr;
                }
                binaryMask+=binaryStr;
            }
            //比较二进制字符串中第一个0的位置和最后一个1的位置来判断掩码是否合法
            if(binaryMask.indexOf("0")<binaryMask.lastIndexOf("1")){
                isMask=false;
            }else {
                isMask=true;
            }
```



## 2. Java 方法

- 方法的重载： **函数名必须相同，参数列表必须不同！**


​						返回类型可以相同可以不同，但是返回类型不同不足以成为方法的重载！



> ==**spilt分隔符的使用**==

```java
1. 如果用“.”作为分隔的话，必须是如下写法：String.split("\\."),这样才能正确的分隔开，不能用String.split(".");

2. 如果用“|”作为分隔的话，必须是如下写法：String.split("\\|"),这样才能正确的分隔开，不能用String.split("|");
   “.”和“|”都是转义字符，必须得加"\\";

3. 如果在一个字符串中有多个分隔符，可以用“|”作为连字符，比如：“a=1 and b =2 or c=3”,把三个都分隔出来，可以用String.split("and|or");

   **split分隔符总结**

   1.字符"|","*","+"都得加上转义字符，前面加上"\\"。
   2.而如果是"\"，那么就得写成"\\\\"。
   3.如果一个字符串中有多个分隔符，可以用"|"作为连字符。


```













### 2.1  递归

- 自己调用自己的方法


！注意：递归头和递归体：即什么时候不调用自身方法，什么时候需要调用自身的方法！

## 3.  数组

​	**ArrayIndexOutOfBoundsException : 数组下标越界异常**

```java
public class ArrayDemo {
    public static void main(String[] args) {
        int[] number = {1,2,3,4,5,6,7,8,9};  //通过下标访问，索引从0开始，长度为10
        for (int i = 0; i <number.length; i++) {
            System.out.println(number[i]);
        }

        for (int i : number) {   //增强for循环
            System.out.println(i);
       }
    }
}
```

**数组排序： Array.sort(a)**



## 4.  面向对象

- **三大特性：封装，继承，多态**

创建一个类（属性和方法），创建一个对象来使用它！ 

**这个zs就是一个Student类的具体实例！**

```java
Student zs = new Student();
```

有参和无参构造器！ 和类相同，==必须没有返回值==！

==instanceOf  : 判断是否属于一个类型==

**封装： 属性私有private，使用get/set方法**

**继承：extends，只用单继承，没有多继承(super)**，**<u>私有的无法继承</u>**

​			==final声明的类不能被继承==

**多态：子类中没有，就调用父类的；若重写了父类的方法，执行子类的方法**

对象能执行什么方法，主要看对象**左边**的类型，和右边的关系不大

注意！： **方法多态，不是属性多态**！ 

要有继承关系；子类重写父类的方法，父类引用指向子类的对象



## 5.  ==集合框架==

**collection: list【==有序可重复==】(ArrayList、LinkedList)   set【==无序不可重复==】 (hashSet、treeSet)**

可以==**自动排序**==的组数： TreeSet  ;   TreeMap  ;TreeList

**map :  hashMap(==重点==)    treeMap**

![](I:\KSDownload\53ea0003d93a34f3e653.jpg)

**集合是数组的封装，功能更多，但速度下降；**

**数组**的特点：

1. 一旦初始化，长度就确定了；不能修改
2. 类型一开始就必须声明，e.g： String []、int [] 、double []
3. 数组的方法有限，存储数据==**有序可重复**==



集合可分为==**Collection**== 和  ==**map**== 体系

- ==**Collection**== 接口 ： 单列数据，定义了存储对象的集合

  - ==List== : **有序、可重复的集合 **    **(ArrayList、LinkedList)**
  - ==Set==: **无序、不可重复的集合**    **(hashSet、treeSet、LinkedHashSet)**

- ==**Map**== 接口 ： 双列数据，保存具有映射关系“==**key-value**=="的集合    **hashMap(==重点==)    treeMap  LinkedHashMap**



==**Collection：**==

```java
public class ArrayDemo {
    public static void main(String[] args) {
        Collection coll = new ArrayList();
        //add(Object)
        coll.add("AA");
        coll.add(123);
        coll.add(new Date());

        System.out.println(coll.size());
        Collection coll1 = new ArrayList();
        coll1.addAll(coll);  // 将集合里 的元素全部添加进去
        System.out.println(coll1);  //调用 toString方法
        //是否包含该元素（Object)
        boolean contains = coll1.contains(123);  //实际调用的是equals方法判断，如果引用类型没有重写equals就是==；若重写则是判断内容
        System.out.println(contains);
        
        coll1.remove(123);  // 也调用equals操作，先判断有没有123
    }
}
```

==**注意：**==**Collection接口的实现类的自定义对象中添加数据object时，要重写object所在类要重写equals（）方法**

**当然添加基本数据类型时会自动装箱成包装类，int --> integer等，已经重写了equals（）方法**

**remove(Object)**； 也调用了==**equals**==方法，先判断在移除

**removeAll(Collection)** : 移除交集，取差集

**retainAll(Collection )** : 取俩集合的交集



集合的==**遍历Iterator**==： hasNext()  ;  next()

==**每次调用Iterator方法都会创建一个新的迭代器**==

```java
public static void main(String[] args) {
        Collection coll = new ArrayList();
        //add(Object)
        coll.add("AA");
        coll.add(123);
        coll.add(new Date());
//--------------------------------------------------------
        Iterator iterator = coll.iterator();
        while (iterator.hasNext()){
            //next();指针下移，将下移后的指针位置上的元素返回
            System.out.println(iterator.next());
        }
}
```

**增强for循环（==for each==） 用于遍历集合和数组**

 ```java
    public static void main(String[] args) {
        Collection coll = new ArrayList();
        //add(Object)
        coll.add("AA");
        coll.add(123);
        coll.add(new Date());

        //for(集合元素类型  变量 ： 集合对象)
        //内部还是用了迭代器
        for(Object obj : coll){
            System.out.println(obj);
        }
    }
 ```



**==List接口==：存储有序可重复的数据(ArrayList、LinkedList、Vector) **

三者比较 ： 

- 相同： 实现List接口，存储数据相同，有序可重复

- 不同：ArrayList： 线程不安全，效率高，底层使用Object[]  elementData存储

  ​            Vector ： 线程安全，效率低，底层使用Object[]  elementData存储

  ​			LinkedList ： 对于频繁插入和删除数据，效率比ArrayList高，底层使用双向链表存储，data和next域     

  ​			（单向链表，双向链表（pre域指向前一个数据））

```java
增：list.add(data);list.add(int index,data)
	list.addAll(List)
删 ：list.remove(int index);  //删除索引对应位置的元素
	  remove(obj)
查：list.get(int index) // index 从0开始
	list.indexOf(data)   //首次出现data的索引位置 ,没有返回-1
	list.lastIndexOf(data)  //最后一次出现的索引位置
改：list.set(int index,data)
遍历： Iterator迭代器   foreach
    
```



==**Set接口 ：**== **无序且不可重复（hashSet、LinkedHashSet、TreeSet）** 

- **HashSet**: 线程==不==安全，可以==存储 null  值==

- **LinkedHashSet** : 作为HashSet的子类 ； 遍历内部数据时，可以==**按照添加顺序遍历**==

- **TreeSet** :  **可以==排序==且不可重复，放入的数据必须是一个对象中的类型**   ---按照对象的==**指定属性**==排序 ： Comparable

  **Set中不可重复原理 ：**保证添加元素按照==**equals**==方法判断时，不能是true：相同元素只能添加一个

  ​		==**要求：**==	<u>**（重写对象的hasCode和equals方法）**</u>



- **HashSet**  ： 用hashmap来存储的，我们要存储的元素给Key，value是个常量，所有的key都指向同一个value！

==**HashSet添加元素的原理：**== 添加a，首先调用a所在类的hasCode()方法，计算哈希值，通过哈希值计算出应该放在HashSet中的某个位置，判断此位置上是否有元素： 如果**<u>没有元素</u>**，就添加==**成功**==；若有其他元素b（或以链表形式存在的多个元素 -->  ==**七上八下**== ： 旧的数据指向新数据），先比较a和b的哈希值，若<u>**哈希值不同**</u>，则==**添加成功**==；若相同，再调用a元素的equals方法，若返回true，a与b重复，添加失败；若**<u>返回false</u>**，元素a==**添加成功**==。

​	

- **LinkedHashSet**

链表存放  pre|data|next  记录前一个数据，指向一个数据

遍历LinkedHashSet 效率比HashSet ==**高 **==  

**遍历的时候是按照==添加顺序==进行输出的**



- **TreeSet**    ==**红黑树**==

添加数据时，==**必须是同一个类提供的（相同类的对象）**==；

两种**排序方式** ： 自然排序 和 定制排序

两种排序中，比较相不相同 ：通过Comparable或者comparator  ； 如果==**return  0**==   ，就看作是一样的

```java
public class TreSet {
    public static void main(String[] args) {
        TreeSet set = new TreeSet();
        set.add(123);
        set.add(456);
        set.add(-25);
        set.add(35);
        set.add(75);

        System.out.println(set.add(123));   //  false
        
        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }
}
```



1.  在定义的类中实现==**Comparable接口**==，并重写CompareTo 方法   

​														按照**name**排序

<img src="I:\KSDownload\comparable.png" style="zoom:80%;" />

2. 在TreeSet中传入Comparator对象

   ​				<img src="I:\KSDownload\comparator.png" style="zoom:67%;" />

==**Map接口 ：**== **双列数据 ----Key-Value **      **(HashMap(==主要实现类==) --LinkedHashMap 、 Hashtable --Properties 、TreeMap)**

- HashMap  ： 主要实现类；线程不安全，效率高；存储 **null 的key和value**      **(数组+链表+红黑树)**

- LinkedHashMap:  双向链表，**保证在遍历的时候按照添加的顺序遍历**。==**能够记录添加元素的先后顺序**==

- Hashtable :    古老实现类；线程安全，效率低 ；不能存储 null 的key和value

- TreeMap : 保证按照添加的**Key-Value**进行排序，实现排序遍历。考虑==**key的自然排序或定制排序**==

- Properties : 常用来处理配置文件的事情

   **key - value**： key可以看做是**Set**（无序且不重复) ---->  key所在的类要重写**<u>equals()和hashCode</u>**方法 (以hashMap为例）

​									    value看作是**collection** (可重复、无序的)  -->   所在类重写**<u>equals()</u>**方法

​	**key-value**  构成了一个**==Entry==**对象  ：**无序且不可重复 ，使用==Set==存储**



HashMap的**实现原理** ： jdk 1.7

​			HashMap map = new HashMap();   实例化之后创建了长度为**16**的一位数组**Entry[] table**   Entry类型 的数组

​			map.put(key1,value1);    由于**Key**用的是**HashSet**存储，存储规则见上；唯一不同的是，**若equals返回==true==:**

​			**使用新添加的value值替换原有存在的值。**  put方法

​			在不断添加的过程中，默认的扩容方式： 容量为原来的2倍，并复制原有的数据。



**==jdk.1.8==**   与1.7的不同之处

1. 实例化后没有创建长度16的数组
2. 8 底层的数组是 ： Node[] ,而不是 Entry[]
3. 首次调用put方法时，底层创建长度16的数组
4. 1.7 --数组+链表   1.8 -- 数组+链表+红黑树 （当某个索引位置上以**链表存储的数据**超过==**8**==个且**数组**的长度超过**==64==**的时候改用**==红黑树==**存储）----**==查找效率更高！==**

<img src="I:\KSDownload\hashMap.png" style="zoom:67%;" />

==**Map的方法 ：**==

```java
增：map.put(Object,object)
   map.putAll(Map m)
删 ： map.remove(Object key)   //返回的是key对应的value值
   	 map.clear();  //清空数据，；map还存在
查： 	map.get(key)  //返回value的值
   	 map.containsKey(key)  // true false
  	 map.containsValue(value)//true false
 	 map.size()
  	 map.isEmpty()
     map.equals(Object obj) //判断当前的map和obj是否相等
    
遍历 ：
    Set keySet() : 返回所有key构成的set集合
    Collection values() :返回所有的value集合
    Set entrySet() :返回所有的key-value构成的set集合
```

==**遍历**==方法 :

```java
public class MapTest {
    public static void main(String[] args) {
        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(1, 123);
        map.put(2, 858);
        map.put(3, 57);
        map.put(4, 77);

        //遍历map，没有迭代器
        Set set = map.keySet();    -----------------------1
        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
           // System.out.println(iterator.next());
         
            Object next = iterator.next();
            Integer integer = map.get(next);
            System.out.println(next+"---"+integer);
        }
       

        Collection values = map.values();-----------------2
        for(Object obj : values){
            System.out.println(obj);
        }
        
        for(Map.Entry entry : map.entrySet()){------------3    //最重要！！！！
            System.out.println(entry.getKey()+"---"+ entry.getValue());
        }
    }
}
```



==**TreeMap**== ： **key的自然排序或定制排序**    key必须是由同一个类创建的对象

​		见上！







## 6.  网络编程

**1. 主要问题：**

​		如何定位到网络上的主机，找到后如何进行通信

**2.网络编程的要素**

​		ip和端口号； 网络通信协议

**3. 万物皆对象**



==java.net.InetAddress==



**4. 通信协议**

TCP/IP协议簇

- TCP:用户传输协议
- UDP：用户数据报协议
- 对比： TCP-打电话   UDP-发短信

TCP：连接稳定；**==三次握手；四次挥手==**、客户端和服务端连接、传输完成，释放连接，**效率低**

**三次握手：**

```java
A:我能连接一下么
B:回应A一下B在
A:那我连接了
```

**四次分手：**

```java
A:我要走了
B:我知道你要走了
B:你真的要走了么？
A:我真的要走了
```



UDP：发短信，不连接，不稳定；不管准备好没有，都可以发给你

## 7.  多线程详解

### 创建线程

- 进程和线程，进程里有着线程，如同保护伞！

  ​	**java.lang.thread**

![](I:\KSDownload\进程203551.png)

> 步骤： **第一种**
>

- 自定义线程类继承==Thread类==

- 重写==run()==方法，编写执行体

- 创建线程对象，调用==start()==方法启动线程

- ==**有局限性，一个对象不能被多个线程使用**==

- ![](I:\KSDownload\run 和start.png)

  开启star()，线程同时下载三张图片，没有先后顺序！

  ```java
  public class TestThread extends Thread{
      //1:继承thread类，重写run方法，start（）启动
      @Override
      public void run() {
          for (int i = 0; i < 5; i++) {
              System.out.println("线程"+i);
          }
      }
  
      public static void main(String[] args) {
          TestThread testThread = new TestThread();
          testThread.start();
          for (int i = 0; i < 10; i++) {
              System.out.println("我学习线程"+i);
          }
      }
  }
  ```

  > **第二种**： 

- ==实现Runnable接口==

- 重写==run()==方法，编写执行体

- 创建线程对象，调用==start()==方法启动线程

- **==推荐使用，灵活方便，一个对象可以被多个线程使用！==**

  ```java
  public class MyRunnable implements Runnable{
  
      @Override
      public void run() {
          for (int i = 0; i < 5; i++) {
              System.out.println("线程"+i);
          }
      }
  
      public static void main(String[] args) {
          MyRunnable myRunnable = new MyRunnable();
          new Thread(myRunnable).start();
          for (int i = 0; i < 10; i++) {
              System.out.println("我学习线程"+i);
          }
      }
  }
  
  ```

  > 第三种：实现callable接口（了解即可）

![](I:\KSDownload\callable30.png)

### 静态代理

- ​	代理对象和真实对象都要实现同一接口

- ​	代理对象要代理真实角色

  线程的第一种创建方法，继承thread类，而thread类实现 了runnable接口和第二种一样

### Lamda表达式

- 任何接口，若只有一个抽象的方法，那么就是一个函数式接口
- 对于函数式接口，可以通过Lamda来创建该接口的对象

![](I:\KSDownload\zhungtai.png)

 ```java
//测试stop
//建议线程自己正常停止
//建议利用标志位
//不要使用stop、destory
public class testStop implements Runnable{

    //设置一个标志位
    private boolean flag = true;
    @Override
    public void run() {
        int i = 0;
        while (flag){
            System.out.println("开始咯"+i++);
        }
    }

    public void stop(){
        this.flag=false;
    }
    public static void main(String[] args) {
        testStop s66top = new testStop();
        new Thread(stop).start();
        if(i=0,i<100,i++){
            if(i = 50){
                sout("stop")；
                s66top.stop();
          }
        }
    }
}
 ```



**线程礼让**：让正在执行的线程==暂停==，但不阻塞；从运行状态回到==就绪状态==；再让CPU重新调度，==**礼让不一定成功**==，可能还是之前的线程！   ==.yield();==

**线程强制执行（Join）,插队执行**

**线程优先值：min:1，max:10**

------

### 守护线程

- 线程分为守护线程和用户线程
- JVM确保用户线程执行完毕
- JVM不用等待守护线程执行完毕
- e.g ： 后台操作日志；监控内存；垃圾回收

### 线程同步（并发）

​	**多个线程操作同一个资源！** ==排队解决问题==

​	同步就是一个等待机制，多个操作对象进入对象的**==等待池==**，等待前面的线程处理完毕，下一个线程在使用！

​	==**条件： 队列 + 锁**== synchronized   只有修改内容的方法需要锁，锁太多，浪费资源

​	**==锁的对象是什么 ？==**         **哪里发生修改内容了，锁的对象是==变化量==！**

```java
synchronized(账户、list){
    操作方法
}
```

并发编程同步器： 同步器是一些使线程能够等待另一个线程的对象，允许它们协调动作。最常用的同步器是==CountDownLatch==和==Semaphore==，不常用的是<u>Barrier 和Exchanger</u>



### 死锁

相互等待对方释放资源，形成僵持局面；某个同步代码块拥有**==两个以上对象的锁==**，会发生“死锁”的现象！



### Lock(锁)

```java
private final ReentrantLock lock = new ReentrantLock();
```

![](I:\KSDownload\lock.png)



​	    lock是显式的锁（手动开启和关闭） synchronized是隐式的，出了作用域自动释放！

- Lock只有代码块，synchronized有 代码块和方法锁；
- lock锁性能更好，有更好的扩展性
- **使用顺序：Lock  >   同步代码块   >   同步方法**



### 线程通信

​	**预设缓存区**

wait():  线程等待，直到其他线程通知，**==会释放锁==**  ;   ==sleep==: **不会释放锁**

wait(long timeOut): 指定等到的毫秒数

notify():唤醒等待的线程

notifyAll(): 



### 线程池：

```java
ExecutorService service1 = Executors.newFixedThreadPool(10);
service1.execute(new Thread());//实现runnale接口的用execute
service1.submit(new callable) //实现callable用submit
    service1.shutdown();
```



## 8. 注解 Java.Annotation

------

Java中有4个标准的meta-annotation

- @Target       表示我们的注解可以用在什么地方
- @Retetion    表示注解在什么地方有效
- @Document 表示是否将我们的注解生成在Javadoc中
- @Inherited    子类可以继承父类的注解

### 反射机制：

------

**在程序执行前进借助Reflection Api取得任何类的内部信息，并能直接操作任意对象的内部属性及其方法**！

获得class的方式：

1. 通过创建的对象获得；

   ```java
   User user = new User();
   Class c2 =user.getClass();
   ```

2. forname获得

   ```java
   Class c1 = Class.forName("wsw.Main.User");  类名所在的包
   ```

3. 类名.class获得

   ```java
   Class c3 = User.class;
   ```

   ==**只要元素的维度和类型是一样的，那他们的Class就是同一个！**==



### 类加载

------

==**内存分析：**==

![](I:\KSDownload\内存.png)

类加载的过程：

![](I:\KSDownload\classLoder.png)



## 9. 方法整理

```java
String 
replaceAll(String regex, String replacement)   // 用Replacement替换符合正则表达的字符串
String
a.trim(); //删除字符串的头尾空白符
spilt(String regex)   // 分割字符串    [^a-zA-Z]  匹配非字母的字符串
注意 ：特殊字符需要转义"\"
    "." "|" "*" "\" "]"
    
    split("[.]") split("\\|") split("\\*") split("\\\\") split("\\[\\]") spilt("\\:")
```

















# 数据结构

## **线性结构和非线性结构**

- 线性结构：

1. 数据元素存在**==一对一==**的关系的线性关系
2. 两种存储方式：**顺序存储（顺序表中的存储元素是连续的）和链式存储（存储元素不一定连续）**
3. **<u>数组，队列，链表，栈</u>**



- 非线性结构：

		 1.  不是一对一关系
         		 2.  二维数组，多维数组，广义表，树，图



## **稀疏数组**

当一个数组中的==大多数==元素为0，或者为==同一个==数值，可以使用稀疏数组来保存该数组！

<img src="I:\KSDownload\稀疏数组.png" style="zoom: 67%;" />

**第一行记录原始数组的总的==行列==和多少个==值==**

**原始数组转化稀疏数组的思路：**

1.  遍历原始数组，得到有效的数据个数sum；
2. 创建数组 sparseArr [==sum+1==] [==3==]
3. 将有效数据填入稀疏数组中

**反过来转换：**

1. 读取第一行数据，创建数组 arry[6] [7];
2. 读取稀疏数组的元素给原始数组赋值即可 

```java
public static void main(String[] args) {
        //创建原始数组  0 :无 ， 1： 黑   2： 白
        int[][] arry = new int[11][11];
            arry[1][2] = 1;
            arry[2][3] = 2;
            for (int[] row : arry){
                for(int data : row){
                    System.out.printf("%d\t",data);
                }
                System.out.println();
            }
            int num = 0;
        for (int[] ints : arry)
            for (int j = 0; j < arry[0].length; j++) {
                if (ints[j] != 0) num++;
            }
        System.out.println(num);

            int[][] arry2 = new int[num+1][3];   //创建稀疏数组
            arry2[0][0]=arry.length;
            arry2[0][1]=arry[0].length;
            arry2[0][2]=num;
            int count = 0; //计数器
        for(int i =0;i<arry.length;i++){
            for (int j=0;j<arry[0].length;j++){
                if(arry[i][j] !=0){
                    count++;
                    arry2[count][0]=i;
                    arry2[count][1]=j;
                    arry2[count][2]=arry[i][j];
                }
            }
        }

        for (int[] ints : arry2) {
            System.out.printf("%d\t%d\t%d\t", ints[0], ints[1], ints[2]);
            System.out.println();
        }

        int[][] arry3 = new int[arry2[0][0]][arry2[0][1]];

        for (int i =1;i<arry2.length;i++){    //恢复原始数组
                arry3[arry2[i][0]][arry2[i][1]] = arry2[i][2];
        }
    }
```



## 队列

**银行排队叫号**

特点 ：

1. 有序列表，可以用**==数组和链表==**实现
2. **==先入先出==**原则；（先存入的数据先取出，后存入的数据后取出）
3. <img src="I:\KSDownload\队列.png" style="zoom:67%;" />

**添加数据，==rear==尾部增加；取出数据，==front==头部增加**



###  **数组模拟队列**

1. 队列本身是**==有序列表==**，若使用数组的结构来存储队列的数据，则队列数组, 其中 **maxSize** 是该队列的最大容量

2. 队列的输入输出是利用前后端处理的，有两个变量==**front及** **rear**==分别记录队列前后端的==下标==。

3.  满足的要求：看图理解

   ​				两个方法，addQueue,    1)**尾指针**往后移：==rear+1== , 当 ==front == rear== 【空】

    														   2)**rear** ==**小于**==队列的最大下标 maxSize - 1  ;   **rear == maxSize - 1[队列满]**

```java
public class Queue {
    public static void main(String[] args) {
        //创建对象
        ArrayQueue arrayQueue = new ArrayQueue(3);
    }
}


class ArrayQueue {
    public int maxSize;  //数组最大容量
    public  int front;   // 队列头部
    public  int rear;    // 队列尾部
    public int[] arry;   //存放数据，模拟队列

    //创建队列的构造器   名字和类名相同

    public ArrayQueue() {
    }

    public ArrayQueue (int arrMaxSize){
        maxSize = arrMaxSize;
        arry = new int[maxSize];
        front = -1;   //初始化头部尾部
        rear = -1;
    }

    public  Boolean isFull(){
        return rear == maxSize - 1;
    }

    public  Boolean isEmpty(){
        return rear == front;
    }

    public void addQueue(int n){   // 加数据
            if(isFull()){
                System.out.println("不能加入数据");
                return;
            }
            rear++;  // 尾部后移
            arry[rear] = n;
    }

    public int getQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列为空");
        }
        front++;
        return arry[front];
    }
}
```

上述数组模拟最大的问题： 当所有数据被取出，无法利用以前的空间存放数据，即**==数组只能用一次！==**

改进成 ： 环形**==数组队列==**，重复利用数组空间。 利用取模   ==**%**==



改进思路 ： 

1. front变量做调整，以前front是指向第一个数据的前一个位置；调整为指向第一个元素，**arry[front]**是==**第一个**==元素

   front = 0  ;   rear = 0  ;  初始值

2. rear也有调整：指向最后一个元素的后一个位置

3. 队列满 ： **（rear + 1）% maxSize  == front**

4. 队列为空时 ：  **rear == front**

5. 有效数据的个数  ：  **（rear  + maxSize - front ) %  maxSize** 

```java
// 修改 之后的code ,环形队列
package wangsw.Queue;

public class Queue {
    public static void main(String[] args) {
        //创建对象
        ArrayQueue arrayQueue = new ArrayQueue(4);  // 预留一个空间  只能输入三个数据
    }
}


class ArrayQueue {
    public int maxSize;  //数组最大容量
    public  int front;   // 指向第一个数据 ， arr[front]
    public  int rear;    // 最后一个元素的后一个位置
    public int[] arry;   //存放数据，模拟队列

    //创建队列的构造器   名字和类名相同

    public ArrayQueue() {
    }

    public ArrayQueue (int arrMaxSize){
        maxSize = arrMaxSize;
        arry = new int[maxSize];
        front = 0;   //改进后的 初始值
        rear = 0;
    }

    public  Boolean isFull(){
        return (rear + 1) % maxSize  == front;
    }

    public  Boolean isEmpty(){
        return rear == front;
    }

    public void addQueue(int n){   // 加数据
            if(isFull()){
                System.out.println("不能加入数据");
                return;
            }
            arry[rear] = n;
            rear = (rear + 1 )% maxSize;
    }

    public int getQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列为空");
        }
        int a = arry[front];
        front = (front + 1)% maxSize;
        return a;
    }
    public int size(){
       return  (rear  + maxSize - front )% maxSize;
    }
    public void showArry(){
        for(int i = front;i<front + size();i++ ){
            System.out.printf("arry[%d] = %d\n",i%maxSize,arry[i%maxSize] );
        }

    }

}
```



## 单向链表（Linked List）

链表是有序的列表，在内存中存储如下：

<img src="C:\Users\wangsw\AppData\Roaming\Typora\typora-user-images\image-20200607133735525.png" alt="image-20200607133735525" style="zoom: 80%;" />

1. 链表是以**==节点==**的方式来存储的
2. > 每个节点包含**==data==域**和**==next==域**
3. 链表的各个节点**==不一定==**是按顺序来存放的！
4. 链表分为**带头节点**和**不带节点**的链表，根据实际的需求来确定



```java
// 没有顺序的链表

package wangsw.LinkedList;

public class Linkedlist {
    public static void main(String[] args) {
        HeroNode node2 = new HeroNode(1,"11","22");
        HeroNode node3 = new HeroNode(2,"22","33");
        HeroNode node4 = new HeroNode(3,"33","44");
        HeroNode node5 = new HeroNode(4,"44","55");
        SingleLinkedList linkedList = new SingleLinkedList();
        linkedList.addHeroNode(node2);
        linkedList.addHeroNode(node3);
        linkedList.addHeroNode(node4);
        linkedList.addHeroNode(node5);
        linkedList.list();
    }
}

class SingleLinkedList{
        // 初始化头节点，头节点不能动,不存放数据
        private HeroNode head = new HeroNode(0,"","");
        //找到链表的最后节点 （不考虑顺序）
        //将最后节点的next 指向下一个新的节点

        public void addHeroNode(HeroNode heroNode){
                    // 需要辅助变量
                HeroNode temp = head;
                while (true){
                    if(temp.next == null){   //代表链表的最后节点
                            break;
                    }else {
                            //如果不是，就temp后移
                        temp = temp.next;
                    }
                }
                    temp.next = heroNode;   // 将下一个节点赋予其next域
        }

        public void list(){

            if(head.next == null){
                System.out.println("空");
                return;
            }
            HeroNode temp = head.next;
            while (true){
                if(temp ==null){
                    break;
                }
                System.out.println(temp);
                temp = temp.next;   // temp 后移
            }
        }

}
class HeroNode{
    public int no;
    public String name;
    public  String nickName;
    public HeroNode next;

    public HeroNode(){};

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
```

> **按顺序添加的实现：**  1. 首先找到**==新添加的节点的位置==**  2.  **新的节点.next = temp.next  ;    temp.next = 新的节点**

**找到链表的最后和找到插入的位置，都break，因为两种情况都是 插入在==temp==后面**

```java
package wangsw.LinkedList;
// 有顺序的插入
public class Linkedlist {
    public static void main(String[] args) {
        HeroNode node2 = new HeroNode(1,"11","22");
        HeroNode node3 = new HeroNode(2,"22","33");
        HeroNode node4 = new HeroNode(3,"33","66");
        HeroNode node5 = new HeroNode(4,"44","55");
        SingleLinkedList linkedList = new SingleLinkedList();
        linkedList.addByOrder(node3);
        linkedList.addByOrder(node5);
        linkedList.addByOrder(node4);
        linkedList.addByOrder(node2);
        linkedList.list();
    }
}

class SingleLinkedList{
        // 初始化头节点，头节点不能动,不存放数据
        private HeroNode head = new HeroNode(0,"","");
        //找到链表的最后节点 （不考虑顺序）
        //将最后节点的next 指向下一个新的节点

        public void addHeroNode(HeroNode heroNode){
                    // 需要辅助变量
                HeroNode temp = head;
                while (true){
                    if(temp.next == null){   //代表链表的最后节点
                            break;
                    }else {
                            //如果不是，就temp后移
                        temp = temp.next;
                    }
                }
                    temp.next = heroNode;   // 将下一个节点赋予其next域
        }

        // 考虑顺序的时候
        public void addByOrder(HeroNode heroNode){
            //寻找插入位置，位于temp的前一个位置
            HeroNode temp = head;
            Boolean flag = false;   // 标号是否存在，默认为false
            while (true){
                if(temp.next == null){  //达到链表的最后
                    break;
                }
                if(temp.next.no > heroNode.no){  //位置找到了，在temp的后面
                    break;
                }else if(temp.next.no==heroNode.no){  //标号以存在
                    flag = true;
                    break;
                }
                temp = temp.next;   // 下移继续循环，直到break；
            }
            if(flag){
                System.out.println("存在");
            }else {                           // 找到最后或者找到之间位置，都break，因为都是插入在temt后面
                heroNode.next = temp.next;
                temp.next = heroNode;
            }
        }

        public void list(){

            if(head.next == null){
                System.out.println("空");
                return;
            }
            HeroNode temp = head.next;
            while (true){
                if(temp ==null){
                    break;
                }
                System.out.println(temp);
                temp = temp.next;   // temp 后移
            }
        }

}
class HeroNode{
    public int no;
    public String name;
    public  String nickName;
    public HeroNode next;

    public HeroNode(){};

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
```

> **更新节点： update 循环寻找即可**

```java
public void update(HeroNode newNode){
            if(head ==null){
                System.out.println("链表为空");
            }
            HeroNode temp = head.next;  //直接指向节点的
            Boolean flag = false;
            while (true){
                if(temp==null){
                    break;
                }else if(temp.no ==newNode.no){
                    flag =true;
                    break;
                }
                temp = temp.next;
            }
            if(flag){
                temp.name =newNode.name;
                temp.nickName =newNode.nickName;
            }else {
                System.out.println("没有找到要修改的节点");
            }
        }
```

> **练习：单链表倒数第K个节点**

（1). head节点和index值

（2). index表示倒数几个节点

（3).先遍历链表，得到总长度getLength

（4). 得到size后，从链表的第一个开始遍历(size - index)个，即可

```java
//获得单链表的size，（带头节点的链表不考虑头）
public int getLength(HeroNode head){
            if(head.next ==null){
                return 0;
            }
            int length = 0;//开始统计
            //定义辅助变量
            HeroNode cur = head.next;
            while (cur !=null){
                 length++;
                 cur = cur.next;
            }
            return length;
        }

public HeroNode getIndexNode(HeroNode head,int index){
            if(head.next ==null){
                return null;
            }
            int size = getLength(head);
            //校验index
            if(index<=0 || index>size){
                return  null;
            }
            HeroNode cur = head.next;
            for(int i=0;i<size-index;i++){
                cur = cur.next;
            }
            return cur;
        }
```

> 练习2：









## 双向链表

![](I:\KSDownload\双链表.png)

- > 每个节点有**==pre域、data域、next域==**

- 双向链表的遍历、添加、修改。删除：

  1.  遍历和单链表一样，只是可以向前和向后；
  2.  ==添加==到最后：
     - 先找到最后的节点
     - temp.next = new
     - new.pre = temp
  3. ==修改==，原理和单链表一样
  4. ==删除==节点
     - 双向链表实现==**自我删除**==，直接找到节点temp
     - temp.pre.next = temp.next;
     - temp.next.pre = temp.pre;

```java
// 遍历双向链表
    public void list(){
        if(head.next == null){
            System.out.println("空");
            return;
        }
        HeroNode2 temp = head.next;
        while (true){
            if(temp ==null){
                break;
            }
            System.out.println(temp);
            temp = temp.next;   // temp 后移
        }
    }  

public void addHeroNode(HeroNode2 heroNode){
        // 需要辅助变量
        HeroNode2 temp = head;
        while (true){
            if(temp.next == null){   //代表链表的最后节点
                break;
            }else {
                //如果不是，就temp后移
                temp = temp.next;
            }
        }
        temp.next = heroNode;   // 将下一个节点赋予其next域
        heroNode.pre = temp;   // 双向链表添加完成
    }

public void update(HeroNode2 newNode){
        if(head ==null){
            System.out.println("链表为空");
        }
        HeroNode2 temp = head.next;  //直接指向节点的
        Boolean flag = false;
        while (true){
            if(temp==null){
                break;
            }else if(temp.no ==newNode.no){
                flag =true;
                break;
            }
            temp = temp.next;
        }
        if(flag){
            temp.name =newNode.name;
            temp.nickName =newNode.nickName;
        }else {
            System.out.println("没有找到要修改的节点");
        }
    }
```



## 二叉树

树的节点和高度（几层）

<img src="I:\KSDownload\二叉树.png" style="zoom:67%;" />

​      看==父节点==的位置，确定是前序中序还是后序

- **前序遍历**-- 先输出==父==节点，在遍历==左==子树和==右==子树
  1. 创建二叉树
  2. 先输出当前节点
  3. 如果左节点不为空，则递归继续前序遍历
  4. 如果右节点不为空，则递归继续前序遍历
- **中序遍历 **--先遍历==左==子树，输出当前节点，遍历==右==子树
  1. 如果左节点不为空，则递归继续前序遍历
  2. 输出当前节点
  3. 如果右节点不为空，则递归继续前序遍历
- **后序遍历**--遍历==左==子树，遍历==右==子树，输出当前节点
  1. 如果左节点不为空，则递归继续前序遍历
  2. 如果右节点不为空，则递归继续前序遍历
  3. 输出当前节点

```java
// 前序
public void preOrder(){
            System.out.println(this); // 输出父节点
            // 递归向左子树
            if(this.left !=null){
                this.left.preOrder();
            }
            // 递归向右子树
            if(this.right !=null){
                this.right.preOrder();
            }
        }

// 中序
public void infixOrder(){
            // 递归向左子树中序遍历
            if(this.left !=null){
                this.left.infixOrder();
            }
            // 输出当前节点
            System.out.println(this);
            // 递归向右子树中序遍历
            if(this.right !=null){
                this.right.infixOrder();
            }
        }
// 后序
public void postOrder(){
            // 递归向左子树中序遍历
            if(this.left !=null){
                this.left.postOrder();

            // 递归向右子树中序遍历
            if(this.right !=null){
                this.right.postOrder();
            }
                // 输出当前节点
                System.out.println(this);
        }
```



## 图

==**DFS 步骤**== ：   递归步骤

- 访问初始节点v，并标记v==被访问==了
- 查找节点v的第一个临近点 w
- 若w存在，则继续执行 4 ； 如果w不存在，则回到第一步，从v的下一个节点继续
- 若w未被访问，对w进行==dfs递归==（把w当做是另一个v，然后进行步骤123）
- 查找w节点的下一个临近节点转到步骤 3



下面的是 dfs 解决树的问题 

> 1. **base case**    递归的截止条件
> 2. **向子问题要答案 (return value)**
> 3. **利用子问题答案构架当前问题（当前递归层）的答案**
> 4. **若有必要，做额外的操作**
> 5. **返回答案给父问题**













#  算法

## 栈--四则运算

思路 ： 

1. 通过索引index来扫描表达式
2. 数字入数字栈
3. 如果是符号，分为以下：
   - 符号栈为空，直接入栈
   - 不为空，比较两个符号的优先级，如果准备入栈的优先级==小于或者大于==栈中的操作符，从数栈中pop出两个数，在pop出一个符号，计算结果，得到的结果入数栈。然后将扫描的操作符入符号栈。
   - ==大于==符号栈中的操作符，==直接入栈==
   - 扫描结束后，就顺序从数栈和符号栈中取出数，计算结果
   - 最后的数就是最终的结果



> **前、中、后缀表达式**

前缀表达式 ： 运算符在操作数之前

中缀 ： 人们的计算思路

后缀表达式： 运算符操作数的右边















## 递归

==自己调用自己==，每次调用传入不同的变量，解决复杂的问题。**代码简洁**

 递归的调用规则：

1. 当程序执行一个方法时，就会开辟一个独立的空间（栈）

2. 每个空间的数据（局部变量），是独立的

3. 递归必须向**退出递归的条件**逼近，否则就是**==StackOverflowError==**错误

4. 当方法执行完毕后，或遇到return。**==遵守谁调用，就将结果返回给谁==**！

   <img src="I:\KSDownload\递归.png" style="zoom:80%;" />





































## 约瑟夫环（单向环形链表）

> **思路：1. 创建第一个节点，让first指向该节点，并形成环形**
>
>    			2. 创建新节点 并加入链表中
>             			3. 最后遍历整个链表，结束的标志就是：节点.next = first 结束

<img src="I:\KSDownload\2020-07-04_154701.png" style="zoom:67%;" />

<img src="I:\KSDownload\2020-07-04_154713.png" style="zoom:67%;" />

 ```java
package wangsw.LinkedList;

public class Practice {
    // 约瑟夫环  单向环形链表
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.add(5);
        list.showBoy();
        list.countBoy(1,2,5);
    }
}
    class LinkedList{
        // 创建first节点
        private Boy first = null;
        // 添加节点
        public void add(int num){
            if(num<=0){
                System.out.println("不满足");
            }
            Boy cur = null;
            for(int i=1;i<=num;i++){
                // 创建节点
                Boy boy = new Boy(i);
                if(i==1){
                    first = boy;
                    first.setNext(first);
                    cur = first;
                } else {
                    cur.setNext(boy);
                    boy.setNext(first);
                    cur = boy;
                }
            }
        }

        public void showBoy(){
            if(first ==null){
                System.out.println("空");
                return;
            }
                Boy curr = first;
                while (true){
                    System.out.println(curr.getNo());
                    if(curr.getNext() == first){
                        break;
                    }
                    curr = curr.getNext();
                }
        }
        public void countBoy(int startNo,int countNum,int nums){ // 从第几个  ；m数几个；总共有几个人
            if(first ==null || startNo<1 || startNo > nums){
                System.out.println("输入有误");
                return;
            }
            // 创建辅助指针
            Boy helper = first;
            while (true){
                if(helper.getNext()==first){
                    break;
                }
                helper = helper.getNext();
            }
            for(int j=0;j<startNo-1;j++){  // 移动到小孩开始的地方
                first = first.getNext();
                helper = helper.getNext();
            }
            // first和helper同时移动m-1次，直到helper指向firsrt
            while (true){
                if(helper==first){
                    break;
                }
                for(int i=0;i<countNum-1;i++){
                    first = first.getNext();
                    helper = helper.getNext();
                }
                System.out.println(first.getNo());
                first = first.getNext();
                helper.setNext(first);
            }
            System.out.println("最后的一个："+helper.getNo());
        }
    }
    class Boy{
        private int no;
        private Boy next;

        public Boy(int no) {
            this.no = no;
        }

        public int getNo() {
            return no;
        }

        public void setNo(int no) {
            this.no = no;
        }

        public Boy getNext() {
            return next;
        }

        public void setNext(Boy next) {
            this.next = next;
        }
    }


 ```



## 排序算法

重点**内部排序**（使用==内存排序==）

**插入排序（直接插入排序、希尔排序）、选择排序（简单选择排序、堆排序）、交换排序（冒泡排序、快速排序）、**

**归并排序、基数排序**

https://www.cnblogs.com/onepixel/articles/7674659.html

1. **冒泡排序**

   -  比较相邻的元素。如果第一个比第二个大，就**交换**它们两个；
   - 不断重复步骤   ==**每次的最后一个都是最大的**==
   - 外循环次数是 ==n-1==次，每次排序的==次数逐渐减小==（每次循环都能找到**==大==**的值）

   ```java
   public static void main(String[] args) {   int[] array = {8,9,1,7,2,3,5,4,6,0};
           int[] array = {3,9,-1,10,-2};
           int temp = 0;
           for(int i=0; i < array.length-1; i++) {
               for (int j = 0; j < array.length - 1- i; j++) {
                   // 如果前面的数比后面的大，就交换
                   if (array[j] > array[j + 1]) {
                       temp = array[j + 1];
                       array[j + 1] = array[j];
                       array[j] = temp;
                   }
               }
               System.out.println(Arrays.toString(array));  // 输出数组
           }
       }
   ```

   **优化一下 :  如果在途中一次交换都没有，就退出循环**·

   ```java
   public static void main(String[] args) {
           int[] array = {3,9,-1,10,-2};
           int temp = 0;
       
       	boolean flag =false;
       
           for(int i=0; i < array.length-1; i++) {
               for (int j = 0; j < array.length - 1- i; j++) {
                   // 如果前面的数比后面的大，就交换
                   if (array[j] > array[j + 1]) {
                       flag =true;
                       temp = array[j + 1];
                       array[j + 1] = array[j];
                       array[j] = temp;
                   }
               }
               System.out.println(Arrays.toString(array));  // 输出数组
               if(!flag){
                       break;
                   }else {
                       flag = false;
                   }
           }
       }
   ```

   

2. **选择排序**
   - 从要排序的数据中，指定规则选出元素，在交换位置
   - 从要排序的数据中找出一个==最小的数==，然后跟当前第一个数据交换
   - 每次交换的数据都在减少，经过**==n-1次==**

```java
public static void selectSort(int[] array) {   int[] array = {8,9,1,7,2,3,5,4,6,0};
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            int min = array[i];
            for (int j = i + 1; j < array.length; j++) {
                if (min > array[j]) {  // 比较大小
                    min = array[j];
                    minIndex = j;
                }
            }
            // 交换位置
            if(minIndex !=i) {
                array[minIndex] = array[i];
                array[i] = min;
                System.out.println(Arrays.toString(array));
            }

        }
    }
[0, 9, 1, 7, 2, 3, 5, 4, 6, 8]
[0, 1, 9, 7, 2, 3, 5, 4, 6, 8]
[0, 1, 2, 7, 9, 3, 5, 4, 6, 8]
[0, 1, 2, 3, 9, 7, 5, 4, 6, 8]
[0, 1, 2, 3, 4, 7, 5, 9, 6, 8]
[0, 1, 2, 3, 4, 5, 7, 9, 6, 8]
[0, 1, 2, 3, 4, 5, 6, 9, 7, 8]
```



3. **插入排序**

   - 通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入

   ```java
   public static void  insertSort(int[] array) {   int[] array = {8,9,1,7,2,3,5,4,6,0};
           // {101,   34,119,1}
           // {34,  101,101,119}  36
          // {1,34,101,119};
           for (int i = 1; i < array.length; i++) {
               int insertNum = array[i];
               int insertIndex = i - 1;  // 待插入的前一个数的下标
               // 待排序的数小于前面的数，才插入
               while (insertIndex >= 0 && insertNum < array[insertIndex]) {
                   array[insertIndex + 1] = array[insertIndex]; // 数后移
                   insertIndex--;
               }
               // 退出循环，意思就是 insertNum >= array[insertIndex]  放在 Index+1的位置
               array[insertIndex + 1] = insertNum;
               System.out.println(Arrays.toString(array));
           }
       }
   //  数依次后移腾出位置
   [8, 9, 1, 7, 2, 3, 5, 4, 6, 0]
   [1, 8, 9, 7, 2, 3, 5, 4, 6, 0]
   [1, 7, 8, 9, 2, 3, 5, 4, 6, 0]
   [1, 2, 7, 8, 9, 3, 5, 4, 6, 0]
   [1, 2, 3, 7, 8, 9, 5, 4, 6, 0]
   ```



4. **希尔排序**（对==插入排序==的基础上改进）

   <img src="C:\Users\wangsw\Desktop\笔记\刷题笔记\4.png" style="zoom:67%;" />

   ```java
   //希尔排序   核心是分小组进行插入排序
       public  static void shellSort(int[] array){    int[] array = {8,9,1,7,2,3,5,4,6,0};
           //   {8 9 1 7 2 |||  3 5 4 6 0 }
           // 第一轮 分成 num/2
           int temp = 0;
           for(int gap = array.length / 2; gap > 0; gap /=2){
               for (int i = gap; i < array.length; i++) {
                   int j = i;
                   temp = array[j];  // 带插入的数,插入排序    j-gap 步长对应的值  8--3 | 9--5
                   while (j-gap>=0 && temp < array[j-gap]) {
                       array[j] = array[j-gap];
                       j = j-gap;
                   }
                   array[j] = temp;
               }
               System.out.println(Arrays.toString(array));
           }
       }
   //   {8 9 1 7 2 |||  3 5 4 6 0 }   10/2 = 5组   8和3比较，9和5比较  1和4你叫    
   [3, 5, 1, 6, 0, 8, 9, 4, 7, 2]      5/2  = 2组  
   [0, 2, 1, 4, 3, 5, 7, 6, 9, 8]
   [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
   ```



5. **快速排序**(冒泡的改进)  

   前后==**双指比**==较交换位置

   思路： 先分割数据，找**基准**，把比基准小的放在==左==边，比大的放==右==边，然后快速排序

   <img src="C:\Users\wangsw\AppData\Roaming\Typora\typora-user-images\image-20200724095817989.png" alt="image-20200724095817989" style="zoom:80%;" />

```java
public static void quickSort(int[] array,int left,int right){
        // { 8, -2, 3, 9, 0, 1, 7, 6}
        if(left >right) return;    // 一定要加，不然递归栈溢出！！！
        int l = left,r = right;
        // 基准数
        int pivot = array[left];
        // 双指针运行
        while(l < r){
            // 找数，比pivot大的和小的，交换位置
            // 先看右边 ！！ 一定要先由
            while((array[r] >= pivot) && l < r) r--;
            // 然后左边 ！！
            while((array[l] <= pivot) && l < r) l++;
            if(l < r){  // 交换各自的数据
                int temp = array[l];
                array[l] = array[r];
                array[r] = temp;
            }
        }
        // 基准数与l r碰头数交换
        array[left] = array[l];
        array[l] = pivot;
        //对左右两边进行递归排序
        quickSort(array, left, l - 1);
        quickSort(array, l + 1, right);
    }
```



6. **归并排序**（==分而治之==） 依次递归

```java
public static void MergetSort(int[] array,int left,int right,int[] temp){
        if(left<right){
            int mid = left + (right - left)/2;
            MergetSort(array,left,mid,temp);
            MergetSort(array,mid+1,right,temp);
            merge(array,left,right,mid,temp);
        }
    }

    // 合并数组的方法
    public static void merge(int[] array,int left,int right,int mid,int[] temp){
        // {8 4 5 7 1 3 6 2}
            int i = left;   // 左边一半的索引
            int j = mid + 1 ;  // 分为两半
            int t = 0;   // 中转数组的索引

        // 拷贝数据,直到有一半处理完毕
        while(i<=mid && j<=right){
            if(array[i] <= array[j]){
                temp[t++] = array[i++];
            }else {
                temp[t++] = array[j++];
            }
        }
        // 还有剩余的数继续放到temp中
        while(i<=mid){  // 左边的数据还有剩余
            temp[t++] = array[i++];
        }
        while(j<= right){  // 右边的数据还有剩余
            temp[t++] = array[j++];
;
        }
        //将temp数据拷贝到array中，并不是所有的数据，因为他分 和 治了很多次
        t = 0;
        int tempLeft = left;
        while(tempLeft <= right){
            array[tempLeft++] = temp[t++];
        }
    }
```



7. 















## 二分查找  ==有序查找==

==**必须是有序数组中**==

**递归法**

- 首先确定数组的中间下标   mid = (left+right)/2  
- 需要查找的数findVal 和arr[mid] 比较
  - findVal  > arr[mid]    ,  需要找的数在 ==**右半区间**==
  - findVal  < arr[mid]    ,  需要找的数在 ==**左半区间**==
  - findVal = arr[mid]   , 则返回

重要 ： 什么时候==结束递归？==

1. 找到就结束递归
2. 递归完仍然没找到，也需要结束递归 。  当==left > right==  则结束递归



**非递归**

时间复杂度O(log2N)

```java
	int right = nums.length - 1; // 注意
	while(left <= right) {
		int mid = left + (right - left) / 2;
		if(nums[mid] == target)
            // 找到直接返回即可
			return mid; 
		else if (nums[mid] < target)
			left = mid + 1; // 注意
		else if (nums[mid] > target)
			right = mid - 1; // 注意
	}
		return -1;
}
```



## 插值查找

将mid的值与被查找数挂钩

```java
    public static int BinarySearchint(int val,int[] array,int left,int right){
        // 有序数组才行
        // 将二分查找换成插值查找
        if(val < array[0] || val > array[array.length - 1])
            return -1;
        int l = left,r = right;
        while (l <= r){
            int mid = l + (r - l)*(val - array[l])/(array[r] - array[l]);  // 由于val参与mid，不在范围内会引起mid越界
            if(array[mid] > val)
                r = mid - 1;
            else if(array[mid] < val)
                l = mid + 1;
            else return mid;
        }
        return -1;
    }
```

## 斐波那契查找

斐波那契数列  ： {1  1  2  3  5  8  13  21  34  55}    两个相邻数的比例，无限接近 ==0.618==

也是改变  ==mid==  的位置

mid = low + F(k-1) -1    F代表斐波那契数列      F[k] = F[k-1] + F[k-2]    **k>2**

关键是找到  ==k==

```java
// 获得斐波那契数列
    public static int[] fib(){
        int[] f = new int[20];  // 初始化20个，比数组长
        f[0] = 1;
        f[1] = 1;
        for(int i = 2;i < 20; i++){
            f[i] = f[i-1] + f[i-2];
        }
        return f;
    }
    //  编写斐波那契查找     F[k] - 1 = (F[k-1]-1) + (F[k-2]-1) + 1
    // mid = low + f[k-1] - 1
    public static int fibSerach(int[]a ,int key){
        int low = 0;
        int high = a.length - 1;
        int k = 0; // 斐波那契分割数字的下标
        int mid = 0;
        int[] f = fib();  // 存放数列
        while (high > f[k] - 1)  // 保证 F[k] - 1 >= 数组长度即可
            k++;
        // 将数组a扩容跟f[k] 一样的长度，缺省的值用数组最后一个数补全
        int[] temp = Arrays.copyOf(a,f[k]);
        for(int i=high+1;i < temp.length;i++) //  temp = 	 {1,8,10,89,1000,1234,1234,1234,1234}
            temp[i] = a[high];  //缺省的值用数组最后一个数补全

        while (low <= high){
            mid = low + f[k-1] -1;
            if(key < temp[mid]){
                high = mid -1;
                k--;    //   f[i] = f[i-1] + f[i-2];  继续往f[k-1]前部分找   f[i-1] = f[i-2] + f[i-3];
            }else if(key > temp[mid]){
                low = mid +1;
                k -=2;  // // f[i] = f[i-1] + f[i-2];  继续往f[k-2]部分找   f[i-2] = f[i-3] + f[i-4];
            }else {  // 找到了
                if(mid <= high)  // 返回小的索引
                    return mid;
                else return high;
            }
        }
        return -1;
    }
```







## 动态规划   核心是穷举求最值问题

数学归纳法，==假设dp[0......i-1]都解决了，怎么算出dp[i]的大小==

### 背包问题

核心思想： 大问题转化为小问题，一步步获得最优解；（思想---》公式和规律---》代码）

<img src="I:\KSDownload\背包问题.png" style="zoom:80%;" />

> **背包问题：**
>
> 1.背包总价值最大，重量不超过
>
> 2.物品不能重复

```java 
//  w(i) 和 v(i)表示第i个物品的价值和重量，C为背包容量
//  v[i][j] = 前i个物品，能装入容量为j的背包最大价值

```

|   物品    |  0   |   1磅    |    2     |           3            |             4              |
| :-------: | :--: | :------: | :------: | :--------------------: | :------------------------: |
|           |  0   |    0     |    0     |           0            |             0              |
| **吉他**1 |  0   | 吉他1500 | 吉他1500 |          1500          |            1500            |
| **音箱**4 |  0   |   1500   |   1500   |          1500          | 3000（与价值比较上面比较） |
| **电脑**3 |  0   |   1500   |   1500   | 2000（与上面价值比较） |   3500（与上面价值比较）   |

```java
公式如下：
//  v[i][0]=v[0][j]=0
//  w(i)>j时，v[i][j]=v[i-1][j]
//  w(i)<=j;  v[i][j]=max{v[i-1][j]  ,  v[i]+v[i-1][j-w(i)] }  装入i-1商品，到剩余空间j-w(i)的最大值   
```



### 最长连续递增子序列

```java
示例： 1,9,2,5,7,3,4,6,8,0
输出： 3,4,6,8
```

https://www.cnblogs.com/kyoner/p/11216871.html  讲解通俗易懂

```java
public class Main {
    public Integer getLength(int[] num){
        int[] dp = new int[num.length];
        // 数组赋值初始为1
        Arrays.fill(dp,1);
        for(int i=0;i<num.length;i++){
            for(int j=0;j<i;j++){
                if(num[j]<num[i]){
                    dp[i] =Math.max(dp[i],dp[j]+1);
                }
            }
        }
        int res = 0;
        for(int i=0;i<dp.length;i++){
            res=Math.max(res,dp[i]);
        }
        return res;
    }
}
```



### 动态规划解题框架 

==动态转移方程==      解决问题的核心 （ 备忘录，避免重复计算）

```java
public static void main(String[] )
```

 

















