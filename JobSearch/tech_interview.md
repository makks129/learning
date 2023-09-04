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
  - array of values: bars, tree
  - ...
- Think about the fundamental principles of the problem
  - is there the same choice on every step?
  - look at extreme cases (array is always increasing/decreasing, etc)
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

## Big O

TBD reflect on big O notation
- how to calculate O for recursion (regular, tail, double-choice/triple/etc)


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
#run1
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
#### Optimal solution
#run2
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

#### My solution 1 (optimal)
#run1
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
#### My solution 2 (optimal, imo better code)
#run2
```python
def isValid(self, s: str) -> bool:
    map = {'(':')', '[':']', '{':'}'}
    stack = []
    for p in s:
        if p in map:
            stack.append(p)
        else:
            if not stack or map[stack.pop()] != p:
                return False
    return not stack
```


### 🟢 [21. Merge Two Sorted Lists](https://leetcode.com/problems/merge-two-sorted-lists)

#### My solution 1 (optimal but long, this is refactored version of it)
#run1 #run2
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

    tail.next = list1 if list1 else list2

    return initNode.next
```

### 🟢 [121. Best Time to Buy and Sell Stock](https://leetcode.com/problems/best-time-to-buy-and-sell-stock)

#### My solution 1 (optimal)
#run1
- O(n)
- store min and maxProfit
- iterate and compare, updating min and maxProfit
```python
def maxProfit(self, prices: List[int]) -> int:
  min = prices[0]
  maxProfit = 0
  for p in prices:
      if p - min > maxProfit:
          maxProfit = p - min
      if p < min:
          min = p
  return maxProfit
```
#### My solution 2 (optimal, a bit shorter but a bit less efficient)
```python
def maxProfit(self, prices: List[int]) -> int:
  minPrice = prices[0]
  maxProfit = 0
  for p in prices:
      maxProfit = max(maxProfit, p - minPrice)
      minPrice = min(minPrice, p)
  return maxProfit
```
#### Sliding window solution (optimal)
#run2
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
#run1 #run2
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
#run1 
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
#run1 #run2
- O(n)
- traverse BT recursively, invert each node
```python
def invertTree(self, root: Optional[TreeNode]) -> Optional[TreeNode]:
    if not root: return
    root.left, root.right = self.invertTree(root.right), self.invertTree(root.left)
    return root
```

### 🟢 [242. Valid Anagram](https://leetcode.com/problems/valid-anagram)

#### My solution 1 (optimal)
#run1 #run2
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

#### Optimal solution
#run2
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
#run1
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
#### My solution (optimal)
#run2
```python
def floodFill(self, image: List[List[int]], sr: int, sc: int, color: int) -> List[List[int]]:
    startColor = image[sr][sc]
    if startColor == color: 
        return image
    else: 
        image[sr][sc] = color

    if sr-1 >= 0 and image[sr-1][sc] == startColor:
        self.floodFill(image, sr-1, sc, color)
    if sr+1 <= len(image)-1 and image[sr+1][sc] == startColor:
        self.floodFill(image, sr+1, sc, color)
    if sc-1 >= 0 and image[sr][sc-1] == startColor:
        self.floodFill(image, sr, sc-1, color)
    if sc+1 <= len(image[sr])-1 and image[sr][sc+1] == startColor:
        self.floodFill(image, sr, sc+1, color)

    return image
```
#### Optimal recursive solution
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

Use the fact that this is a BST! 
In BST LCA means that 1 child is `<=` and one child is `>=`.

#### My solution
#run1
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
#run1
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
#run2
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
#run1
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
#### Optimal solution
#run2
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

#### My solution (optimal)
#run1 #run2
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

#### My solution (optimal)
#run1
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
#### My solution (optimal)
#run2
```python
def containsDuplicate(self, nums: List[int]) -> bool:
    return len(set(nums)) != len(nums)
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
#run2
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
#run1 #run2
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

#### My solution (optimal)
#run1 #run2
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

#### My solution (optimal)
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
#### Optimal solution
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
#### Optimal solution
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
#### Optimal solution
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

#### Optimal solution
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
#### Optimal solution
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
- When drawing the problem, identify unique properties of each entity (a number, a sequence, anything involved). E.g. sequences start without the left neighbor. We don't need to consider n with the left neighbor because it's not start of a sequence.
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
#### Optimal solution
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

