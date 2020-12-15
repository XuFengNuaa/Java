# Code总结

## 二分查找

- **寻找一个数**
- **寻找左右边界**

```java
int binarySearch(int[] nums, int target) {
		int left = 0, right = ...;
		while(...) {
			int mid = left + (right - left) / 2;
			if (nums[mid] == target) {
						...
				} else if (nums[mid] < target) {
					left = ...
				} else if (nums[mid] > target) {
					right = ...
				}
		}
			return ...;
```

为防止溢出，==mid = left + (right - left) / 2;==

1. > **寻找一个数,存在返回索引**

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

 ==重点是== ： while退出的条件   **==l <= r==**



2. > **寻找边界条件**

   上述问题有个局限性，若有多个重复的数    

   ```java
   nums = [1,2,2,2,3]	target = 2 ;
   ```

   寻找到2就退出循环了 ， **无法找到 2 的左边界 `索引 2`  ，右边界 `索引 3`**

   

   - **寻找==左==边界！ **   全部闭区间     说明  **小于**target的值有 ==left== 个

```java
int right = nums.length - 1; // 注意
	while(left <= right) {  // 注意
		int mid = left + (right - left) / 2;
```

```java
	if (nums[mid] < target) {
			left = mid + 1;
        
	} else if (nums[mid] > target) {
        
			right = mid - 1;
        
	} else if (nums[mid] == target) {
            // 别返回，锁定左侧边界
            right = mid - 1;
	}
}
		//  注意返回的值！！  越界问题  
		//	目标数比所有数都大问题
	if (left >= nums.length || nums[left] != target)
			return -1;

		return left;
}
```



-  **寻找==右==边界!    **       **大于**target的数有 ==right==个

```java
int right = nums.length - 1; // 注意
	while(left <= right) {  // 注意
		int mid = left + (right - left) / 2;
```

```java
	if (nums[mid] < target) {
			left = mid + 1;
        
	} else if (nums[mid] > target) {
			
        right = mid - 1;
        
	} else if (nums[mid] == target) {
		// 别返回，锁定右侧边界
		left = mid + 1;
	}
}
		//  注意返回的值！！  越界问题  
		//	目标数比所有数都小问题
	if (right < 0 || nums[right] != target)   改动左边界
			return -1;

		return right;  返回right
}
```



**==split("\\\s+")==**    :   中间多个空格分割

char字符运算 ： char+char（int）  转化为int    ==（'9' - '0') = 9==



### 数字中1出现的次数

https://leetcode-cn.com/problems/1nzheng-shu-zhong-1chu-xian-de-ci-shu-lcof/solution/mian-shi-ti-43-1n-zheng-shu-zhong-1-chu-xian-de-2/

<img src="C:\Users\wangsw\Desktop\笔记\刷题笔记\1.png" style="zoom: 67%;" />

<img src="C:\Users\wangsw\Desktop\笔记\刷题笔记\2.png" alt="2" style="zoom: 67%;" />

<img src="C:\Users\wangsw\Desktop\笔记\刷题笔记\3.png" alt="3" style="zoom: 67%;" />

```JAVA
讲解：
 0010 - 2219  十位上2的个数
 0010-0019   10个
 0110-0119	 10个
 0210-0219 。。。。。。  所以 是 23*10 = 230
```

```JAVA
public int countDigitOne(int n) {
        // 按照位数来
        int num = 0;  // 记录总数
        long i = 1; // 从个位开始
        while(n/i !=0){
            long high = n/(i*10); // 高位
            long cur = (n/i) % 10; // 当前位
            long low = n-(n/i)*i; // 低位
            if(cur ==0)
                num += high*i;
            else if(cur ==1)
                num += high*i+ low+1;
            else
                num += (high+1)*i;
            i *=10;
        }

        return num;
    }
```



## 链表操作

增删改查 



## 动 态 规 划

- 列==动态转移方程==
- 初始条件以及子问题的迭代
- 用==dp数组记录==每个值

```java
for 状态1 in 状态1的所有取值：
	for 状态2 in 状态2的所有取值：
		for ...
		dp[状态1][状态2][...] = 择优(选择1，选择2...)
```

### 背包问题

#### ==0 1 背包==

每个物品只能拿一次

```java
动态转移方程  dp[j] = MAx(dp[j],dp[j-w[i]] + c[i]);  转化为一维数组求解
for(int i =1;i < m+1;i++){
    这边需要倒着推，因为 i+1需要依赖前面的数据， 否则会覆盖掉
    for(int j = n ;j>=0;j--){
        if(j>=w[i])
        	dp[j] = max(dp[j],dp[j-w[i]] + c[i]); 
    }
}
```



