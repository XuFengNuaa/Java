## JUC

1. > **线程和进程**

   进程 ：运行一个程序，开辟一个==进程==。系统运行程序的基本单位

   线程： 比进程更小的运行单位。一个进程包含多个线程。多个线程共享一块内存空间和一组资源

   每个进程间是相互独立的，一个进程包含多个线程，每个线程之间可能相互影响。



2. >  **java的线程默认有几个？  java真的能开启线程么？**

   2个， main线程 和  GC

   thread  底层调用了native 本地方法    start0

   ```java
   private native void  start0 ;
   ```

   

3. >  **并发和 并行**

   - 并发 ： 多个线程操作同一资源  ， cpu 快速切换交替

   - 并行 ： 多个线程同时进行

     并发编程的==本质== ： 充分利用==CPU资源==



4. > **线程的基本状态**

   ```java
   NEW 新生
   RUNnABLE 运行中
   Blocked  阻塞
   waiting  等待
   time_waiting 超时等待
   terminated  结束进程
   ```



5. > **wait 和 sleep 的区别**   ( ==重点牢记！==)

   - 来自不同类   Object  .  wait     Thread   .  sleep
   - 释放锁   wait 会释放锁  ； sleep表示线程休眠 ，抱着锁睡，不会释放
   - 使用的范围不同 :  wait  只能在同步代码快中使用  ；  sleep  在任何地方都可以使用
   - 捕获异常 ：  wait 不需要捕获异常 ， sleep 在使用  `Thread.sleep()`  的时候，需要捕获异常 



6. >   **==重点 Lock 锁==**

    传统的==synchronized==  ：  进程排队，依次获得锁，执行操作

   Lock 是接口   **interface lock** 

   ```java
   Lock lock = new ReentrantLock();  // 默认无参  非公平锁
   public ReentrantLock(){
       sync = new NonfairSync();   // 两个重载函数
   }
   public ReentrantLock(Boolean fair){ // 公平锁
       
   }
   ```

   - 公平锁 ： 十分公平 ； 先来后道
   - 非公平锁 ： **十分不公平；可以插队(==默认==)**

   **lock 三部曲 ：**

   ```java
   // 1. new ReentrantLock();
   //2. lock.lock(); 枷锁
   代码放入 try  catch 中
   //3. finally =》 解锁
   ```

   **synchronized  和 lock 的区别**

   > 1.  synchronized  是关键字  ， lock  是个 java类
   >
   > 2. synchronized 是自动的，无法获取锁的状态 ， lock 可以知道是否获取了锁
   >
   > 3. synchronized 自动释放锁 ； lock 必须手动释放 ，不释放造成**死锁**
   >
   > 4. Synchronized 可重入锁，不可中断，非公平锁 ！  lock 可重入锁，可中断。非公平锁
   >
   > 5. synchronzed 适合少量代码 ， lock 适合大量代码
   >



7. > **生产者和消费者问题**	

   synchronized 的生产者和消费者问题   ： **线程间的通信**

   线程可以被唤醒，不会被通知、中断。。 所谓的虚假唤醒

   解决 ： 等待的方法应在循环中进行  。 将if 换成  ==while==

<img src="C:\Users\wangsw\AppData\Roaming\Typora\typora-user-images\image-20200905183234981.png" alt="image-20200905183234981" style="zoom: 50%;" />



​	Lock 版的生产者和消费者问题    ==Condition==

​                     	 <img src="C:\Users\wangsw\AppData\Roaming\Typora\typora-user-images\image-20200905183530004.png" alt="image-20200905183530004" style="zoom:67%;" />

> ​				**synchronzied 有着 wait  和 sleep**    
>
> ​				**lock 有着 await  和  signal     同时可以==精确唤醒==** 





> 8.  **判断锁的对象是谁？？**

==锁锁的到底是谁？==

对于==函数式接口== ， 可以使用 lambda 表达式  。

e.g   ==Ruunnable==  接口