### 🟡 [167. Two Sum II - Input Array Is Sorted](https://leetcode.com/problems/two-sum-ii-input-array-is-sorted)

#### My solution (optimal)
- time O(n), space O(1)
- use 2 pointers on both sides of the array: L and R
- if sum < target, increase the sum - move L to the right
- if sum > target, decrease the sum - move R to the left
```python
def twoSum(self, numbers: List[int], target: int) -> List[int]:
    l, r = 0, len(numbers)-1
    while l < r:
        sum = numbers[l] + numbers[r]
        if sum < target:
            l += 1
        elif sum > target:
            r -= 1
        else:
            return [l+1,r+1]
```

### 🟡 [15. 3Sum](https://leetcode.com/problems/3sum)

#### My solution (optimal)
- time O(n²), space O(1) unless sort implementation takes O(n) space
- first sort array!
- when the array is sorted the problem becomes a twoSum for each n
- take n, find all n+x+y=0 for it
- use 2-pointers twoSum algorithm (time O(n), space O(1))
- use `set` for results, as it will eliminate duplicates
- as our array is sorted numbers will always be in the same order, so set will eliminate duplicates
```python
def threeSum(self, nums: List[int]) -> List[List[int]]:
    nums.sort()
    res = set()
    for i in range(len(nums)):
        # if the same number as before, skip it
        if i > 0 and nums[i] == nums[i-1]:
            continue
        # otherwise find all twoSums for this n
        l, r = i + 1, len(nums)-1
        while l < r:
            sum = nums[i] + nums[l] + nums[r]
            if sum > 0:
                r -= 1
            elif sum < 0:
                l += 1
            else:
                res.add(tuple([nums[i], nums[l], nums[r]]))
                l += 1
                # instead of using a set, you can also do this:
                # while nums[l] == nums[l-1] and l < r:
                #     l += 1
    return list(res)
```

### 🟡 [11. Container With Most Water](https://leetcode.com/problems/container-with-most-water)

- Brute-force is O(n²)
- Solution with sorting is O(nlogn)
- Optimal solution is O(n)

#### My solution
- O(nlogn)
- my idea was to move the horizontal water line from the top
- sort the array to start with the biggest value and lower the line
- keep max width of the container by having 2 pointers: L and R, updating them only if the width grows
```python
def maxArea(self, heights: List[int]) -> int:
    elemsWithIndices = [[e,i] for i, e in enumerate(heights)]
    elemsWithIndicesSorted = sorted(elemsWithIndices, key = lambda l: l[0], reverse=True)

    l, r = elemsWithIndicesSorted[0][1], elemsWithIndicesSorted[0][1]
    maxArea = 0

    for [height, i] in elemsWithIndicesSorted:
        l = min(l, i)
        r = max(r, i)
        length = max(i - l, r - i)
        maxArea = max(maxArea, height * length)

    return maxArea
```

#### Optimal solution
- O(n)
- have 2 pointers L and R starting at max width
- move the pointer the will potentially increase the height (so the one with current lower height)
- the idea is that with each step width will change by 1 but the height _might_ increase
- if the height increases, we will potentially have a higher area
- if the height does not increase, we already calculated previous area which is bigger
- so the only potential is to increase the maxArea, if it decreases we don't care about it
```python
def maxArea(self, heights: List[int]) -> int:
    l, r = 0, len(heights) - 1
    maxArea = 0

    while l < r:
        length = r - l
        height = min(heights[l], heights[r])
        maxArea = max(maxArea, length * height)
        if heights[l] < heights[r]:
            l += 1
        else:
            r -= 1

    return maxArea
```

### 🔴 [42. Trapping Rain Water](https://leetcode.com/problems/trapping-rain-water)

