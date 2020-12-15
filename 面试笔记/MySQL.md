# MySQL

## 数据库分类

1. 关系型数据库 ： EXCEl一样，行和列
   - MySQL、Oracle
   - 表与表之间，行与列之间的关系进行数据存储



2. 非关系型数据库  {key: value}
   - Redis、MongDB
   - 对象存储，通过对象的自身属性来决定。  **==不断变化的==** 



## 数据类型

> 数值

-  **int   标准的整数       4个字节**

> 字符串

-  **varchar    可变字符串      定义长度**

> 时间

- **dateTime    YYYY-MM-DD  HH:mm:ss**
- **timestamp   时间戳   1970.1.1 - 现在的毫秒数**

> NULL

- **没有值**



## 数据引擎

- INNODB   默认使用

- MYISAM   早些年使用

  |            |    MYISAM    |    INNODB    |
  | :--------: | :----------: | :----------: |
  |  事务支持  |    不支持    |     支持     |
  | 数据行锁定 | 不支持  表锁 |     支持     |
  |  外键约束  |    不支持    |     支持     |
  |  全文索引  |     支持     |    不支持    |
  | 表空间较小 |     较小     | 约两倍MYSIAM |



## 联表查询（JOIN）

**leftJOIN 		 InnerJOIN   		RightJOIN**

左（去除交集）      交集                右（去除交集)



<img src="https://img-blog.csdnimg.cn/20181103160140252.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2h1YW5nX18y,size_16,color_FFFFFF,t_70" alt="img" style="zoom:80%;" />

> **查询参加考试的同学（学号、姓名·······）**
>
> ==**思路：分析查询的字段来自那些表**==
>
> ==**确定使用那种查询？  7种**==
>
> ==**确定交叉点（那些数据是相同的）**==
>
> ==**判断条件 ：学生表ID = 成绩表ID**==

```sql
Select s.studentNo,name,Subject,result   // 一定要指定取哪个表的ID，两个表都有ID，不然会报错
From student As s
INNER JOIN result AS r
On s.studentNO = r.studentNO
WHERE result IS NULL
```



## 事务

==要么成功，要么失败==

> 事务原则：**ACID**  :  原子性，一致性，隔离性、持久性
>
> 脏读，幻读

例子 ： 转账  A  800 (B:600) --> B   200   (B 800  A 600) 

**原子性**： 针对同一事务，==要么都成功，要么都失败==

**一致性：**==事务前后数据完整性保持一致==（最终一致性： 两个金额总和肯定是1400）

**持久性：**事务结束后，不能因外界原因导致数据丢失 。==事务一旦提交则不可逆==

​				事务没有提交，恢复原来状态

​				事务提交了，持久到数据库了，不可更改

**隔离性：**针对多个用户同时操作，C也给B转账，排除其他事务对其影响



**==事务的隔离级别：==**

脏读：一个事务读取了另一个事务未提交的数据

不可重复读：读取某行数据时，多次读取数据不一致（这不一定是错误，可能场合不对）

幻读：一个事务读到了**别的事务插入的数据**，导致前后不一致（一般是行影响）



## 索引

> 官方定义：  Index帮助MYSQL**==高效获取数据==**的**数据结构**。提取句子主干，就可以得到索引的本质

- **主键索引（primary Key）**
  - 唯一标示，不可重复，只有一个列作为主键
- **唯一索引(Unique Key)**
  - 避免重复列出现，唯一索引可以重复，多个列可以标志位唯一索引
- **常规索引(Kye/Index)**
  - 默认的，index或者可以关键字来设置
- **全文索引**



- **索引原则**
  - 索引不是越多越好 
  - 不要对经常变动的数据加索引
  - 小数据量不要加索引
  - **索引加在常用来查询的字段上！**

```sql
create INDEX name  on 表（字段）
```



> **==索引的数据结构==**

**Btree  : Innodb默认的数据结构**



## 如何设计数据库

关于数据库的设计

- 分析需求：分析业务和需要处理的数据的需求
- 概要设计：设计关系图  E-R图



## 数据库三大范式

- 第一范式 

  **原子性  ：保证每一列不可再分**

- 第二范式

  前提满足第一范式

  **每张表只描述一件事情**

- 第三范式

  满足1.2的前提下，每一列都要与**==主键直接相关==**，非间接相关

