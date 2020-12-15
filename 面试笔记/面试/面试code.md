## 字符串

392. 判断子序列
393. 回文子串
72. 编辑距离



**子序列问题 ： **动态规划

- 二维 dp 数组  
  - 当涉及到**两个字符串 i  j**  时； 定义 dp [i] [j]   前 0.....i   和  前0.....j 个字符串  要求的.....

1143  最长公共子序列【==两==个字符串】

516  最长回文子序列 【只涉及==一====个==字符串】 dp[i] [j]   字符串 i... j  最大回文子串的长度



## **二叉树问题**

offer 55 二叉树深度    DFS 

94 二叉树的**前、中、后序**遍历  

```java
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList();
        if(root == null) return list;
        search(root,list);
        return list;
    }
    void search(TreeNode node,List<Integer> list){  // dfs
        if(node == null) return;
        search(node.left,list);  左
        list.add(node.val); 中
        search(node.right,list);  右
    }
}
```



102  二叉树的层序遍历   用 队列 来实现   `Deque<E>  queue = new ArrayDeque();`

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {         //   对列 画图 感受队列的过程
    public List<List<Integer>> levelOrder(TreeNode root) {
        // 使用 队列
        List<List<Integer>> res = new ArrayList();
        Deque<TreeNode> queue = new ArrayDeque();
        if(root != null) queue.add(root); // 先把节点加入
        while(!queue.isEmpty()){
            List<Integer> chain = new ArrayList();
            int size = queue.size();   ///  很重要   循环里面不能用 可变的size 
            for(int i=0;i<size;i++){
                TreeNode node = queue.pollFirst();
                chain.add(node.val);
                if(node.left !=null) queue.add(node.left);
                if(node.right !=null) queue.add(node.right);
            }
            res.add(new ArrayList(chain));
        }
        return res;
    }
}
```

103 锯齿层序遍历    【**偶数**层需要倒着输出】

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        // 使用 队列
        List<List<Integer>> res = new ArrayList();
        Deque<TreeNode> queue = new ArrayDeque();
        int index =1;
        if(root != null) queue.add(root); // 先把节点加入
        while(!queue.isEmpty()){
            List<Integer> chain = new ArrayList();
            int size = queue.size();
            for(int i=0;i<size;i++){
                TreeNode node = queue.pollFirst();
                chain.add(node.val);
                if(node.left !=null) queue.add(node.left);
                if(node.right !=null) queue.add(node.right);
            }
            if(index % 2 ==0)    //  这里需要判断改动
                Collections.reverse(chain);
            res.add(new ArrayList(chain));
               
            index++;
        }
        return res;
    }
}
```



107 倒序层序遍历 ， 从最底下开始输出



144   145  前序 中 右的**迭代**写法、

==**只需要改变**==下面 ==右中左==的顺序

前序 ： 右 左中    中序 ： 右 中 左  后序 ： 中 右 左   **入栈的方向是==相反==的**

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList();
        Stack<TreeNode> stack = new Stack();
        if(root !=null) stack.add(root);
        while(! stack.isEmpty()){
            TreeNode node = stack.peek();
            if(node != null){
                stack.pop();
                if(node.right !=null) stack.add(node.right);  // 右
                stack.add(node);  // 中 
                stack.add(null);   // 中捆绑
                if(node.left != null) stack.add(node.left);   // 左
                
            }else{
                stack.pop();  // 弹出 null
                res.add(stack.pop().val); // 弹出添加值
            }
        }
        return res;
    }
}
```



## 链表

反转链表 

- ==递归==反转  明白

  ```java
  /**
   * Definition for singly-linked list.
   * public class ListNode {
   *     int val;
   *     ListNode next;
   *     ListNode(int x) { val = x; }
   * }
   */
  class Solution {
      public ListNode reverseList(ListNode head) {
          // 使用递归  截止条件
          if( head==null) return head;
          if(head.next == null) return head;
          ListNode last = reverseList(head.next);
          head.next.next = head;
          head.next = null;
          return last;
      }
  }
  ```

- ==迭代==输出

  ```java
  / 先迭代输出结果
          ListNode pre = null, cur = head , nxt = head;
          while(cur !=null){
              nxt = cur.next;
              cur.next = pre;
              pre = cur;
              cur = nxt;
          }
          return pre;
  ```




删除倒数节点

- 先遍历一遍  取倒数N个节点

```java
// 双指针
ListNode temp = head,temp2 = head;
        for(int i=0;i<k-1;i++){  // 先双指针移动
            temp = temp.next;
        }
        while(temp.next !=null){
            temp2 = temp2.next;
            temp= temp.next;
        }
        return temp2;
```

-  删除节点  返回头结点

  ```java
  class Solution {
      public ListNode removeNthFromEnd(ListNode head, int n) {
              // 删除节点  双指针
              ListNode pre = new ListNode(0);
              pre.next = head;
              ListNode temp = pre, cur = pre;
              for(int i=0;i<n;i++){
                  temp = temp.next;
              }
  
              while(temp.next !=null){
                  cur = cur.next;
                  temp = temp.next;
              }
              cur.next = cur.next.next;
  
              return pre.next;
      }
  }
  ```

  

判断有无环   **双指针**

```java
快慢指针  最终快指针会超过一圈和 慢指针相遇
ListNode fast = head,slow = head;
while(fast.next !=null && fast.next.next !=null){
    fast = fast.next.next;
    slow = slow.next;
    
    if(fast == slow ) return true
}
return false;
```

判断环的起始位置

```java
ListNode fast = head,slow = head;
while(fast.next !=null && fast.next.next !=null){
    fast = fast.next.next;
    slow = slow.next;
    
    if(fast == slow ) break;
}

slow = head;
// 遇到的时候 ，其中一个从起点开始，最种会相遇
while(slow !=fast){
    slow = slow.next;
    fast = fast.next;
}
return slow;

```

<img src="C:\Users\wangsw\AppData\Roaming\Typora\typora-user-images\image-20200909215859764.png" alt="image-20200909215859764" style="zoom:50%;" />



二分查找



快排