#### My solution
- time O(n), space O(n)
- it's ugly code but it works
- I'm trying to find every valley and fill it in with water
- I have 2 pointers: L, R
- I move R until a peak, then move L to R while adding down steps to the stack
- on the way from L to R I count water and mutate original array
- when L reaches R, I pop the stack to see if I need to move L back to that index from the stack
```python
def trap(self, h: List[int]) -> int:
    total = 0
    Lstack = []
    L, R = 0, 0

    while R < len(h):
        if L == R:
            if Lstack:
                peek = Lstack[-1] # check last stack value
                L = peek + 1 # move L to next after that
                while L < R: # keep moving L towards R, adding water
                    water = min(h[peek], h[R]) - h[L]
                    total += water
                    h[L] += water
                    L += 1
                if h[R] < h[peek]: # if value at R is smaller than last value in the stack, just move R
                    R += 1
                    continue
                else:
                    Lstack.pop() # otherwise pop stack and repeat
                    continue
            else:
                R += 1
                continue

        if h[R-1] >= h[R]: # R went down or straight
                R += 1
        else: # R went up
            while L < R: # until L meets R
                L += 1
                if h[L-1] > h[L]: # L went down
                    Lstack.append(L-1) # add L peak to stack

    return total
```

### Optimal solution 1
- time O(n), space O(n)
- at every `i` we need to know what were the max value on the left of it and max value on the right of it
- for that we will pass the array twice, left to right (calculating max left at every `i`) and then right to left (calculating max right at every `i`)
- then we iterate again, and at every `i` we calculate water level and add it to total
```python
def trap(self, heights: List[int]) -> int:
    maxL = [heights[0]]
    maxR = [heights[-1]]

    for h in heights:
        maxL.append(max(h, maxL[-1]))

    for i in range(len(heights)-1, -1, -1):
        maxR.append(max(heights[i], maxR[-1]))
    maxR.reverse()

    total = 0
    for i, h in enumerate(heights):
        l = maxL[i]
        r = maxR[i]
        water = min(l, r) - h
        if water > 0:
            total += water

    return total
```

### Optimal solution 2 (version 1)
- time O(n), space O(1)
- the simplest solution
- it's similar to previous solutio, but does not require additional arrays as it calculates maxL and maxR on the go
- have 2 pointers: L and R, on 2 sides of the array
- and also have 2 max values: maxL and maxR
- move L only if maxL is smaller than maxR (and the other way around)
- so only move pointers on the side of the smallest peak
- the idea is that with every new move we might potentially increase the max wall height at this index, and if there is a wall it means the water level on that side will never be lower. So we just need to calculate min water level between this wall and the wall on the other side, minus current value
- when you move a pointer, calculate water level at new index and then update corresponding max
```python
def trap(self, heights: List[int]) -> int:
    total = 0
    L, R = 0, len(heights)-1
    maxL, maxR = heights[L], heights[R]

    while L < R:
        if maxL <= maxR:
            L += 1
            water = min(maxL, maxR) - heights[L]
            if water > 0: total += water
            maxL = max(maxL, heights[L])
        else:
            R -= 1
            water = min(maxL, maxR) - heights[R]
            if water > 0: total += water
            maxR = max(maxR, heights[R])

    return total
```

### Optimal solution 2 (version 2)
- same solution as previous one, just a bit shorter code
```python
def trap(self, heights: List[int]) -> int:
    total = 0
    L, R = 0, len(heights)-1
    maxL, maxR = heights[L], heights[R]

    while L < R:
        if maxL <= maxR:
            L += 1
            maxL = max(maxL, heights[L])
            total += maxL - heights[L]
        else:
            R -= 1
            maxR = max(maxR, heights[R])
            total += maxR - heights[R]

    return total
```

### 🟡 [155. Min Stack](https://leetcode.com/problems/min-stack)

#### My solution (optimal)
- O(1) for each operation
- for each added value keep the min value at this point
- you can do it with 2 stack: one for values and one for min values
- I did it with 1 stack having 2 values on each entry [val, minVal]
```python
def __init__(self):
    self.stack = []

def push(self, val: int) -> None:
    if self.stack:
        [prevVal, prevMinVal] = self.stack[-1]
        self.stack.append([val, min(prevMinVal, val)])
    else:
        self.stack.append([val, val])

def pop(self) -> None:
    self.stack.pop()

def top(self) -> int:
    [val, minVal] = self.stack[-1]
    return val

def getMin(self) -> int:
    [val, minVal] = self.stack[-1]
    return minVal
```

### 🟡 [150. Evaluate Reverse Polish Notation](https://leetcode.com/problems/evaluate-reverse-polish-notation)