**规范性和性能问题：**

关联查询的表不得超过==**三张**==

- 考虑到商业化的需求（成本，用户体验！）   **数据库性能更加重要**
- 考虑性能的时候，需要适当的考虑规范性
- 故意给表增加冗余的字段。（从多表变成单表）
- 故意增加一些计算列（）



## 数据库连接池

避免频繁的开启关闭连接，浪费资源

**池化技术：**准备预先资源，过来就连接事先准备好的





# Redis

NoSql   非关系型数据库   存储数据的格式并不是固定的，   redis 利用key ,value 的 键值对来控制

Redis 一秒 写8万次 ，读 11万次

数据类型多种多样，**不需要**提前设计数据库。

端口号 ： **6379**

> **Redis是单线程的**！

Redis 是 很快的，基于内存操作，CPU不是Redis性能瓶颈，Redis的瓶颈是机器的内存和网络的带宽，既然能使用单线程,就用单线程。

**为什么单线程还这么快？**

1. 误区 ：  高性能的服务器一定是多线程？
2. 误区2 ： 多线程（CPU会==切换上下文==，也是需要时间的！！！）一定比单线程效率高？

**==核心：==**  redis 是将所有的数据放在==内存中==操作，所以使用单线程去操作效率就是最高的。 多线程（CPU上下文会切换：耗时的操作！！）。对于内存系统来说，如果没有上下文切换，效率就是最高的。



## 五大基本数据类型

Redis 是内存中的数据结构存储系统 ： 可以用作 ==数据库  缓存  消息中间件==MQ

支持的数据类型 ： **strings，hashes,  lists,  sets,  sorts sets**  



### strings(字符串）

```bash
 set key1 v1  # 设置值
 get key1  #  获取值
 EXIST key1  # 判断某个key是否存在
 APPEND key1 "hello"  # 追加字符串   v1hello   如果这个key不存在，就相当于set  key
 ###########################################################
 # 步长  自增 自减

set view 0
incr view  #  自增1
INCRBY view 10  # 加10
decr view  # 自减1
DECRBY view 10 # 自减10
## #####################################  获取一定长度的字符串
set key1 "hello,wsw"
get key1
GETRANGE key1 0 3 #  截取字符串 【0，3】  输出  hell
GETRANGE key1 0 -1 #  获取全部字符串  hello，wsw
###################################  替换字符串
SETRANGE key1 1 XXX ## 输出 hxxxo,wsw
################### 设置过期时间
#setex	设置过期时间
#setnx   不存在再设置
setex key3 30 "hello"  设置keys 30s后 过期
setnx mykey "redis"  # 如果mykey不存在，创建mykey ； 存在就创建失败
######  批量设置 和 获取值
mset k1 v1 k2 v2 k3 v3
mget k1 k2 k3
msetnx k1 v1 k4 v4  ## k1 已经存在了，k1设置失败 ， 进而k4也失败了。 是原子性操作，要么都成功，要么都失败 
```

订阅数 ，粉丝数，浏览量  



### list

```bash
## list 操作
## 可以看成 栈 或者队列
所有的命令以L开头
```

实际上是个链表，在开头、尾部插入元素

实现功能 ： 消息队列



### Set

数字集合类 ： 差集 交集  并集

### Hash

key - map 集合



### Zset（有序结合）

在set基础上增加 值 ；  可以用来排序

加了权重值，比如**微博热点排序**







## Redis 事务

本质： 一组命令的集合！ 在事务的执行过程中，会按照顺序执行

**一次性、顺序性、排他性**

Redis==单条命令==保证原子性的，但是事务==**不**==保证原子性。





## Redis 持久化

面试和工作，持久化都是重点

==**RDB   AOF**==

> **什么是RDB**

​	在指定的时间间隔内，将内存中的数据集快照写入到磁盘中，恢复时将快照文件直接读到内存中。

​	Redis会单独创建一个子进程来持久化，会先将数据写入到一个临时RDB文件中，快照写入完成后用临时文件替换掉上次的持久	化文件。RDB要比AOF更加的==高效==。   默认是使用==**RDB持久化**==



​	**缺点是** ： 最后一次持久化的==数据可能会丢失==。如果宕机了，数据就丢失了