#### ==完全背包==

每个物品可以无限拿

```java
与 0 1背包不同的是 可以拿无限次
 第一种解法(朴素法)
    for(int i=1;i < m+1;i++){
        for(int j=n;j>=1;j--){
           for(int k=0;k<= j/w[i];k++){
               dp[i] = max(dp[j],dp[j-k*w[i]] + k*c[i]);
           }
        }
    }

第二种 ： 
  for(int i =1;i < m+1;i++){
    这边是正着来，需要用到新的数据
    for(int j = 0 ;j<=n;j++){
        if(j>=w[i])
        	dp[j] = max(dp[j],dp[j-w[i]] + c[i]); 
    }
} 
```



#### ==多重背包==

每个物品规定数量  S[ i ]

```java
for(int i=1;i < m+1;i++){
        for(int j=n;j>=1;j--){
           for(int k=0;k<= S[i];k++){
               if(j>=k*w[i])
               		dp[i] = max(dp[j],dp[j-k*w[i]] + k*c[i]);
           }
        }
    }
```



### 买卖股票

买卖股票 1 

<img src="C:\Users\wangsw\AppData\Roaming\Typora\typora-user-images\image-20200807152732244.png" alt="image-20200807152732244" style="zoom:67%;" />

```java
 动态转移方程 ： 第i天卖出的钱和i-1天比较  当天的钱 ： price[i] - 前i天的股票最低价
 dp[i] = max(dp[i-1],price[i] - min)
     
 public int maxProfit(int[] prices) {
        int n = prices.length;
       /* int res = 0;
        for(int i=0;i < n;i++){
            for(int j=i+1;j<n;j++){
                res = Math.max(res,prices[j]-prices[i]);
            }
        } */
        int res = 0;
        int min = Integer.MAX_VALUE;
        for(int i=0;i < n;i++){
            min = Math.min(min,prices[i]);
            res = Math.max(res,prices[i]-min);
        }
        return res;
    }
```



买卖股票 2



### 斐波那契数列

#### 198 . House Robber (Easy)

```java
// 相邻之间不能够偷盗
偷了最后最后一个，前一个不能偷
不偷最后一个，等于偷了前一个
// dp[i] = max{dp[i-1] , dp[i-2] + num[i]}
    public int rob(int[] nums) {
    int pre2 = 0, pre1 = 0;
    for (int i = 0; i < nums.length; i++) {
        int cur = Math.max(pre2 + nums[i], pre1);
        pre2 = pre1;
        pre1 = cur;
    }
    return pre1;
}
```

#### 213.加强版打家劫舍

(环形房子，**偷第一个不能偷最后一个**)

```java
// 偷最后一个，不能偷第一个

Max{rob(nums , 0,n-1),rob(nums , 1 , n)}

public int rob(int[] nums,int left , int right) {
    int cur = 0, pre = 0,dp =0
    for (int i = left; i <right;i++){
        dp = Math.max(pre + nums[i], cur);
        pre = cur;
        cur = dp;
    }
    return cur;
}

```

#### 信件错拍  

---  有N个信和信封 ，被打乱 ，求错误装信方式的数量

<img src="C:\Users\wangsw\AppData\Roaming\Typora\typora-user-images\image-20200810171000734.png" alt="image-20200810171000734" style="zoom:50%;" />

​															对于第N个信件 ， K 装进 N ， N的装进 K

**两者互换，两个都装正确 ， 即 dp[i] = dp[i-2] *（i-1)** 

<img src="C:\Users\wangsw\AppData\Roaming\Typora\typora-user-images\image-20200810171015464.png" alt="image-20200810171015464" style="zoom:50%;" />

​														K 和 N的都装进别的邮箱了 ， **假设互换一个**, K 装正确了

<img src="C:\Users\wangsw\AppData\Roaming\Typora\typora-user-images\image-20200810171404456.png" alt="image-20200810171404456" style="zoom:50%;" />

dp[i] = (i-1)*dp[i-1]*

```java
dp[i] = (dp[i-1] + dp[i-2]) * (i-1)
```

#### 生产母牛

 假设农场中成熟的母牛每年都会生 1 头小母牛，并且永远不会死。第一年有 1 只小母牛，从第二年开始，母牛开始生小母牛。每只小母牛 3 年之后成熟又可以生小母牛。给定整数 N，求 N 年后牛的数量。

```java
dp[i] = dp[i-1] + dp[i-3];
```



### 矩阵路径型

#### 64  Minimum Path Sum (Medium)

