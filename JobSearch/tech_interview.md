# Preparing for tech interview <!-- omit in toc -->


- [Resources](#resources)
- [Tips](#tips)
- [Evaluation](#evaluation)
- [Data structures](#data-structures)
  - [Hashmap](#hashmap)
  - [Stack](#stack)
- [Exercises](#exercises)
  - [🟢 1. Two Sum](#-1-two-sum)
    - [My solution 1](#my-solution-1)
    - [Good solution](#good-solution)
  - [🟢 20. Valid Parentheses](#-20-valid-parentheses)
    - [My solution 1 (good)](#my-solution-1-good)
  - [🟢 21. Merge Two Sorted Lists](#-21-merge-two-sorted-lists)
    - [My solution 1 (good but long, this is refactored version of it)](#my-solution-1-good-but-long-this-is-refactored-version-of-it)
  - [🟢 121. Best Time to Buy and Sell Stock](#-121-best-time-to-buy-and-sell-stock)
    - [My solution 1 (good)](#my-solution-1-good-1)
    - [My solution 2 (good, a bit shorter but a bit less efficient)](#my-solution-2-good-a-bit-shorter-but-a-bit-less-efficient)
    - [Sliding window solution (good)](#sliding-window-solution-good)
  - [🟢 125. Valid Palindrome](#-125-valid-palindrome)
    - [My solution 1](#my-solution-1-1)
    - [Solution (reverse)](#solution-reverse)
  - [🟢 226. Invert Binary Tree](#-226-invert-binary-tree)
    - [My solution 1 (iterative)](#my-solution-1-iterative)
    - [My solution 2 (recursive)](#my-solution-2-recursive)
  - [🟢 242. Valid Anagram](#-242-valid-anagram)
    - [My solution 1 (good)](#my-solution-1-good-2)
    - [Python specific solution](#python-specific-solution)
    - [Solution with sorting](#solution-with-sorting)
  - [🟢 704. Binary Search](#-704-binary-search)
    - [Good solution](#good-solution-1)
  - [🟢 733. Flood Fill](#-733-flood-fill)
    - [My solution](#my-solution)
    - [Better recursive solution](#better-recursive-solution)
    - [Solution with stack](#solution-with-stack)
  - [🟢 235. Lowest Common Ancestor of a Binary Search Tree](#-235-lowest-common-ancestor-of-a-binary-search-tree)
    - [My solution](#my-solution-1)
  - [🟢 110. Balanced Binary Tree](#-110-balanced-binary-tree)
    - [My solution](#my-solution-2)
    - [Same solution but without exception](#same-solution-but-without-exception)
  - [🟢 141. Linked List Cycle](#-141-linked-list-cycle)
    - [My solution](#my-solution-3)
    - [Good solution](#good-solution-2)
  - [🟢 232. Implement Queue using Stacks](#-232-implement-queue-using-stacks)
    - [My solution (good)](#my-solution-good)
  - [🟢 217. Contains Duplicate](#-217-contains-duplicate)
    - [My solution](#my-solution-4)
  - [🟢 206. Reverse Linked List](#-206-reverse-linked-list)
    - [My solution (2 pointers)](#my-solution-2-pointers)
    - [My solution (recursive)](#my-solution-recursive)
    - [Solution 2 (recursive)](#solution-2-recursive)
    - [Solution 3 (recursive)](#solution-3-recursive)
  - [🟢 104. Maximum Depth of Binary Tree](#-104-maximum-depth-of-binary-tree)
    - [My solution](#my-solution-5)
    - [BFS](#bfs)
    - [DFS](#dfs)
  - [🟢 543. Diameter of Binary Tree](#-543-diameter-of-binary-tree)
    - [My solution (good)](#my-solution-good-1)
  - [🟢 100. Same Tree](#-100-same-tree)
    - [My solution (good)](#my-solution-good-2)
  - [🟢 572. Subtree of Another Tree](#-572-subtree-of-another-tree)
    - [My solution (brute-force)](#my-solution-brute-force)
    - [Good solution](#good-solution-3)
  - [🟢 70. Climbing Stairs](#-70-climbing-stairs)
    - [My solution](#my-solution-6)
    - [DP solution (bottom-up, iterative)](#dp-solution-bottom-up-iterative)
  - [🟢 746. Min Cost Climbing Stairs](#-746-min-cost-climbing-stairs)
    - [My solution](#my-solution-7)
    - [Solution without pointers](#solution-without-pointers)
  - [🟡 49. Group Anagrams](#-49-group-anagrams)
    - [My solution (brute-force)](#my-solution-brute-force-1)
    - [Good solution](#good-solution-4)
  - [🟡 347. Top K Frequent Elements](#-347-top-k-frequent-elements)
    - [My solution](#my-solution-8)
    - [Good solution](#good-solution-5)
  - [🟡 238. Product of Array Except Self](#-238-product-of-array-except-self)
    - [My solution (optimal)](#my-solution-optimal)
  - [🟡 36. Valid Sudoku](#-36-valid-sudoku)
    - [Good solution](#good-solution-6)
  - [🟡 271. Encode and Decode Strings](#-271-encode-and-decode-strings)
    - [My solution](#my-solution-9)
    - [Good solution](#good-solution-7)
  - [🟡 128. Longest Consecutive Sequence](#-128-longest-consecutive-sequence)
    - [My solution (space is suboptimal)](#my-solution-space-is-suboptimal)
    - [Good solution](#good-solution-8)

## Resources

- Roadmap - https://neetcode.io/roadmap (approach https://www.youtube.com/watch?v=SVvr3ZjtjI8)
- Study guide - https://www.techinterviewhandbook.org/grind75?weeks=8
- Course - https://www.educative.io/courses/grokking-coding-interview-patterns-python
- Course - https://www.enjoyalgorithms.com/data-structures-and-algorithms-course/
- Course - https://designgurus.org/course/grokking-the-coding-interview
- Course - https://algo.monster/?sscid=11k7_guvme&
- Leetcode list - https://leetcode.com/list/?selectedList=owsdlmt3
- Mock coding interviews with real engineers - https://interviewing.io/?urc=DMCa

## Tips

__To approach a problem:__
- Visualize it by drawing it out
- Think about how to solve it by hand, code the solution
- Come up with more examples
- Break it down into smaller independent parts
- Apply common data structures and algorithms:
  - hashmap / graph / stack / queue / heap / tree / trie
  - sorting / binary search / sliding window / 2 pointers / union find / BFS, DFS / traverse from the back / topological sorting

__To optimize a problem:__
- Identify BTTC (Best Theoretical Time Complexity). If you achieved BTTC it might still be possible to:
  - Do less work: O(n) with 1 pass rather than 2
  - Use less space (see below)
- Identify overlapping or repeated computations. Is it possible to reuse results of previous computations?
- Try different data structures
- Identify redundant work
  - Unnecessary conditions
  - Order or checks
  - Remove unnecessary func invocations - when you need to use a result of a func, save it to a var
  - Early termination
  - Minimize work inside loops - save in vars outside of loop if func call repeats
  - Lazy evaluation

__To optimize space complexity__:
- Changing data in-place / overwriting input data
- Change a data structure

## Evaluation

TBD


## Data structures

### Hashmap
__Features and usages__
- Checking for presence
__Exercises__
- Two Sum

### Stack
__Features and usages__
- Order
- Traversal in order
- Binary tree traversal non-recursive
__Exercises__
- Valid Parentheses
- Invert Binary Tree (non-recursive)


## Exercises

### 🟢 [1. Two Sum](https://leetcode.com/problems/two-sum)

#### My solution 1
- O(n²)
- make 2 pointers and move them together forward (with 2D loop) until they meet the target
```python
def twoSum(self, nums: List[int], target: int) -> List[int]:
    p1 = 0
    p2 = 1
    while p1 < len(nums) - 1:
        while p2 < len(nums):
            if nums[p1] + nums[p2] == target:
                return [p1, p2]
            p2+=1
        p1+=1
        p2 = p1 + 1
    return []
```
#### Good solution
- O(n)
- use hashmap to store previous values {val:i}
- iterate once, looking for a num that is a difference between current num and target
- as soon as you meet the second num, your first num will already be in a hashmap and you will get it's index
```python
def twoSum(self, nums: List[int], target: int) -> List[int]:
    map = {}
    for i, currentNum in enumerate(nums):
        secondNum = target - currentNum
        if secondNum in map:
            return [i, map[secondNum]]
        map[currentNum] = i
    return
```

### 🟢 [20. Valid Parentheses](https://leetcode.com/problems/valid-parentheses)

#### My solution 1 (good)
- O(n)
- use stack to iterate over string and add chars to it
- if top char in stack closes the current bracket, then pop, otherwise append
```python
def isValid(self, s: str) -> bool:
    map = {"(":")", "[":"]", "{": "}"}
    stack = []
    for c in s:
        if not stack:
            stack.append(c)
            continue
        last = stack[-1]
        if last in map and map[last] == c:
            stack.pop()
        else:
            stack.append(c)
    return not stack
```

### 🟢 [21. Merge Two Sorted Lists](https://leetcode.com/problems/merge-two-sorted-lists)

#### My solution 1 (good but long, this is refactored version of it)
- O(n)
- create initNode as a dummy head, define tail
- iterate over lists growing the tail
- if any of lists remain, add to the tail
```python
def mergeTwoLists(self, list1: Optional[ListNode], list2: Optional[ListNode]) -> Optional[ListNode]:
    initNode = ListNode()
    tail = initNode

    while list1 and list2:
        if list1.val < list2.val:
            tail.next = list1
            list1 = list1.next
        else:
            tail.next = list2
            list2 = list2.next
        tail = tail.next

    if list1: tail.next = list1
    if list2: tail.next = list2

    return initNode.next
```

### 🟢 [121. Best Time to Buy and Sell Stock](https://leetcode.com/problems/best-time-to-buy-and-sell-stock)

#### My solution 1 (good)
- O(n)
- store min and maxProfit
- iterate and compare, updating min and maxProfit
```python
def maxProfit(self, prices: List[int]) -> int:
  min = prices[0]
  maxProfit = 0
  for p in prices:
      print(p, min, maxProfit)
      if p - min > maxProfit:
          maxProfit = p - min
      if p < min:
          min = p
  return maxProfit
```
#### My solution 2 (good, a bit shorter but a bit less efficient)
```python
def maxProfit(self, prices: List[int]) -> int:
  minPrice = prices[0]
  maxProfit = 0
  for p in prices:
      maxProfit = max(maxProfit, p - minPrice)
      minPrice = min(minPrice, p)
  return maxProfit
```
#### Sliding window solution (good)
- 2 pointers: start at 0,1
- move L only if price at R is smaller
- move R by 1 each time
```python
def maxProfit(self, prices: List[int]) -> int:
    l, r = 0, 1
    maxProfit = 0
    while r < len(prices):
        maxProfit = max(maxProfit, prices[r] - prices[l])
        if prices[r] < prices[l]:
            l = r
        r += 1
    return maxProfit
```

### 🟢 [125. Valid Palindrome](https://leetcode.com/problems/valid-palindrome)

#### My solution 1
- O(n)
- use 2 pointers, going from L> and from <R, checking for equality
```python
def isPalindrome(self, s: str) -> bool:
    s = ''.join(filter(str.isalnum, s)).lower()
    l, r = 0, len(s)-1
    while l < r:
        if s[l] is not s[r]:
            return False
        l += 1
        r -= 1
    return True
```
#### Solution (reverse)
- O(n)
- compare string to reversed string
```python
def isPalindrome(self, s: str) -> bool:
  s = ''.join(filter(str.isalnum, s)).lower()
  return s == s[::-1]
```

### 🟢 [226. Invert Binary Tree](https://leetcode.com/problems/invert-binary-tree)

#### My solution 1 (iterative)
- O(n)
- traverse BT using stack
- invert node on adding to stack
```python
def invertTree(self, root: Optional[TreeNode]) -> Optional[TreeNode]:
    current = root
    stack = []
    while True:
        if current is not None:
            current.left, current.right = current.right, current.left
            stack.append(current)
            current = current.left
        elif stack:
            current = stack.pop()
            current = current.right
        else:
            break
    return root
```
#### My solution 2 (recursive)
- O(n)
- traverse BT recursively, invert each node
```python
def invertTree(self, root: Optional[TreeNode]) -> Optional[TreeNode]:
    if not root: return
    root.left, root.right = self.invertTree(root.right), self.invertTree(root.left)
    return root
```

### 🟢 [242. Valid Anagram](https://leetcode.com/problems/valid-anagram)

#### My solution 1 (good)
- O(n), 2n
- map chars from string S
- remove from map on string T
- alternative is to use 2 maps and compare them for equality
```python
def isAnagram(self, s: str, t: str) -> bool:
    map = {}
    for e in s:
        map[e] = map.get(e, 0) + 1
    for e in t:
        if not map.get(e): return False
        if map[e] == 1: map.pop(e)
        else: map[e] = map[e] - 1
    return not map
```
#### Python specific solution
```python
def isAnagram(self, s: str, t: str) -> bool:
  return Counter(s) == Counter(t)
```
#### Solution with sorting
```python
def isAnagram(self, s: str, t: str) -> bool:
  return sorted(s) == sorted(t)
```

### 🟢 [704. Binary Search](https://leetcode.com/problems/binary-search)

#### Good solution
- O(log n)
- 2 pointers, L and R
- Mid pointer is (L+R)/2
- move pointer away from the side that is eliminated by comparison
```python
def search(self, nums: List[int], target: int) -> int:
    l, r = 0, len(nums) - 1
    while l <= r:
        m = (l+r)//2
        if target > nums[m]:
            l = m+1
        elif target < nums[m]:
            r = m-1
        else: return m
    return -1
```

### 🟢 [733. Flood Fill](https://leetcode.com/problems/flood-fill)

#### My solution
- recursive
```python
def floodFill(self, image: List[List[int]], sr: int, sc: int, color: int) -> List[List[int]]:
    initColor = image[sr][sc]
    return self.floodFillRec(image, sr, sc, initColor, color)

def floodFillRec(self, image: List[List[int]], sr: int, sc: int, initColor: int, targetColor: int) -> List[List[int]]:
    if initColor == targetColor:
        return image
    image[sr][sc] = targetColor
    for dir in [(1,0), (-1,0), (0,1), (0,-1)]:
        i, j = sr+dir[0], sc+dir[1]
        if self.inBounds(image, i, j) and image[i][j] == initColor:
            image[i][j] = targetColor
            self.floodFillRec(image, i, j, initColor, targetColor)
    return image

def inBounds(self, image: List[List[int]], i: int, j: int) -> bool:
    return i >= 0 and i < len(image) and j >=0 and j < len(image[i])
```
#### Better recursive solution
```python
def floodFill(self, image: List[List[int]], sr: int, sc: int, color: int) -> List[List[int]]:
    initColor = image[sr][sc]

    def dfs(sr, sc):
        if 0 <= sr < len(image) and 0 <= sc < len(image[0]) \
        and image[sr][sc] == cur_color and image[sr][sc] != color:
            image[sr][sc] = color
            dfs(sr+1, sc)
            dfs(sr-1, sc)
            dfs(sr, sc+1)
            dfs(sr, sc-1)

    dfs(sr, sc)
    return image
```
#### Solution with stack
```python
def floodFill(self, image: List[List[int]], sr: int, sc: int, color: int) -> List[List[int]]:
    oldValue = image[sr][sc]
    stack = [(sr, sc)]
    if image[sr][sc] == color:
        return image
    while len(stack) != 0:
        curr = stack.pop()
        row, col = curr[0], curr[1]
        image[row][col] = color
        self.checkDirections(image, row, col, oldValue, stack)
    return image

def checkDirections(self, image: List[List[int]], sr: int, sc: int, oldVal: int, stack: List[tuple[int, int]]) -> None:
    directions = [(-1, 0), (1, 0), (0, -1), (0, 1)]
    for direction in directions:
         newRow = sr + direction[0]
         newCol = sc + direction[1]
         if self.inBounds(image, newRow, newCol) and image[newRow][newCol] == oldVal:
             stack.append((newRow, newCol))

def inBounds(self, image: List[List[int]], row: int, col: int) -> bool:
    return (row >=0 and row < len(image)) and (col >=0 and col < len(image[row]))
```

### 🟢 [235. Lowest Common Ancestor of a Binary Search Tree](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree)

Can be solved by creating a path for P and a path for Q and then comparing them until they diverge.
But easier solution is just to follow 1 path until it diverges - that node will be LCA.

#### My solution
- O(log n)
- follow a path until P and Q diverge
- this node will be LCA
```python
class Solution:
    def lowestCommonAncestor(self, root: 'TreeNode', p: 'TreeNode', q: 'TreeNode') -> 'TreeNode':
        lca = root
        while lca:
            if (lca.val >= p.val and lca.val <= q.val) or (lca.val <= p.val and lca.val >= q.val):
                break
            elif p.val < lca.val:
                lca = lca.left
            else:
                lca = lca.right
        return lca
```

### 🟢 [110. Balanced Binary Tree](https://leetcode.com/problems/balanced-binary-tree)

#### My solution
- O(n)
- recursive
- propagate level down
- use exception to exist recursion
```python
def isBalanced(self, root: Optional[TreeNode]) -> bool:
    def getLeafLevel(level: int, root: Optional[TreeNode]) -> int:
        if not root: return level
        l = getLeafLevel(level+1, root.left)
        r = getLeafLevel(level+1, root.right)
        if l - r < -1 or l - r > 1: raise Exception()
        return max(l, r)

    try:
        getLeafLevel(0, root)
        return True
    except:
        return False
```
#### Same solution but without exception
- Alternatively you can propagate tree height up the recursion call stack, and make it -1 when one of the nodes turns out to be unbalanced. Then -1 will propagate to the first call of inner function and will indicate that one of the nodes was unbalanced
```python
def isBalanced(self, root: Optional[TreeNode]) -> bool:
    def dfs(root) -> int:
        if not root: return 0
        l = dfs(root.left)
        r = dfs(root.right)
        if l == -1 or r == -1 or abs(l-r) > 1:
            return -1
        return 1 + max(l, r)

    return dfs(root) != -1
```

### 🟢 [141. Linked List Cycle](https://leetcode.com/problems/linked-list-cycle)

#### My solution
- O(n) space also O(n)
- using hashmap to check which nodes are visited
```python
def hasCycle(self, head: Optional[ListNode]) -> bool:
    map = {}
    while head:
        if map.get(head, 0) == 1:
            return True
        else:
            map[head] = 1
            head = head.next
    return False
```
#### Good solution
- O(n) space O(1)
- not using any additional data structures
- Floyd's Tortoise and Hare: iterating over the list with 2 pointers: 1 makes 1 step, 2 makes 2 steps
- at some point they will either be at the same node (if there is a cycle) or reach the end (if not)
```python
def hasCycle(self, head: Optional[ListNode]) -> bool:
    p1, p2 = head, head
    while p2 and p2.next:
        p2 = p2.next.next
        p1 = p1.next
        if p1 == p2:
            return True
    return False
```

### 🟢 [232. Implement Queue using Stacks](https://leetcode.com/problems/implement-queue-using-stacks)

#### My solution (good)
- push O(1), pop/peek O(n) (because it does it only when s2 is empty complexity of the dequeue operation becomes O(1))
- it's also possible to implement push O(n) and pop/peek O(1), but that solution is worse as it will move all elements from s1 to s2 and back to s1 on every push
```python
class MyQueue:
    def __init__(self):
        self.s1 = []
        self.s2 = []

    def push(self, x: int) -> None:
        self.s1.append(x)

    def pop(self) -> int:
        self.flipStack()
        return self.s2.pop()

    def peek(self) -> int:
        self.flipStack()
        return self.s2[-1]

    def empty(self) -> bool:
        return len(self.s1) == 0 and len(self.s2) == 0

    def flipStack(self):
        if len(self.s2) == 0:
            while self.s1:
                self.s2.append(self.s1.pop())
```

### 🟢 [217. Contains Duplicate](https://leetcode.com/problems/contains-duplicate)

#### My solution
- O(n) space O(n)
- can be done with space O(1) by either making time O(n²) or in case of sorting time O(nlogn)
- using hashmap or hashset
```python
def containsDuplicate(self, nums: List[int]) -> bool:
    s = set()
    for n in nums:
        if n in s: return True
        s.add(n)
    return False
```

### 🟢 [206. Reverse Linked List](https://leetcode.com/problems/reverse-linked-list)

#### My solution (2 pointers)
- O(n)
- 2 pointers
```python
def reverseList(self, head: Optional[ListNode]) -> Optional[ListNode]:
    p1, p2 = None, head
    while p2:
        temp = p1
        p1 = p2
        p2 = p2.next
        p1.next = temp
    return p1
```
#### My solution (recursive)
- go to the end, then return node and head
- reverse node next pointer
- keep returning same head
```python
def reverseList(self, head: Optional[ListNode]) -> Optional[ListNode]:
    def rec(node: ListNode) -> tuple[ListNode, ListNode]:
        if not node or not node.next:
            return (node, node)
        n, head = rec(node.next)
        node.next = None
        n.next = node
        return (node, head)

    (n, head) = rec(head)
    return head
```
#### Solution 2 (recursive)
- have 2 nodes in the inner func, reverse their next pointers on the way back
- always return same tail (which will be the head of new list)
```python
def reverseList(self, head: Optional[ListNode]) -> Optional[ListNode]:
    def rec(prev, current):
        if not current:
            return prev
        tail = rec(current, current.next)
        current.next = prev
        return tail
    res = rec(None, head)
    return res
```
#### Solution 3 (recursive)
- not using inner functions
- much harder to understand
```python
def reverseList(self, head: Optional[ListNode]) -> Optional[ListNode]:
    if not head: return None
    newHead = head
    if head.next:
        newHead = self.reverseList(head.next)
        head.next.next = head
    head.next = None
    return newHead
```

### 🟢 [104. Maximum Depth of Binary Tree](https://leetcode.com/problems/maximum-depth-of-binary-tree)

#### My solution
- recursive
```python
def maxDepth(self, root: Optional[TreeNode]) -> int:
    if not root: return 0
    return max(self.maxDepth(root.left), self.maxDepth(root.right)) + 1
```
#### BFS
- non-recursive level-order traversal
- use a queue to go over each level 1 by 1
```python
def maxDepth(self, root: Optional[TreeNode]) -> int:
    if not root: return 0

    level = 0
    q = deque([root])
    while q:
        for i in range(len(q)):
            node = q.popleft()
            if node.left:
                q.append(node.left)
            if node.right:
                q.append(node.right)
        level += 1
    return level
```
#### DFS
- pre-order DFS
- use a stack to simulate recursive call stack
```python
def maxDepth(self, root: Optional[TreeNode]) -> int:        
    s = [[root, 1]] # [node, level]
    res = 0
    while s:
        node, level = s.pop()
        if node:
            res = max(res, level)
            s.append([node.left, level + 1])
            s.append([node.right, level + 1])
    return res
```

### 🟢 [543. Diameter of Binary Tree](https://leetcode.com/problems/diameter-of-binary-tree)

#### My solution (good)
- recursive
- on every node return max length of one of its children and also the longest path ever found
- very similar to [110. Balanced Binary Tree] where you can propagate `balanced` state up the call stack, here we propagate longest path up the call stack
```python
def diameterOfBinaryTree(self, root: Optional[TreeNode]) -> int:
    def dfs(node) -> tuple[int,int]: # [max length from this node, longest path ever found]
        if not node: return (0, 0)
        l, longestOfL = dfs(node.left)
        r, longestOfR = dfs(node.right)
        longest = max(l+r, longestOfL, longestOfR)
        return (max(l, r) + 1, longest)

    return dfs(root)[1]
```

### 🟢 [100. Same Tree](https://leetcode.com/problems/same-tree)

#### My solution (good)
- recursive
- compare each node of both trees, recursively iterate
```python
def isSameTree(self, p: Optional[TreeNode], q: Optional[TreeNode]) -> bool:
    if not p and not q:
        return True
    if not p or not q or p.val != q.val:
        return False
    return self.isSameTree(p.left, q.left) and self.isSameTree(p.right, q.right)
```

### 🟢 [572. Subtree of Another Tree](https://leetcode.com/problems/subtree-of-another-tree)

#### My solution (brute-force)
- O(s+t)
- recursive, using isSameTree on each node
```python
def isSubtree(self, root: Optional[TreeNode], subRoot: Optional[TreeNode]) -> bool:
    if not root: return False
    isSame = self.isSameTree(root, subRoot)
    if isSame: return True
    return self.isSubtree(root.left, subRoot) or self.isSubtree(root.right, subRoot)

def isSameTree(self, t1: Optional[TreeNode], t2: Optional[TreeNode]) -> bool:
    if not t1 and not t2: return True
    if not t1 or not t2 or t1.val != t2.val: return False
    return self.isSameTree(t1.left, t2.left) and self.isSameTree(t1.right, t2.right)
```
#### Good solution
- flatten trees into strings
- check if subtree string exists in tree string
- alternatively can be done without strings, just flattening node of both trees into 2 stacks and comparing stacks like strings
```python
def isSubtree(self, root: Optional[TreeNode], subRoot: Optional[TreeNode]) -> bool:
    def treeToString(p):
        # "." is needed to indicate the beginning of a tree (for example for case [12]vs[2])
        return "." + str(p.val) + treeToString(p.left) + treeToString(p.right) if p else "X"
    return treeToString(subRoot) in treeToString(root)
```

### 🟢 [70. Climbing Stairs](https://leetcode.com/problems/climbing-stairs)

__DP__

#### My solution
- O(n)
- recursion + memoization
- on each step there are 2 choices: go 1 stair up or 2 stairs up
- treat the problem as binary tree, do DFS, memoize
```python
def climbStairs(self, n: int) -> int:
    memo = {-1:0, 0:1}
    def step(n):
        if n in memo:
            return memo[n]
        memo[n-1] = step(n-1)
        memo[n-2] = step(n-2)
        return memo[n-1] + memo[n-2]
    return step(n)
```
#### DP solution (bottom-up, iterative)
- O(n)
- iterative
- recursive solution can become iterative if you think about this problem __bottom-up__ (from final case to initial case)
- E.g. on n=5, calculate num of steps needed to take from stair 5, then 4, then 3, etc. Each lower step will be just the sum of 2 higher steps
```python
def climbStairs(self, n: int) -> int:
    oneToLast, last = 1, 0 # there is only 1 way to do n-1->n and 0 ways to do n->n
    for i in range(n):
        # move oneToLast to the left, calculating its result as the sum or next 2
        # also move last to left
        oneToLast, last = oneToLast+last, oneToLast
    return oneToLast
```

### 🟢 [746. Min Cost Climbing Stairs](https://leetcode.com/problems/min-cost-climbing-stairs)

__DP__

#### My solution
- O(n)
- iterative
- bottom-up
- same logic as in [70. Climbing Stairs] but on each iteration we need to calculate min cost of 2 previous options
- then follow from right to left the same way
```python
def minCostClimbingStairs(self, cost: List[int]) -> int:
    l, r = 0, 0
    for i in range(len(cost)-1, -1, -1):
        l, r = min(cost[i]+l, cost[i]+r), l
    return min(l, r)
```
#### Solution without pointers
- the only difference is that we mutate current value in the array to a new value, instead of using 2 pointers
```python
def minCostClimbingStairs(self, cost: List[int]) -> int:
    cost.append(0)
    for i in range(len(cost)-3, -1, -1):
        cost[i] += min(cost[i+1], cost[i+2])
    return min(cost[0], cost[1])
```

### 🟡 [49. Group Anagrams](https://leetcode.com/problems/group-anagrams)

#### My solution (brute-force)
- add every new word to the list of its anagrams, or create a new list for it
```python
def groupAnagrams(self, strs: List[str]) -> List[List[str]]:
    res = []
    for s in strs:
        added = False
        for r in res:
            if self.isAnagram(s, r[0]):
                r.append(s)
                added = True
        if not added:
            res.append([s])
    return res

def isAnagram(self, s1: str, s2: str) -> bool:
    return Counter(s1) == Counter(s2)
```
#### Good solution
- O(n*m)
- go over each word, over each char in it, and count them up
- add count of chars for each word as a key to `res`
- if next word's char count matches one of the other char counts, it's going to go under the same key in this map
- if not, it will create a new key in this map
- return all values (each key has a list of words) of this map
```python
def groupAnagrams(self, strs: List[str]) -> List[List[str]]:
    res = defaultdict(list)
    for s in strs:
        count = [0]*26 # a..z
        for c in s:
            count[ord(c) - ord("a")] += 1
        res[tuple(count)].append(s)
    return res.values()
```

### 🟡 [347. Top K Frequent Elements](https://leetcode.com/problems/top-k-frequent-elements)

#### My solution
- use hashmap to count occurrences
- then sort it by count
- then return k top
- less efficient than optimal (see below)
```python
def topKFrequent(self, nums: List[int], k: int) -> List[int]:
    count = {}
    for n in nums:
        if n in count:
            count[n] = [n, count[n][1]+1]
        else:
            count[n] = [n, 1]
    res = sorted(count.values(), reverse=True, key=lambda arr: arr[1])
    return [arr[0] for arr in res[0:k]]
```
#### Good solution
- O(n) !
- use hashmap to count occurrences
- then put them into a n-length array, where i is count and value is list of nums with this count
- the iterate this array backwards to return top k nums
```python
def topKFrequent(self, nums: List[int], k: int) -> List[int]:
    count = {}
    freq = [[] for _ in range(len(nums)+1)]
    for n in nums:
        count[n] = count.get(n, 0) + 1
    for n, c in count.items():
        freq[c].append(n)
    res = []
    for i in range(len(freq)-1, 0, -1):
        for n in freq[i]:
            res.append(n)
            if len(res) == k:
                return res # will always execute at some point according to problem definition
    # Final step but using list comprehension
    # ll = [freq[i] for i in range(len(freq)-1, 0, -1)]
    # return [item for sublist in ll for item in sublist][0:k]
```

### 🟡 [238. Product of Array Except Self](https://leetcode.com/problems/product-of-array-except-self)

#### My solution (optimal)
- O(n) space, O(1) time
- iterate over the array twice
- on first pass (l->r): compute prefix, and store in results array
- on second pass (l<-r): compute postfix and multiply prefixes in result
```python
def productExceptSelf(self, nums: List[int]) -> List[int]:
    res = [1 for _ in nums]
    for i in range(1, len(nums)):
        res[i] = res[i-1] * nums[i-1]
    p = 1
    for i in range(len(nums)-2, -1, -1):
        p = p * nums[i+1]
        res[i] = res[i] * p
    return res
```

### 🟡 [36. Valid Sudoku](https://leetcode.com/problems/valid-sudoku)

#### Good solution
- iterate over the board once
- on each value, check if its present in current row, column and square
- if yes, return false, if no - add value to row, column and square maps
- to keeps squares we will have these keys for them:
  (0,0)(0,1)(0,2)
  (1,0)(1,1)(1,2)
  (2,0)(2,1)(2,2)
- each key here identifies one square, to get this key we just need to int-divide current row//3 and current col//3
```python
def isValidSudoku(self, board: List[List[str]]) -> bool:
    rows = collections.defaultdict(set)
    cols = collections.defaultdict(set)
    squares = collections.defaultdict(set) # key = (row//3,col//3)
    for r in range(9):
        for c in range(9):
            v = board[r][c]
            if v == '.':
                continue
            if (v in rows[r] or
                v in cols[c] or
                v in squares[(r//3,c//3)]):
                return False
            cols[c].add(v)
            rows[r].add(v)
            squares[(r//3,c//3)].add(v)
    return True
```

### 🟡 [271. Encode and Decode Strings](https://leetcode.com/problems/encode-and-decode-strings)

#### My solution
- O(n)
- using ord/chr to encode each char
- sub-optimal, because it's slow
```python
class Codec:
    def encode(self, strs: List[str]) -> str:
        res = ""
        for s in strs:
            for c in s:
                res += str(ord(c)) + "_"
            res += ",_"
        return res[0:-3]

    def decode(self, s: str) -> List[str]:
        if not s: return [""]
        res = []
        word = ""
        chars = s.split("_")
        for c in chars:
            if (c == ","):
                res.append(word)
                word = ""
            else:
                word += chr(int(c))
        res.append(word)
        return res
```
#### Good solution
- O(n)
- append next word length to the beginning of that word
- example: ["Hello", "world"] -> "5-Hello5-world"
```python
class Codec:
    def encode(self, strs: List[str]) -> str:
        res = ""
        for s in strs:
            res += str(len(s)) + "-" + s # - is a delimiter between string length and string itself
        return res

    def decode(self, s: str) -> List[str]:
        res, i, nextWordLen = [], 0, ""
        while i < len(s):
            if s[i] == "-":
                res.append(s[i+1:i+1+int(nextWordLen)])
                i += int(nextWordLen) + 1
                nextWordLen = ""
            else:
                nextWordLen += s[i]
                i += 1
        return res
```

### 🟡 [128. Longest Consecutive Sequence](https://leetcode.com/problems/longest-consecutive-sequence)

__Lessons learned:__
- When drawing the problem, identify unique properties of each entity. E.g. sequences start without the left neighbor. We don't need to consider n with the left neighbor because it's not start of a sequence.
- By converting array to `set` we can always check for any number with O(1) complexity. So while iterating we can check even the future numbers before we iterated to them.

#### My solution (space is suboptimal)
- time O(n), space O(n), however take more space than optimal solution
- iterate over the list, for each n saving it to a map
- creating lists from nums on the left and right of n
```python
def longestConsecutive(self, nums: List[int]) -> int:
    longestArrLen = 0
    map = {} # {n:[]}
    for n in nums:
        if n in map: continue
        arr = [n]
        if n-1 in map:
            arr = map[n-1] + arr
        if n+1 in map:
            arr = arr + map[n+1]
        map[n] = [n]
        map[arr[0]] = arr
        map[arr[-1]] = arr
        longestArrLen = max(longestArrLen, len(arr))
    return longestArrLen
```
#### Good solution
- time O(n), space O(n)
- if you convert list to set we can always check prev/next neighbor while iterating
- important: sequences start with a num that doesn't have the left neighbor
- if n doesn't have a left neighbor - start counting right, while saving longest found seq
```python
def longestConsecutive(self, nums: List[int]) -> int:
    longestSeqLen = 0
    numsSet = set(nums)
    for n in numsSet:
        if n-1 not in numsSet:
            seqLen = 1
            while n + seqLen in numsSet:
                seqLen += 1
            longestSeqLen = max(longestSeqLen, seqLen)
    return longestSeqLen
```









































/