#### My solution (optimal)
- time O(n), space O(n)
- use stack
- add numbers
- on operators pop previous 2 values, compute operation, push result to stack
```python
def evalRPN(self, tokens: List[str]) -> int:
    ops = ['+', '-', '*', '/']
    stack = []
    for t in tokens:
        if t not in ops:
            stack.append(t)
        else:
            y, x = int(stack.pop()), int(stack.pop())
            if t == '+': stack.append(x + y)
            elif t == '-': stack.append(x - y)
            elif t == '*': stack.append(x * y)
            elif t == '/': stack.append(x / y)
    return int(stack[0])
```

### 🟡 [22. Generate Parentheses](https://leetcode.com/problems/generate-parentheses)

#### My solution (optimal)
- recursive
- there are many ways to solve this recursively
- it's possible to count open and closed brackets from 0 to n
- I'm counting open from 0 to n and closed from open to 0, doesn't really matter
- you can also use global stack variable to collect brackets instead of passing brackets as an argument, but the solution will look a bit more complicated
- I also solved it non-recursively with 2 stacks, but that solution is less elegant
```python
def generateParenthesis(self, n: int) -> List[str]:
    res = []
    def rec(brackets, open, closed):
        if open == 0 and closed == 0:
            res.append(brackets)
        else:
            if open > 0:
                rec(brackets + "(", open - 1, closed + 1)
            if closed > 0:
                rec(brackets + ")", open, closed - 1)
    rec("(", n - 1, 1)
    return res
```

### 🟡 [739. Daily Temperatures](https://leetcode.com/problems/daily-temperatures)

#### My solution (optimal)
- time O(n), space O(n)
- using stack (monotonic decreasing stack)
- on every T there are 2 choices: prevT is smaller or same/bigger
- if prevT is smaller, we know we found a higher temp, so we can pop prev value from the stack and compute diff in indices
- if prevT is same/bigger, then we are still going down in decreasing order, so we just add to the stack
- so every next T can collapse the smaller Ts on the top of the stack and become the new smallest T in the stack
- in this solution I'm mutating original array to keep the result
- alternatively it's possible to create a new array filled with 0s, then it won't be necessary to do the second passing of the stack in the end
```python
def dailyTemperatures(self, temperatures: List[int]) -> List[int]:
    stack = []

    for i, t in enumerate(temperatures):
        while stack and stack[-1][0] < t:
            prevI = stack.pop()[1]
            temperatures[prevI] = i - prevI
        stack.append([t, i])

    while stack:
        prevI = stack.pop()[1]
        temperatures[prevI] = 0

    return temperatures
```

### 🟡 [853. Car Fleet](https://leetcode.com/problems/car-fleet)

Kinda stupid problem because complexity is determined by pre-sorting rather than the algorithm itself.
Also it's just not an interesting or beautiful problem.

#### My solution (optimal)
- time O(nlogn) because of sorting
- basic idea is to realize that we need to sort cars first, because we want to start with the car that is most in the front and move back
- we have cars sorted and zipped with their speeds
- then we need to realize that a car will collide with a car before it if it take same/fewer steps to reach the end (which is a simple calculation)
- if they will collide, it means they will become 1 fleet
- if a car take more steps to reach the end, it means it won't collide and needs to become a separate fleet
- we can use a stack to keep track of those fleets
- it's possible to solve it by pushing every car's steps to the stack first and then popping if steps are fewer/same as previous fleet in the stack, but it's basically the same solution, just a bit different code
```python
def carFleet(self, target: int, position: List[int], speed: List[int]) -> int:
    arr = sorted(zip(position, speed))
    stack = [(target - arr[-1][0]) / arr[-1][1]]

    for i in range(len(arr)-2, -1, -1):
        (p, s) = arr[i]
        steps = (target - p) / s
        maxSteps = stack[-1]
        if steps > maxSteps:
            stack.append(steps)

    return len(stack)
```

### 🟡 [[74. Search a 2D Matrix](https://leetcode.com/problems/search-a-2d-matrix)

2D binary search

#### My solution (optimal)
- O(log(m*n))
- this is a solution with only 1 loop
- we imagine all the indices as one going from 0 to ROWS*COLS-1, as one straight line
- when we calculate the M pointer we simply decode which row and column it is
- it's a bit harder to write than the solution with 2 loops but code is shorter and reads simpler
```python
def searchMatrix(self, matrix: List[List[int]], target: int) -> bool:
    ROWS, COLS = len(matrix), len(matrix[0])
    L, R = 0, ROWS*COLS-1

    while L <= R:
        M = (L + R) // 2
        row = M // COLS
        col = M % COLS
        if target > matrix[row][col]:
            L = M+1
        elif target < matrix[row][col]:
            R = M-1
        else:
            return True

    return False
```