```html
[[1,3,1],
 [1,5,1],
 [4,2,1]]
Given the above grid map, return 7. Because the path 1→3→1→1→1 minimizes the sum.
```

题目描述：求从矩阵的左上角到右下角的最小路径和，每次只能**向右和向下**移动。

```java
// 1. 用二维数组表示dp[i][j] 经过grid[i][j]的最小sum
class Solution {
    public int minPathSum(int[][] grid) {
        // 定义 dp[i][j]
        if(grid == null || grid.length ==0 || grid[0].length==0){
            return 0;
        }
        int m = grid.length, n = grid[0].length;
        int[][]dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(i==0 && j==0) continue;
                if(i == 0) dp[i][j] = dp[i][j-1] + grid[i][j];
                else if(j ==0) dp[i][j] = dp[i-1][j] + grid[i][j];
                else 
                    dp[j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
            } 
        }
        return dp[n-1];
    }
}

// 2. 降维，使用一维数组
class Solution {
    public int minPathSum(int[][] grid) {
        // 定义 dp[j] // 每一行用dp[j]数组来表示
        if(grid == null || grid.length ==0 || grid[0].length==0){
            return 0;
        }
        int m = grid.length, n = grid[0].length;
        int[]dp = new int[n];
        dp[0] = grid[0][0];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(i==0 && j==0) continue;
                if(i == 0) dp[j] = dp[j-1] + grid[i][j];
                else if(j ==0) dp[j] = dp[j] + grid[i][j];
                else 
                    dp[j] = Math.min(dp[j], dp[j-1]) + grid[i][j];
            } 
        }
        return dp[n-1];
    }
}
```

#### 62 Unique Paths (Medium)

题目描述：统计从矩阵左上角到右下角的==路径总数==，每次只能向右或者向下移动。

<div align="center"> <img src="https://cs-notes-1256109796.cos.ap-guangzhou.myqcloud.com/dc82f0f3-c1d4-4ac8-90ac-d5b32a9bd75a.jpg" width=""> </div><br>

```java
//  只能向下或者向右走 ，也即到达某一个地点的路只有两条，从他的上面或者左边走
到达 grid[i][j] 可以从grid[i-1][j]  和  grid[i][j-1]走
class Solution {
    public int uniquePaths(int m, int n) {
        if(m == 0 || n ==0)  return 0;
        int[] dp = new int[n];
        dp[0] = 1;
        for(int i=0;i < m;i++){
            for(int j =0;j < n;j++){
                if(i==0 && j==0) continue;
                if(i ==0)  dp[j] = dp[j-1];
                else if(j==0) dp[j] = dp[j];
                else dp[j] = dp[j] + dp[j-1];
            }
        }
        return dp[n-1];
    }
}
```



### 数组区间

#### 303  Range Sum Query - Immutable (Easy)

```html
Given nums = [-2, 0, 3, -5, 2, -1]

sumRange(0, 2) -> 1
sumRange(2, 5) -> -1
sumRange(0, 5) -> -3
```

转化为 前 j 个减去 前 i 个

```java
public NumArray(int[] nums) {
        sums = new int[nums.length + 1];
        for(int i=1; i <=nums.length;i++){
            sums[i] = sums[i-1] + nums[i-1];
        }
    }
    
    public int sumRange(int i, int j) {
        return sums[j+1] - sums[i];

    }
```



#### 413 数组等差数列子区间的个数   ==**连续的**==

```html
A = [0, 1, 2, 3, 4]

return: 6, for 3 arithmetic slices in A:

[0, 1, 2],
[1, 2, 3],
[0, 1, 2, 3],
[0, 1, 2, 3, 4],
[ 1, 2, 3, 4],
[2, 3, 4]

dp[2] = 1
    [0, 1, 2]
dp[3] = dp[2] + 1 = 2
    [0, 1, 2, 3], // [0, 1, 2] 之后加一个 3
    [1, 2, 3]     // 新的递增子区间
dp[4] = dp[3] + 1 = 3
    [0, 1, 2, 3, 4], // [0, 1, 2, 3] 之后加一个 4
    [1, 2, 3, 4],    // [1, 2, 3] 之后加一个 4
    [2, 3, 4]        // 新的递增子区间
```

```java
// 定义dp[i]为以第A[i]结尾组成的等差数列
int[] dp = new int[n];
for(int i=2;i<n;i++){
    if(A[i]-A[i-1] == A[i-1] - A[i-2])
        dp[i] = dp[i-1] +1;
}
// 求 dp数组的总和
for(int ss : dp)
    count += ss ;
return count;
```



### 分割整数 