<img src="C:\Users\wangsw\AppData\Roaming\Typora\typora-user-images\image-20200905184303025.png" alt="image-20200905184303025" style="zoom:67%;" />

1. ​	标准情况下，synchronized 锁的对象  是  ==谁调用锁的是谁==
2.  增加一个普通方法 (没有锁，不是同步方法)     
3. 两个对象调用同步方法
4. 锁静态方法static   ，static  锁的是 ==整个 class== 
5. 一个static  一个普通的锁同步方法    当代调用的时候，不是同一个锁



9. >  **集合类不安全**

   多个线程操作**ArrayList**  产生  ==CurrentModificationException==   //  ==并发修改异常==

   怎么解决呢？ 三种解决方案

   ==CopyOnWrite==   ==写入时复制==      

   多个线程调用的时候  ，读取的时候 CopyOf 拷贝一份， 写入的时候  可能会造成覆盖问题

   

   - ```java
     List<String> list = new Vector<>();  // vector 是安全的
     
     List<String> list = Collections.synchronizedList(new ArrayList<>());
         
     List<String> list2 = new CopyOnWriteArrayList<>();
     ```

   <img src="C:\Users\wangsw\AppData\Roaming\Typora\typora-user-images\image-20200905194119619.png" alt="image-20200905194119619" style="zoom:67%;" />

​		     同理 ==set== 也是会出现  ==并发修改异常==   

```java
Set<String> strings = Collections.synchronizedSet(new HashSet<>());
        
Set<Object> set = new CopyOnWriteArraySet<>();

```

 		==Map== 的解决方案   

```java
Map<String, String> map = new ConcurrentHashMap<>();
```



10. >  **callable 接口**

    与 runable 不同 ， 为另一个线程设计的  。  然而  runnable  没有返回值 ， 不抛出异常

    - 有返回值
    - 可以抛出异常
    - 方法不同  run / call 

<img src="C:\Users\wangsw\AppData\Roaming\Typora\typora-user-images\image-20200905202435453.png" alt="image-20200905202435453" style="zoom:67%;" />





### 		辅助工具类

11. > **CountDownatch**	

    计数器    Down  ==减法==

     ==一次性的，不能循环利用==![image-20200905203147449](C:\Users\wangsw\AppData\Roaming\Typora\typora-user-images\image-20200905203147449.png)

原理 ： 

   `countDownLatch.countDown();`

​	`countDownLatch.await();`      ==等待计数器归0 ，执行下面的操作==



> 12. **CyclicBarrier**

达到**共同的屏障**        朋友吃饭，有的早到，有的迟到，必须所有人 到了才开始

**一个线程组的线程需要等待所有线程完成任务后再继续执行下一次任务**

==可以循环利用==





13. >  **阻塞队列  BlockQueue**

    先进先出   使用 **多线程并发处理 ， 线程池**

    如果==队列满==了，必须阻塞

    如果==队列是空==的，必须阻塞等到生产

    可以一直往里面加



14. > **SynchronizedQueue  同步对列**

    不存储元素 ； 放进去一个元素==必须取出==才能再次put 进去



15. > **线程池**

      池化技术： 事先准备好一些资源，有人来直接用；用完还给我

      好处 ： 

    - **降低系统资源消耗**，通过重用已存在的线程，降低==线程创建和销毁造成的消耗==；
    - **提高系统响应速度**，当有任务到达时，通过复用已存在的线程，无需等待新线程的创建便能立即执行；
    - **方便线程并发数的管控**。因为线程若是无限制的创建，可能会导致内存占用过多而产生==OOM==，并且会造成cpu过度切换

    ​	

> ​         **创建线程的三大方法、七大参数、4个拒绝策略**

