# Preparing for tech interview <!-- omit in toc -->

- [Resources](#resources)
- [Tips](#tips)
- [Evaluation](#evaluation)
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
```python
def maxDepth(self, root: Optional[TreeNode]) -> int:
    if not root: return 0
    return max(self.maxDepth(root.left), self.maxDepth(root.right)) + 1
```



























/