#### 343  整数拆分  

类似于剪绳子  每段尽量分 ==**3**==

题目描述：For example, given n = 2, return 1 (2 = 1 + 1); given n = 10, return 36 (10 = 3 + 3 + 4).

```java
1. 
     // 类似于 剪绳子 尽可能的多分3  数学分析法
        if(n==2) return 1;
        if(n==3) return 2;
        int res = 1;
        while(n > 4){
            n = n-3;
            res *= 3;
        }
        res *= n;
        return res; 
2. 
    // 动态规划
        int[] dp = new int[n+1];
        dp[1] = 1;
        for(int i=2;i<=n;i++){
            for(int j=1;j <i;j++){  // 循环多次，要把最大的取出来
                dp[i] = Math.max(dp[i], Math.max(j*dp[i-j],j*(i-j)));
            } 
        }
        return dp[n];
```



#### 按平方数来分割整数

279  Perfect Squares(Medium)

<img src="C:\Users\wangsw\AppData\Roaming\Typora\typora-user-images\image-20200812112251785.png" alt="image-20200812112251785" style="zoom: 67%;" />

```java
// 动态规划 完全背包问题  可以无限取东西
public int numSquares(int n) {
        // 定义 dp[i][j];  像完全背包的题目
        int[] dp = new int[n+1];
        for(int i=0;i<n+1;i++) // 初始化值
            dp[i] = i;
        int a = (int)Math.sqrt(n); // 开方
        
        int[] num = new int[a+1];
        
        for(int i=0;i<a+1;i++){  // 存储完全平方值
            num[i] = i*i;
        }
        
        for(int i=0;i < a+1;i++){
            for(int j=0;j <= n;j++){
                if(j >= num[i])
                    dp[j] = Math.min(dp[j], dp[j-num[i]]+1);
            }
        }
        return dp[n];
    }
```

#### 分割整数构成字母字符串

91\. Decode Ways (Medium)

<img src="C:\Users\wangsw\AppData\Roaming\Typora\typora-user-images\image-20200812112818742.png" alt="image-20200812112818742" style="zoom:67%;" />

```java
// 主要看最后一位是不是0 ，最后两位是不是小于27   加强版上楼梯
class Solution {
    public int numDecodings(String s) {
        // 定义dp[i]为 以i结尾的有几个
        if(s.charAt(0) =='0') return 0;
        int[] dp =new int[s.length()];
        dp[0] = 1;
        for(int i=1;i<s.length();i++){
            // 类似上楼梯约束版
            if(s.charAt(i) != '0'){
                dp[i] = dp[i-1];
            }
            int sum = 10*(s.charAt(i-1)-'0')+(s.charAt(i)-'0');
            if(sum >=10 && sum <=26){
                if(i==1) dp[i]++;
                else dp[i] += dp[i-2];
            }
        }
        return dp[s.length()-1];
    }
}
```



### 字符串问题

#### 最小编辑距离

<img src="C:\Users\wangsw\AppData\Roaming\Typora\typora-user-images\image-20200825104223046.png" alt="image-20200825104223046" style="zoom: 67%;" />

分析问题：

```html
总共几种情况   沿用数学归纳法
1. 如果word[0...i-1] 到 word[0...j-1] 的变换需要消耗k步，那 word1[0..i] 到 word2[0..j] 的变换需要几步呢？
	如果 word[i] == word[j] 就需要 k步。 否则需要k+1 步 ，替换就行了
2. 如果word[0...i-1] 到 word[0...j] 的变换需要消耗k步，那 word1[0..i] 到 word2[0..j] 的变换需要几步呢？
	删除一个字母即可 ， k+1 步
3. 如果word[0...i] 到 word[0...j-1] 的变换需要消耗k步，那 word1[0..i] 到 word2[0..j] 的变换需要几步呢？
	添加一个字母即可
```

```java
class Solution {
    public int minDistance(String word1, String word2) {
        // 明确dp的含义，定义dp函数  dp[i][j] 代表s1[0..i]和时[0...j]需要几步
        int m = word1.length(),n = word2.length();
        int[][] dp = new int[m+1][n+1];
        for(int i =0;i<m+1;i++){
            for(int j=0;j<n+1;j++){
                if(i==0) dp[0][j] = j;
                else if(j==0) dp[i][0] = i;
                else if(word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else {
                    dp[i][j] = Math.min(dp[i-1][j-1],Math.min(dp[i][j-1],dp[i-1][j])) +1;
                }
            }
        }
        return dp[m][n];
    }
}
```





## 滑动窗口

1. 最小覆盖子串