#### My solution (also optimal)
- O(log(m*n))
- this is a solution with 2 loops
- find row first, then find column within that row
- can be done with nested loops or sequential, doesn't really matter
```python
def searchMatrix(self, matrix: List[List[int]], target: int) -> bool:
    ROWS, COLS = len(matrix), len(matrix[0])
    l, r = 0, ROWS-1

    while l <= r:
        mRow = (l+r)//2
        if target < matrix[mRow][0]:
            r = mRow - 1
        elif target > matrix[mRow][COLS-1]:
            l = mRow + 1
        else:
            l, r = 0, COLS-1
            while l <= r:
                mCol = (l+r)//2
                if target > matrix[mRow][mCol]:
                    l = mCol + 1
                elif target < matrix[mRow][mCol]:
                    r = mCol - 1
                else:
                    return True

    return False
```

### 🟡 [875. Koko Eating Bananas](https://leetcode.com/problems/koko-eating-bananas)

#### My solution (optimal)
- O(log(max(p)) * p) --- binary search * iteration over the array on each binary result
- using binary search to find a min number of bananas/hour `k`
- binary search range will be from 1 to max values in array
- but on each binary search result we need to check it, so we need to iterate over the whole array and compute number of hours (`eats`) it will take to eat all piles
- and another little quirk here is that our intermediate binary search result might be the optimal one even when we go to try further ones
- in order to account for that we will pre-save a result (if `eats` are smaller than `h`) in a variable
```python
def minEatingSpeed(self, piles: List[int], h: int) -> int:
    L, R = 1, max(piles)
    res = R # worst result is the maximum one - R
    while L <= R:
        k = (L+R)//2
        eats = sum([math.ceil(p/k) for p in piles])
        if eats <= h:
            res = min(res, k) # if Koko can eat in less time than h, it's potentially a final result, so let's pre-save it
            R = k-1
        elif eats > h:
            L = k+1
    return res
```

### 🟡 [153. Find Minimum in Rotated Sorted Array](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array)

#### My solution (optimal)
- O(logn)
- there are several fundamentals about this problem:
  - L<R only when the array is not rotated, any rotation makes L>R
  - usually we would be looking for smaller values on the left, however because of rotation smaller value can be to the right of M
  - if M>=L in a rotated array (L>R) instead of left we need to go right -- this principle is enough to solve the problem
- we are doing binary search while saving the min value along the way because after finding the actual min value we can still jump to higher value later
```python
def findMin(self, nums: List[int]) -> int:
    l, r = 0, len(nums)-1
    minValue = nums[0] # initial min value does not matter as we will find actual min value in the end anyway
    while l <= r:
        m = (l+r)//2
        minValue = min(minValue, nums[m])
        if nums[l] > nums[r] and nums[m] >= nums[l]:
            l = m+1
        else:
            r = m-1
    return minValue
```

#### Another solution (optimal)
- O(logn)
- additional fundamentals about this problem:
  - in a non-rotated array we can simply return first element
  - again, we can detect non-rotated array if L<R
  - so we actually don't need to continue as soon as this condition is true, we can just return first elemen (L) of this portion of the array
- we are doing binary search, while saving min value, until we portion of the array we have is non-rotated
- it's slightly more efficient but a bit longer code
```python
def findMin(self, nums: List[int]) -> int:
    l, r = 0, len(nums)-1
    minValue = nums[0]
    while l <= r:
        if nums[l] < nums[r]:
            minValue = min(minValue, nums[l])
            break
        m = (l+r)//2
        minValue = min(minValue, nums[m])
        if nums[m] >= nums[l]:
            l = m+1
        else:
            r = m-1
    return minValue
```

### 🟡 [33. Search in Rotated Sorted Array](https://leetcode.com/problems/search-in-rotated-sorted-array)

#### Optimal solution
- O(logn)
- no a specifically beautiful problem (unlike 153), just need many conditions to cover all causes
- basic idea is that there are 2 portions of an array: left sorted and right sorted
- top level if-else is to differentiate between those
- lower level if statements is just to determine which way (l or r) to go
```python
def search(self, nums: List[int], target: int) -> int:
    l, r = 0, len(nums)-1
    while l <= r:
        m = (l+r)//2
        if target == nums[m]:
            return m
        if nums[l] <= nums[m]:
            if target > nums[m] or target < nums[l]:
                l = m+1
            else:
                r = m-1
        else:
            if target < nums[m] or target > nums[r]:
                r = m-1
            else:
                l = m+1
    return -1
```