- 1. newFixedThreadPool    创建==指定线程数量==的线程池    如果线程数量达到最大数，将加入阻塞队列

     适用于<u>线程资源有限，数据量小</u>

  2. newCachedThreadPool    创建可缓存的线程池。无限扩大的线程池，数目为Integer.Max  容易==OOM==

     若长时间没有往线程池中提交任务，即线程空闲超过时间，线程自动终止。若有任务，重新创建。

     适用于<u>负载较轻，响应时间要求高</u>

  3. newSingleThreadExecutor : ==单线程==的线程池，保证任务的顺序进行

  4. newScheduledThreadPool ： 创建==可定期或延时==执行任务的线程池



- 七个参数

  本质上调用了 ThreadPoolExecutors

  ```java
  public ThreadPoolExecutor(  int corePoolSize   // 核心线程池大小
                              int maximumPoolSize, // 最大线程池数量
                              long keepAliveTime,  // 超时，没人调用线程就释放关闭
                              TimeUnit unit,  // 超时单位
                              BlockingQueue<Runnable> workQueue,  // 阻塞队列
                              ThreadFactory threadFactory, // 线程工厂，创建线程的   有默认的线程工厂
                              RejectedExecutionHandler handler) // 拒绝策略
  ```

  **先使用核心线程池，其余的人在阻塞队列中等待，当阻塞队列满了，就启用新的线程池； 直到线程池的数量超过 最大数量 ，此时启用 拒绝策略**



- 四大拒绝策略

  ThreadPoolExecutor.AbortPolicy  :  抛出异常，拒绝新的任务进来

  ThreadPoolExecutor.CallerRunsPolicy :   哪儿的哪儿去 ; 调用执行自己的线程运行任务。

  ThreadPoolExecutor.DiscardPolicy  ： 队列满了，不抛出异常，==直接丢弃==

  ThreadPoolExecutor.Discard==Old==estPolicy ： 队列满了，将==丢弃最早==的未处理的任务请求



9. > **volatile  轻量级的同步机制**

   保证可见性  禁止指令重排   ==不保证原子性==

   

   **JMM : Java内存模型**

   内存模型规定 所有==变量==存在==主内存==中 ， 每个==线程==有自己的==工作内存==（存储线程共享变量的==副本==） ， 线程对变量的操作必须在自己的工作内存中进行，而==不能直接操作==主内存中的变量。

   -  不同线程之间==无法直接==访问对方工作内存的变量    

   -  线程通信主要有两种 ； 一是  消息传递   二是   共享内存（java线程间的通信）

    <img src="https://upload-images.jianshu.io/upload_images/14923529-55ffcf1994434a48.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240" alt="img" style="zoom:67%;" />

   内存交互操作有==8种== ；read load use  assign write store  lock  unlock

   <img src="C:\Users\wangsw\AppData\Roaming\Typora\typora-user-images\image-20200906182449523.png" alt="image-20200906182449523" style="zoom: 67%;" />



- 保证可见性

  <img src="C:\Users\wangsw\AppData\Roaming\Typora\typora-user-images\image-20200906183012106.png" alt="image-20200906183012106" style="zoom:67%;" />

- ==不保证==原子性   

  怎么解决呢？ 

  可以加锁： synchronized  lock    也可以使用 `concurrent.atomic` 下的 原子类； ==保证原子性==

  原子类的底层跟操作系统挂钩； 直接在内存中修改！  ==**Unsafe类**== 是个特殊的存在



- 禁止==指令重排==

  指令重排 ： 写的程序，计算机==并不是==按照我们写的==顺序执行==的

  采用volatile  ： 由有==**内存屏障**== ， 可以避免指令重排现象



10. > **深入理解CAS**

    CompareAndSwap   比较并交换     底层是==**内存操作**==，效率很高

    ```java
    atomicInteger.compareAndSet(int expect , int update);   // 比较并交换
    ```

    **==unsafe   类==**    java无法操作内存 ， 底层调用C++操作内存



​	   CAS ： 比较当前的工作内存中的值和主内存中的值； 如果这个值是期望的值，就执行方法！  不是，就==**一直循环 (自旋锁)**==

> ​	CAS ：  ABA 问题

​		 解决ABA 问题  ， 引入**原子引用**  ，乐观锁的思想