​	优势： 适合大规模的数据恢复； 对数据的**完整性要求不高**



> **什么是AOF**

将==**所有的命令**==全部记录下来， 恢复的时候将文件内容的所有命令全部执行一遍；只可以追加文件，不能改写文件！！

效率会比RDB低。

**优势** : 数据不会丢失，完整性好

**缺点**： 相对于RDB，AOF的数据文件要大很多，恢复的速度很很慢； ==**默认的是RDB**==



> **主从复制**

==**主从复制**== ： 将一台Redis服务器(==master==)的数据复制到其他Redis服务器(==salve==)。

==数据的复制是单向的，只能由主节点到从节点==。  master以==**写**==为主；salve以==**读**==为主

**一个master可以有多个salve ， 一个salve只能有一个master**

主从复制，读写分离 ： 减缓数据库的压力！  80%都是读操作

**一主二从**

> **主从复制的主要作用 ：**
>
> - **数据冗余 ： 热备份数据 ，是持久化的一种数据冗余**
> - **故障恢复 ： 主节点故障，从节点提高服务，实现快速的故障恢复**
> - **负载均衡： 主从复制，读写分离，分担服务器的负载**
> - **集群 ： 主从复制 是哨兵和集群能够实施的基础**

> **复制的原理** 

Salve启动成功连接到master后会发送sync同步命令

Master收到命令，启动存盘进程，收集所有修改数据集命令，进程结束后，==将数据文件传给salve ，并完成一次同步==【全量复制】

【增量复制】：Master继续将数据修改命令集依次传给slave ，完成同步



> **哨兵模式（自动选举主机）**

**概述** ： 当主服务器宕机之后，需要手动把一台服务器切换为主服务器，这需要人工干预，耗时耗力。

这时，优先考虑哨兵模式。自动将从服务器变为主服务器



> **缓存穿透 击穿 和 雪崩**

穿透 ：查询数据，缓存中没有，去查询数据库。当很多个用户同时请求时，数据库可能直接宕机。这时候发生了【==**缓存穿透**==】

> **解决方案 **

**布隆过滤器** ： 检查数据库中是否有这个数据

是种数据结构，将数据以**hash形式存储**，先检验是否有这个数据，避免对底层存储系统的压力



缓存击穿 ： 多个用户对同一个热点key数据进行访问，当这个key失效的瞬间，直接请求数据库，压力很大 【微博热搜】

> **解决方案**

**设置热点数据永不过期**

加互斥锁---**分布式锁** ： 保证只有一个线程去查询数据，没有获得锁的线程等待！

> **缓存雪崩**

 在某一时期，**==大面积的key集体失效==**，导致请求直接打在数据库上；瞬间把数据库击垮



> **解决方案**

**限流降级** ： 缓存失效之后，通过加锁或者队列来请求数据库的线程数量。一个线程查询，其他线程等待。

**数据预热**： 在部署之前，将一些数据预先访问一遍，大部分数据可能加入缓存中。设置key的过期时间不同，【随机失效时间】，防止key的大面积同时失效！！





# SQL语句

==**查询**==语句        **重点！！**

按照顺序来  ：  form    where    group by    select    having     order by     limit

`select .... form .... where .... group by ...   having  ... order by ... limit ....`

​          **输出结果       哪张表      条件(过滤)         分组            条件(过滤)            排序        限定个数**

select  ==*****== (代表所有的属性)

select  name(只要name的属性)



> **where  和  group  by 的区别**
>
> - ==where==  条件过滤的
> - ==group by==  用来分组的
> - group by  ... ,......   后面跟多个属性 ，多个属性相等的会被分为一组   
> - 看到每一个的==**每一**==就要用到**group by**

```sql
id  name  classId                 
1	wang	1                     1	wang	1
2	jia	    2                     6	guo	    1
3	shi	    3                     7	ding	1
4	admin	2                     10 hua    1
                                  2	jia	    2
5	zhou	2                     4	admin	2 
6	guo	    1                     5	zhou	2 
7	ding	1                     3	shi	    3        
8	liu	    3                     8	liu	    3
9	xu	    3                     9	xu	    3
10	hua	    1

group by  分组
分组后遇到 select ， 输出的代表数这一组的第一行数据
SELECT * from student GROUP BY classId;  将学生按照 classId 进行分组，并将【每一组】的第一个输出,其余不输出
输出 ：
1	wang	1
2	jia	    2
3	shi	    3

SELECT * from student WHERE id%2=0 GROUP BY classId;
按照上述的查询规则，关键词顺序  from  where  group by  select 
2	jia	 2
6	guo	 1
8	liu	 3
```