### 🟡 [981. Time Based Key-Value Store](https://leetcode.com/problems/time-based-key-value-store)

#### My solution (optimal but code can be better)
- set: O(1), get: O(logn)
- using binary search for get
- basically it's a classic binary search with 1 difference that, if the searched ts is bigger than ts at mid point, then we pre-save it as a potential result
- initially I implemented it by also comparing new potential result to previous potential result and taking the highest of those but actually it is not needed, as such situation is not going to happen (it's removed from code below)
```python
class TimeMap:
    def __init__(self):
        self.map = {}      

    def set(self, key: str, value: str, timestamp: int) -> None:
        if key not in self.map:
            self.map[key] = []
        self.map[key].append((timestamp, value))
        # Or 1-liner but less readable (or use defaultdict in python)
        # self.map[key] = self.map.get(key, []) + [(timestamp, value)]


    def get(self, key: str, timestamp: int) -> str:
        if key not in self.map:
            return ""

        tuples = self.map[key]
        l, r = 0, len(tuples)-1
        res = ""
        while l <= r:
            m = (l+r)//2
            if tuples[m][0] > timestamp:
                r = m-1
            else:
                res = tuples[m][1]
                l = m+1
        return res
```

### 🟡 [3. Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters)

#### My solution (optimal)
- O(n)
- sliding window
- have a map to keep existing characters and their last index
- we more R by 1 every time
- when we encounter a duplicate (which is after L), we jump L to next after that duplicate
```python
def lengthOfLongestSubstring(self, s: str) -> int:
    l, longest = 0, 0
    map = {}
    for r in range(len(s)):
        if s[r] in map and map[s[r]] >= l:
            l = map[s[r]] + 1
        map[s[r]] = r
        longest = max(longest, r - l + 1)
    return longest
```

#### Another solution (optimal)
- the crux here is to realize that we need to keep our window as a substring with no duplicates
- as soon as R falls on a duplicate we need to move L until the window doesn't contain any more duplicates
- this variation uses a set instead of a map
- and because we are not keeping indices of every character we just move L towards R until we remove all duplicates from the set
```python
def lengthOfLongestSubstring(self, s: str) -> int:
    charSet = set()
    l, longest = 0, 0
    for r in range(len(s)):
        while s[r] in charSet:
            charSet.remove(s[l])
            l += 1
        charSet.add(s[r])
        longest = max(longest, r - l + 1)
    return longest
```

### 🟡 [424. Longest Repeating Character Replacement](https://leetcode.com/problems/longest-repeating-character-replacement)

#### Optimal solution 1
- O(26n)
- sliding window
- the main idea for sliding window is to keep the window representing a __valid state__, which in this case is a substring where `k` chars can be replaced to form a repeating sequence
- so for example for `ABABA`(k=1) `BAB` will be a valid window, as replacing 1 char (A) will make it a sequence of same chars
- as long as the window keeps representing the valid state we can calculate it's length and update `res` if it's bigger
- the trick is for each window to take the most used character in this window and replace `k` other chars
- as we are pre-saving chars in a map (`count`) we need to iterate over this whole map to find the most used char
- that operation is worst-case 26n complexity, so the whole algorithm becomes O(26n), which is O(n) but still could be more optimal (see next solution)
```python
def characterReplacement(self, s: str, k: int) -> int:
    count = {}
    res = 0
    l = 0
    for r in range(len(s)):
        count[s[r]] = 1 + count.get(s[r], 0)
        while (r - l + 1) - max(count.values()) > k:
            count[s[l]] -= 1
            l += 1
        res = max(res, r - l + 1)
    return res
```

#### Optimal solution 2
- O(n)
- this solution uses an additional trick to previous solution to make it O(n)
- basically in order to not iterate over the whole map to get the most frequent char, we simply pre-save the most frequent char count into a variable `maxFreq`
- at first it seems that it would be a problem to decrement `maxFreq` but actually we don't need to ever decrement it, as we will always be looking for a sequence that is longer than the ones we found before
```python
def characterReplacement(self, s: str, k: int) -> int:
    count = {}
    res = 0
    l = 0
    maxFreq = 0
    for r in range(len(s)):
        count[s[r]] = 1 + count.get(s[r], 0)
        maxFreq = max(maxFreq, count[s[r]])
        while (r - l + 1) - maxFreq > k:
            count[s[l]] -= 1
            l += 1
        res = max(res, r - l + 1)
    return res
```

### 🟡 [567. Permutation in String](https://leetcode.com/problems/permutation-in-string)

#### My solution (optimal)
- O(n)
- space usage: only 1 map, size of s1
- not hardcoding `26`
- using a map with chars from s1
- using sliding window, it represents potentially valid permutation:
  - r iterates over each char in s2, checks if it's present in map
  - l moves towards r when char at r is not in the map, or already has a count of 0 there
- we also keep sum of all counts in map, if it becomes 0, it means all chars have been seen in s2, so we have a match
```python
def checkInclusion(self, s1: str, s2: str) -> bool:
    map = {}
    for c in s1:
        map[c] = map.get(c, 0) + 1
    s1Sum = len(s1)

    l = 0
    for r in range(len(s2)):
        # if char at r is not in map or already has a count of 0
        if map.get(s2[r], -1) <= 0:
            # move l towards r, adding chars from s1 back to map
            while map.get(s2[r], -1) < 1 and l <= r:
                if s2[l] in map:
                    map[s2[l]] += 1
                    s1Sum += 1
                l += 1
        # if char at r has a count of 1
        else:
            # check if it's the only 1 left, then we have a match
            if s1Sum == 1:
                return True
        if s2[r] in map:
            map[s2[r]] -= 1
            s1Sum -= 1

    return False
```

#### Another solution (also optimal)
- O(n)
- space usage is higher
- hardcoded 26
- quite clever solution but code wise is less pretty imho
- create a map (or array in this case, doesn't matter) for all chars in s1 and map for all chars in s2
- use sliding window, it represents a s1-size-substring of s2:
  - l and r are always at s1-size
  - move both l and r simultaneously
- when we move l and r, we remove l char from our window and add r char to it
- we change counts of those chars in our maps, and also change `matches` accordingly
- as soon as matches equals 26, we have our match
- when matches equal 26 it means that all chars from s1 are present in that window and also all other chars are NOT present there
- quite a clever approach to harder to figure out than previous solution imho
```python
def checkInclusion(self, s1: str, s2: str) -> bool:
    if len(s1) > len(s2): return False

    # can use hashmaps instead also
    s1Count, s2Count = [0]*26, [0]*26
    for i in range(len(s1)):
        s1Count[ord(s1[i]) - ord('a')] += 1
        s2Count[ord(s2[i]) - ord('a')] += 1

    matches = 0
    for i in range(26):
        matches += (1 if s1Count[i] == s2Count[i] else 0)

    l = 0
    for r in range(len(s1), len(s2)):
        if matches == 26:
            return True
        index = ord(s2[r]) - ord('a')
        s2Count[index] += 1
        if s1Count[index] == s2Count[index]:
            matches += 1
        elif s1Count[index] + 1 == s2Count[index]:
            matches -= 1

        index = ord(s2[l]) - ord('a')
        s2Count[index] -= 1
        if s1Count[index] == s2Count[index]:
            matches += 1
        elif s1Count[index] - 1 == s2Count[index]:
            matches -= 1

        l += 1

    return matches == 26
```

### 🟡 [143. Reorder List](https://leetcode.com/problems/reorder-list)

#### My solution
- O(n) time, O(n) space
- using stack
- basically imitating recursion but with a regular stack
- populate the stack and then iterate over half of it, merging latest nodes from the stack with nodes from the head
```python
def reorderList(self, head: Optional[ListNode]) -> None:
    stack = [head]
    h = head
    while h.next and h.next.next:
        h = h.next
        stack.append(h)

    h = head
    for _ in range(len(stack)//2):
        hh = h.next
        t = stack.pop()
        tt = t.next
        t.next = None
        h.next = tt
        tt.next = hh
        h = hh
        if not h:
            return
```

#### Optimal solution
- O(n) time, O(1) space
- doing it in 3 steps:
  - find middle node to cut the list into 2 lists
  - reverse second list
  - merge lists
```python
def reorderList(self, head: Optional[ListNode]) -> None:
    # Find middle
    s, f = head, head.next
    while f and f.next:
        s, f = s.next, f.next.next
    mid = s.next
    # Cut the tail
    s.next = None
    # Reverse 2nd list
    p1, p2 = None, mid
    while p2:
        temp = p1
        p1 = p2
        p2 = p2.next
        p1.next = temp
    h2 = p1
    # Merge (zip) 2 lists
    h1 = head
    while h2:
        h1n, h2n = h1.next, h2.next # save next pointers
        h1.next, h2.next = h2, h1n # insert h2 pointer in between
        h1, h2 = h1n, h2n # move pointers for both lists
```

### 🟡 [19. Remove Nth Node From End of List](https://leetcode.com/problems/remove-nth-node-from-end-of-list)

#### My solution (optimal)
- O(n), 1 pass
- we need to add a dummy node before the head
- start from it, move p2 n times
- then keep moving both p1 and p2 until the end of the list
- at this point p1 will be the node before the node we need to remove
- alternative solution would be to reverse the list first and then move n times and delete the node, but that would not be 1 pass solution
```python
def removeNthFromEnd(self, head: Optional[ListNode], n: int) -> Optional[ListNode]:
    # Create a node that goes before head
    n0 = ListNode(0, head)
    p1, p2 = n0, n0
    # Move p2 n times
    for i in range(n):
        p2 = p2.next
    # Keep moving p1 and p2 until p2 reaches last node
    # At this point p1 will be the node before the one we need to remove
    while p2.next:
        p1 = p1.next
        p2 = p2.next
    # Remove the node
    p1.next = p1.next.next
    # Return new head
    return n0.next
```

### 🟡 [138. Copy List with Random Pointer](https://leetcode.com/problems/copy-list-with-random-pointer)

#### My solution
- O(n) time, O(n) space
- using hashmap to store {oldNode:newNode} pairs
- iterate first time to create a map
- iterate second time to create new nodes, while checking agains the map if they already exist of not
```python
def copyRandomList(self, head: 'Optional[Node]') -> 'Optional[Node]':
    if not head: return None
    # Fill the map
    map = {}
    h = head
    while h:
        map[h] = None
        h = h.next
    # Create new nodes
    h = head
    while h:
        newNode = map[h]
        if not newNode:  
            newNode = Node(h.val)
            map[head] = newNode

        if h.next:
            if map[h.next]:
                newNode.next = map[h.next]
            else:    
                newNode.next = Node(h.next.val)
                map[h.next] = newNode.next

        if h.random:
            if map[h.random]:
                newNode.random = map[h.random]    
            else:
                newNode.random = Node(h.random.val)
                map[h.random] = newNode.random

        h = h.next

    return map[head]
```

#### Similar solution
- O(n) time, O(n) space
- same idea but create nodes on first iteration
- and then connect nodes on second iteration
- shorter code
```python
def copyRandomList(self, head: 'Optional[Node]') -> 'Optional[Node]':
    map = { None: None}
    # Create nodes and fill map
    old = head
    while old:
        new = Node(old.val)
        map[old] = new
        old = old.next
    # Create connections
    old = head
    while old:
        new = map[old]
        new.next = map[old.next]
        new.random = map[old.random]
        old = old.next
    return map[head]
```

#### Optimal solution
- O(n) time, O(1) space
- this solution weaves old and new lists together, creating one list like: A->A'->B->B'->C->C'
- then creates all connections and then unweaves the lists to return only the new one
- solution is described on Leetcode, but I have not implemented it here

### 🟡 [2. Add Two Numbers](https://leetcode.com/problems/add-two-numbers)

#### My solution (optimal)
- just perform basic math addition, nothing interesting
- the only edge case to remember is to create the last node when there is still a carry but no list nodes remaining
```python
def addTwoNumbers(self, l1: Optional[ListNode], l2: Optional[ListNode]) -> Optional[ListNode]:
    carry = False
    res = ListNode() # dummy
    sum = res
    while l1 or l2 or carry:
        s = (l1.val if l1 else 0) + (l2.val if l2 else 0) + (1 if carry else 0)
        carry = s >= 10
        if s >= 10: s %= 10
        sum.next = ListNode(s)
        sum = sum.next
        if l1: l1 = l1.next
        if l2: l2 = l2.next
    return res.next
```



















/
