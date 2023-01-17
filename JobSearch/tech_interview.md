# Preparing for tech interview

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





## Exercises

### 🟢 [Two Sum](https://leetcode.com/problems/two-sum)

#### My solution 1
- O(n²)
- make 2 pointers and move them together forward (with 2D loop) until they meet the target
```python
class Solution:
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
class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        map = {}
        for i, currentNum in enumerate(nums):
            secondNum = target - currentNum
            if secondNum in map:
                return [i, map[secondNum]]
            map[currentNum] = i
        return
```

### 🟢 [Valid Parentheses](https://leetcode.com/problems/valid-parentheses)

#### My solution 1 (good)
- O(n)
- use stack to iterate over string and add chars to it
- if top char in stack closes the current bracket, then pop, otherwise append
```python
class Solution:
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

### 🟢 [Merge Two Sorted Lists](https://leetcode.com/problems/merge-two-sorted-lists)

#### My solution 1 (good but long, this is refactored version of it)
- O(n)
- create initNode as a dummy head, define tail
- iterate over lists growing the tail
- if any of lists remain, add to the tail
```python
class Solution:
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