**聚合函数**

- `count()`  统计数据的    `distinct`

> **( ) 里面能放什么？**
>
> count(==1==)       count(==字段名==)【count(name)】    count( ==distinct== name)    distinct 代表不重复
>
> 这两者的==**区别**==？
>
> - 如果 **name** 中有null 值，不统计 null 的数据；会统计重复的数据
> - ==distinct== 不统计**==重复==**的数据



```sql
SELECT COUNT(1),classId from student GROUP BY classId;
每个分组有几条数据
count(1)  classId
  4	        1
  3	        2
  3	        3
  
SELECT COUNT(DISTINCT name),classId,name from student GROUP BY classId;
重复的数据不统计
 3	1	wang
 3	2	jia
 3	3	shi
```

count（1）:  在结果中 统计数据 ；  classId = 1 ，有4条数据

- `sum`    **求和**

  > **和count 类似**    sum( int 类型的)
  >
  > sum(1)  == count（1）
  >
  > **sum(id)**
  >
  > ```java
  > SELECT sum(id),classId,name from student GROUP BY classId; // 会把所有的额
  > sum(id)   classId  name
  > 24	        1	    wang     id=  1 6 7 10
  > 11	        2	  jia        id=  2 4 5 
  > 20	        3	  shi        id = 3  8 9 
  > ```
  >
  > **sum(distinct  id)**    **==去除重复==**的数据

- `max`   最大值

- `min`   最小值

- `avg`  平均值

- `group - conact`   字符串的集合



`having` 的过滤  

```sql
SELECT * from student GROUP BY classId;  将学生按照 classId 进行分组，并将【每一组】的第一个输出,其余不输出
输出 ：
1	wang	1
2	jia	    2
3	shi	    3
===============================================
select 输出后 ，如果还想过滤 ，就使用 having
===============================================
eg.  查询出 每个班中人数大于3 的班级号
SELECT COUNT(DISTINCT name),classId from student GROUP BY classId HAVING COUNT(DISTINCT name)>3;
// 给COUNT(DISTINCT name) 取个别名 
SELECT COUNT(DISTINCT name) AS n,classId from student GROUP BY classId HAVING n > 3;
// 结果中不显示 count的内容
SELECT classId from student GROUP BY classId HAVING COUNT(DISTINCT name) > 3;
// 让结果 降序 DESC  默认是升序
SELECT classId from student GROUP BY classId HAVING COUNT(DISTINCT name) > 2 ORDER BY classId desc;
```

`order By`  排序    

ORDER BY  字段名  【默认是升序】   字段名可以是**多个** ， 当前一个有==重复==的时候，按照**后面的排序**

ORDER BY  字段名  ==**DESC**==  降序



`case when`  是放在**==select==** 后面的

> ```sql
> SELECT  id，name，（ case when classId = 20  THEN  6  WHEN  classId = 6  THEN 20  Else classId  END） AS  A 
> FROM stedent；
> ==============================================
> 将 classId = 20 的变为 6 ； classId =6的变为  20
> ```
>

<img src="C:\Users\wangsw\AppData\Roaming\Typora\typora-user-images\image-20200929095623940.png" alt="image-20200929095623940" style="zoom: 50%;" />

<img src="C:\Users\wangsw\AppData\Roaming\Typora\typora-user-images\image-20200929100648532.png" alt="image-20200929100648532" style="zoom: 67%;" />

==**重要来理解**==

虚拟出一列 ， 从而虚拟出好多列 。 每一列的名字是 **基础  爬  SQL**

> ```sql
> FROM subject  GROUP BY name SELECT   
> 怎么虚拟出一列
> (case when subject='基础' THEN score else NULL) AS '基础'   
> (case when subject='爬' THEN score else NULL) AS '爬'
> (case when subject='SQL' THEN score else NULL) AS 'SQL'
> 上述的意思是 ：虚拟这一列，除非=‘基础’，其他全是 null 
> 加上 max 的意思是 ： 这一虚拟列最大的 
> =======================================================
> SELECT name , max(case when subject='基础' THEN score else NULL END) AS '基础' ,
> max(case when subject='爬' THEN score else NULL END) AS '爬' ,
> max(case when subject='SQL' THEN score else NULL END) AS 'SQL' 
> FROM SUBJECT GROUP BY name;
> ```