<img src="C:\Users\wangsw\AppData\Roaming\Typora\typora-user-images\image-20200810100154948.png" alt="image-20200810100154948" style="zoom:50%;" />

```java
class Solution {
    public String minWindow(String s, String t) {
            // 滑动窗口
        Map<Character, Integer> needs = new HashMap<>();
        Map<Character, Integer> windows = new HashMap<>();
        for(int i=0;i<t.length();i++) {
            // 先把出现的字符存入 needs
            needs.put(p.charAt(i),needs.getOrDefault(p.charAt(i),0)+1);
        }
        int left = 0,right =0;
        int match = 0; // 符合t的个数
        int start = 0;
        int end = Integer.MAX_VALUE;
        while (right < s.length()){
            char a = s.charAt(right);
            if(needs.containsKey(a)){
                // 加入到窗口的map中
               windows.put(a,wins.getOrDefault(a,0)+1);
               // if(windows.get(a) ==needs.get(a))
               if(windows.get(a).equals(needs.get(a)))  // Integer装箱 用== 超过-127--128会出错，建议用equals
                   // 字符出现的次数符合要求
                    match++;
            }
            right++;
				// 满足要求了
            while (match == needs.size()){
                // 更新结果
                if(right - left < end){
                     start = left;
                     end = right -left;
                }
                char b = s.charAt(left);
                if(needs.containsKey(b)){
                    windows.put(b,windows.get(b)-1); // 移除
                    if(windows.get(b) < needs.get(b))
                        match--;
                }
                left++;
            }
        }
        return end == Integer.MAX_VALUE ? "" : s.substring(start,end+start);
    }
}
```



2. 字符串中所有的 字母异位子串

   ```java
   class Solution {
       public List<Integer> findAnagrams(String s, String p) {
           List<Integer> list = new ArrayList<>();
           Map<Character,Integer> needs = new HashMap<>();
           Map<Character,Integer> wins = new HashMap<>();
           for(int i=0;i<p.length();i++){
               needs.put(p.charAt(i),needs.getOrDefault(p.charAt(i),0)+1);
           }
           int left=0,right=0,match=0,start=0;
           while(right < s.length()){
               char a = s.charAt(right);
               if(needs.containsKey(a)){
                   wins.put(a,wins.getOrDefault(a,0)+1);
                   if(wins.get(a).equals(needs.get(a))){
                       match++;
                   }
               }
               right++;
   
               while(match == needs.size()){
                   // 判断条件是否符合，直接更新结果
                   if(right - left == p.length()) // 直接减就ok ，因为前面 right已经+1 了
                       list.add(left);
                   char b = s.charAt(left);
                   if(needs.containsKey(b)){
                       wins.put(b,wins.get(b)-1);
                       if(wins.get(b) < needs.get(b)){
                           match--;
                       }
                   }
                   left++;
               }
           }
           return list;
       }
   }
   ```



## DFS(深度优先)

深度优先搜索，一条道走到黑、 （分析问题，==**需要回溯么**==）      **最好画出树状图**

在**递归之前**进行==筛选选择==，==调用递归==，然后==**撤销**==选择 。就能正确得到 每个节点的选择列表和路径。

按照下面的==**套路**==来呗

只要卡住 ==截止条件== ==候选节点==  ==筛选候选==节点就行了。

///// 【明确求解的问题，需不需要用list存储数据。回溯时用list需要删除最后一个元素‘用变量穿的是副本，不用尽心回溯】

### ==一维数组型==

#### 17  电话号码的组合

<img src="C:\Users\wangsw\AppData\Roaming\Typora\typora-user-images\image-20200822132400093.png" alt="image-20200822132400093" style="zoom: 67%;" />

```java
class Solution {
    char[][]  nums = new char[][]{
        {},{},
        {'a','b','c'},{'d','e','f'},
        {'g','h','i'},{'j','k','l'},{'m','n','o'},
        {'p','q','r','s'},{'t','u','v'},
        {'w','x','y','z'} };
    
    public List<String> letterCombinations(String str) {
        List<String> res = new ArrayList();
        if(str.length()==0) return res;
        dfs(str,0,new StringBuilder(),res);  //直接dfs
        return res;

    }
    void dfs(String str, int index, StringBuilder ss,List<String> res){
        // 截止条件
        if(index == str.length()){
            res.add(ss.toString());
            return ;
        }
        // 候选节点
        for(char s : nums[str.charAt(index) - '0']){    // 定义个索引 index；
            ss.append(s);
            dfs(str,index+1,ss,res);
            ss.deleteCharAt(ss.length()-1);
        }
    }
}
```