## 七大 ==JOIN== 连接

​               **leftJOIN 		 InnerJOIN   		RightJOIN**

​                左（去除交集）      交集                右（去除交集)

<img src="https://img-blog.csdnimg.cn/20181103160140252.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2h1YW5nX18y,size_16,color_FFFFFF,t_70" alt="img"  />

<img src="C:\Users\wangsw\AppData\Roaming\Typora\typora-user-images\image-20200929102314179.png" alt="image-20200929102314179" style="zoom: 50%;" />



**problem :  id =1 学生所在班级的名称和班主任是？ ** 多表联合JOIN查询

==student  JOIN  class==

<img src="C:\Users\wangsw\AppData\Roaming\Typora\typora-user-images\image-20200929103317629.png" alt="image-20200929103317629" style="zoom:50%;" />

<img src="C:\Users\wangsw\AppData\Roaming\Typora\typora-user-images\image-20200929103356974.png" alt="image-20200929103356974" style="zoom:50%;" />

将两张表的数据结合在一起生成一张表 ， 总共有多少条数据?    **5*2 = ==10==** 

所以，要对**==JOIN==** 做些操作！  加上  ON classID = class.id 

```sql
SELECT * FROM student s JOIN class c ON classId = class_id ;  // 两个表拼成一张表了
SELECT id,s.name,classId,manager FROM student s JOIN class c ON classId = class_id WHERE id=1;
查询 id= 1 的 班主任的名字
```

- > **left JOIN 和 JOIN 的区别**
  >
  > -  left JOIN 必须有 ==ON== 
  > - 会检查==左边表==的数据是否都==包含在新生成==的表中；是 -- 与JOIN没区别   否 -- 用**==NULL==**与不包含的行组成新行加入新表
  > - left JOIN两边表是==**不能**==交换的
  > - <img src="C:\Users\wangsw\AppData\Roaming\Typora\typora-user-images\image-20200929152940705.png" alt="image-20200929152940705" style="zoom:50%;" />
  >
  > ```sql
  > SELECT s.name  FROM student s LEFT JOIN class c ON classId = class_id WHERE c.class_id is NULL; 
  > // 查询哪些同学没有班级  
  > ```
  >
  > 判断一个值为NULL  使用 ==**is NULL**==

-  right JOIN     

  ```sql
  B right JOIN A = A left JOIN B
  ```



## 练习

<img src="C:\Users\wangsw\AppData\Roaming\Typora\typora-user-images\image-20200929161523921.png" alt="image-20200929161523921" style="zoom:67%;" />

1. 查询“01”课程比“02”课程成绩高的学生的信息及课程分数

   ```sql
   --分析 ：
   --在 SC成绩表中，需要比较两行之间的数据，必须 自己 JOIN 自己 比较数据只能在同一行进行
   SELECT c1.name ,c1.score AS '01分数', c2.score AS '02分数' 
   FROM (SC c1 JOIN SC c2 ON c1.SID = c2.SID AND c1.CID = '01' AND c2.CID = '02') JOIN student s ON c1.SID = s.SID
   WHERE c1.score > c2.score ;
   
   -- 2. 查询平均成绩大于等于60分的同学的学生编号和学生姓名和平均成绩
   FROM
   GROUP By
   ```

   









## 整理回顾

**查询的SQL的结构是**

SELECT ...  FROM  ...  WHERE ...  GROUP BY ...  HAVING ...  ORDER BY....  LIMIT....

**写的顺序是**

FROM ....  WHERE  ... GROUP BY ...  SELECT ...   HAVING ...  ORDER BY ..  LIMIT....



**FROM的作用** 

FROM table_name ， 将表的数据获取过来

JOIN....   

**WHERE的作用**

对 FROM 中 获取的数据进行过滤【==符合条件==的留下】 ，AND  OR   !=

**GROUP BY**

用来分组  group  by  classId   按照 classId 进行分组

分组后遇到 select ， 输出的代表数这一组的第一行数据











