39 组合总和

<img src="C:\Users\wangsw\AppData\Roaming\Typora\typora-user-images\image-20200822134647472.png" alt="image-20200822134647472" style="zoom:67%;" />

注 ： 数字可以无限制重复去，就==不需要标记==使用过

```java

```



#### 113  路劲总和

<img src="C:\Users\wangsw\AppData\Roaming\Typora\typora-user-images\image-20200813150148733.png" alt="image-20200813150148733" style="zoom:67%;" />

sum是==局部变量==，每次递归的sum传的是==副本== ，当递归返回时，在哪一层，该层的sum还是之前的

res.add(==new ArrayList(chain==)    这边需要==深拷贝==，如果直add（chain） 传的是==引用地址==，当chain改变的时候，res里面的内容也会跟着==改变==

```java
 public List<List<Integer>> pathSum(TreeNode root, int sum) {
        // 声明作为结果
        List<List<Integer>> res = new ArrayList();
        if(root == null) return res;
        List<Integer> chain  = new ArrayList();
        chain.add(root.val);
        sum -= root.val;
        dfs(root,sum,chain,res);
        return res;
    }
    void dfs(TreeNode node,int sum,List<Integer> chain,List<List<Integer>> res){
            // 截止条件
            if(node.left == null && node.right ==null){
                if(sum == 0){
                    res.add(new ArrayList(chain)); // 深拷贝一下，不然直接传chain，传的是引用，内容随着chain的改变而改变
                }
            }
            // 候选人  树的话就左右节点是筛选人
            // 筛选 dfs
            if(node.left !=null){
                chain.add(node.left.val);
                dfs(node.left,sum-node.left.val,chain,res); // 没有返回值的
                chain.remove(chain.size()-1); 
            }
            if(node.right !=null){
                chain.add(node.right.val);
                dfs(node.right,sum-node.right.val,chain,res); // 没有返回值的
                chain.remove(chain.size()-1);
            }
    
    }
```

```java
// 上述递归过程 ：  先左节点后右节点   
5 - 4 - 11 -7
5 - 4 - 11 -2
5 - 8-13
5 -8 4 - 5
5 - 8 -4 - 1
```



## 字符串

### 最大回文字符串

> **最大回文字符串**
>
> 中心扩散法，回文对称

```java
import java.util.*;
public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String str = sc.nextLine();
           /* int start =0;
            int end = 0;  */
            int res = 0;
            int ss = 0;
            for(int i=0;i<str.length();i++){
                int res1 = result(str,i,i);  // 中间是数，长度为奇数
                int res2 = result(str,i,i+1); //长度为偶数
                res = Math.max(res1,res2);
                ss=Math.max(ss,res);
              /*  if(res > end-start){
                    start = i-(res-1)/2;
                    end = i+res/2;
                } */
           }
            System.out.println(ss);
            //System.out.println(str.substring(start,end+1));
        }
    }
    public static int result(String str,int l,int r){
           while(l>=0 && r<str.length() && str.charAt(l)==str.charAt(r)){
               l--;
               r++;
           }
        return r-l-1;
    }
}
```

### 判断是否是子序列

<img src="C:\Users\wangsw\AppData\Roaming\Typora\typora-user-images\image-20200901203511115.png" alt="image-20200901203511115" style="zoom:67%;" />

```java
// 
String.indexOf(String str，int index)
String.indexOf(String str);

从index的地方开始找，返回第一次出现的索引
// 还可以利用双指针
class Solution {
    public boolean isSubsequence(String s, String t) {
        int index = -1;
        for (char c : s.toCharArray()) {
      //index表示上一次查找的位置(第一次查找的时候为-1)，所以这里要从t的下标(index+1)开始查找
            index = t.indexOf(c, index + 1);
            //没找到，返回false
            if (index == -1)
                return false;
        }
        return true;
    }
}

// 输出字符串的所有子串
void findAllSubstrings(String str,int length){
	for(int i = 0 ; i < length ; i++ ){
		for(int j = i+1 ; j <= length ; j++ ){
			sub = str.substring(i, j);
			System.out.println(sub);
		}
	}
}
```



括号生成   可以 DFS

<img src="C:\Users\wangsw\AppData\Roaming\Typora\typora-user-images\image-20200901095419511.png" alt="image-20200901095419511" style="zoom:67%;" />



49. 字母异位词分组

    <img src="C:\Users\wangsw\AppData\Roaming\Typora\typora-user-images\image-20200901104332501.png" alt="image-20200901104332501" style="zoom:67%;" />

```java
// 排序使用hashMap
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> map = new HashMap();
        for(String a : strs){
            char[] str = a.toCharArray();
            Arrays.sort(str);  // 排序
            String key = String.valueOf(str); // 将char数组转化为字符串
            if(!map.containsKey(key))
               map.put(key,new ArrayList());
            map.get(key).add(a);
        }
        return new ArrayList(map.values());  // 将map的所有value取出来
    }
}

// 双指针也可  先去掉空格  在用双指针
```

165.  比较版本号

     <img src="C:\Users\wangsw\AppData\Roaming\Typora\typora-user-images\image-20200901151314305.png" alt="image-20200901151314305" style="zoom:67%;" />

```java
class Solution {
    public int compareVersion(String version1, String version2) {
        String[] s1 = version1.split("\\.");  // . 	是特殊的字符 ， 要转义
        String[] s2 = version2.split("\\.");
        int a = s1.length, b =s2.length; 
        int size = Math.max(a,b);
        for(int i =0;i<size;i++){
            int j = (i < a ? Integer.parseInt(s1[i]) : 0);
            int k = (i < b ? Integer.parseInt(s2[i]) : 0);
            if(j > k){
                return 1;
            }
            if(j < k){
                return -1;
            }
        }
        return 0;
    }
}
```

















## 位运算

### 交换奇偶位

```java
public static String swapjo(long a){  // 奇偶位互换
        long b = a & 0b01010101010101010101010101010101; // 从左到右 第一位奇数位
        long c = a & 0b10101010101010101010101010101010; // 保留偶数位
        long res= (b<<1)^(c>>1);
        String num = Long.toBinaryString(res);
        num  = "00000000000000000000000000000000" +num;  // 保留32位
        num  = num.substring(num.length()-32,num.length());
        System.out.println(num);
        return num;
    }
```



## 一、数组

> **01、二维数组中的查找**
>
> ​		 ==二分查找==    **注意开闭**



> **06、旋转数组的最小数字**
>
> ​		==二分查找==   **注意开闭**



> **21、调整数组顺序使奇数位于偶数前面**
>
> ​		 ==双指针==   前后指针 或者 快慢指针

```java
public int[] exchange(int[] nums) {
        // 双指针并行
        /*int left = 0;
        int right = nums.length - 1;
        while(left < right){
             while((nums[left] % 2 == 1) && left<right) left++;
             while((nums[right] % 2 == 0) && left<right) right--;
            if(left < right){
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp; 
            }
        }
        return nums; */
    
        // 快慢指针   快指针判断奇偶数，low记录交换位置
        int low = 0;
        int fast = 0;
        while(fast < nums.length){
            // 奇数  交换位置
            if((nums[fast] & 1) == 1){
                int temp = nums[fast];
                nums[fast] = nums[low];
                nums[low] = temp;
                low++;
            }
            fast++;
        }
        return nums;
    }
```



## 

> **27、数组中出现次数超过一半的数字**
>
> ​	    遍历数组加双指针判断  可行  时间1791ms
>
> ​		hashMpa 统计法，重复的key  value +1
>
> ​		摩尔投票法(**同归于尽法**)  正负抵消    剩下里面多的依然是众数

> **29、连续子数组的最大和**

> **31、把数组排成最小的数**

> **34、数组中的逆序对**

> **36、数字在排序数组中出现的次数**

> **39、数组中只出现一次的数字**

> **40、和为S的连续正数序列**

> **41、和为S的两个数字**

> **49、数组中重复的数字**
>
> ​		 二分查找思想

> **50、构建乘积数组**



## 二、链表

> **06、从尾到头打印链表**
>
> - 链表长度，倒转遍历数组
>
> - 递归解法，加入list，赋值给数组
>
> - 入栈思想   不能用栈的size遍历，出栈会影响size的大小
>
>   ```JAVA
>   class Solution {
>      // ArrayList<Integer> list = new ArrayList();
>       public int[] reversePrint(ListNode head) {
>           if(head == null) return new int[0];
>             /*  recu(head);
>               int[] res = new int[list.size()];
>               for(int i = 0;i<res.length;i++){
>                   res[i] = list.get(i);
>               } 
>               return res;*/
>           LinkedList<Integer> list = new LinkedList(); // ，利用链表的进出栈
>           while(head !=null){
>               list.addLast(head.val);
>               head = head.next;
>           }
>           int[] res= new int[list.size()];
>           for(int i=0;i<res.length;i++){  // 注意这边 不能再用list的大小了  ，出栈时候，size会改变，
>               res[i] = list.removeLast();
>           }
>           return res;
>       }
>      /* public void recu(ListNode node){  // 递归
>           if(node.next != null){
>               recu(node.next);
>           }
>               list.add(node.val);
>       } */
>   }
>   ```
>
>   

> **11、在O(1)时间删除链表结点**

> **13、链表中倒数第K个结点**
>
> - 先计算长度，在从头遍历到   length - k
>
> - 快慢指针
>
>   ```java
>   public ListNode getKthFromEnd(ListNode head, int k) {
>           /*// 先计算链表的长度
>           ListNode temp = head;
>           int count = 1;
>           while(temp.next != null){
>               temp = temp.next;
>               count++;
>           }
>           temp = head;
>           for(int i=0;i<count-k;i++){
>               temp = temp.next;
>           } 
>           return temp; */
>   
>           //快慢指针  速度最快，为什么？
>           ListNode temp = head;
>           for(int i=0;i<k-1;i++){
>               temp = temp.next;
>           }
>           ListNode temp2 = head;
>           while(temp.next !=null){
>               temp = temp.next;
>               temp2 = temp2.next;
>           }
>           return temp2;
>       }
>   ```
>
>   

> **14、反转链表**
>
> - 用三个pre  cur   nxt 反转
>
> - 递归思想
>
>   ```java
>   public ListNode reverseList(ListNode head) {
>         /*  ListNode temp = head;
>           // 重点就是 记录pre cur next的节点，按照需求分析即可
>           ListNode pre = null,nxt = temp;
>           while(temp !=null){
>               nxt = temp.next;  // 记录下一个节点
>               temp.next = pre;  // 让temp.next 指向 pre
>               pre = temp;   // pre  = temp,让下一个节点变为pre
>               temp = nxt;  // 循环下一步
>           }
>           return pre;  */ 
>           if(head == null || head.next ==null) return head; // 递归方法
>           ListNode res = reverseList(head.next);
>           head.next.next = head;
>           head.next = null;
>           return res;
>       }
>   ```
>
>   

> **15、合并两个排序的链表**

> **24、复杂链表的复制**

> **35、两个链表的第一个公共结点**
>
> - 双指针  两条路径  temp1  temp2 各自走完两条路径
>
>   ```java
>   public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
>           // 双指针  主要是链表的长度不一致
>           if(headA ==null || headB ==null)
>               return null;
>           ListNode temp1 = headA,temp2 = headB;
>           while(temp1 !=temp2){
>              /* if(temp1 ==null)
>                   temp1 = headB;
>               else temp1 = temp1.next;
>               if(temp2 ==null)
>                   temp2 = headA;
>               else temp2 = temp2.next; */
>               temp1 = temp1 ==null ? headB : temp1.next;
>               temp2 = temp2 ==null ? headA : temp2.next;
>           }
>           return temp1;
>       }
>   ```
>
>   

> **52、链表中环的入口结点**   双指针操作

> **53、删除链表中重复的结点**
>
> ​		双指针操作



##  三、栈和队列

> **05、两个栈实现一个队列，两个队列实现一个栈**

> **19、包含Min函数的栈**

> **20、栈的压入弹出序列**

> **60、滑动窗口的最大值**



##  四、二叉树

> **04、重建二叉树**

> **16、树的子结构**

> **17、二叉树的镜像**

> **21、从上到下打印二叉树**

> **22、二叉搜索树的后序遍历序列**

> **23、二叉树中和为某一值的路径**

> **25、二叉搜索树与双向链表**

> **37、二叉树的深度**

> **38、平衡二叉树**

> **54、二叉树的下一个结点**

> **55、对称的二叉树**

> **56、按之字形顺序打印二叉树**

> **57、把二叉树打印成多行**

> **58、二叉搜索树的第k个结点**





## 五、字符串

> **02、替换空格**

> **26、字符串的排列**

> **33、第一个只出现一次的字符**

> **42、左旋转字符串**

> **43、翻转单词顺序列**

> **48、把字符串转换成整数**

> **51、字符流中第一个不重复的字符**





##  六、回溯算法

> **61、矩阵中的路径**

> **62、机器人的运动范围**





## 七、其他

> **07、斐波拉契数列**

> **08、二进制中1的个数**

> **09、数值的整数次方**

> **10、打印1到最大的n位数**

> **18、顺时针打印矩阵**

> **28、最小的k个数（TopK）**

> **30、从1到n整数中1出现的次数**

> **32、丑数**

> **44、扑克牌顺子**

> **45、孩子们的游戏(圆圈中最后剩下的数)**   环形链表？

> **46、求1+2+3+…+n**

> **47、不用加减乘除做加法**

> **59、数据流中的中位数**

