# Preparing for tech interview

### Resources
- Roadmap - https://neetcode.io/roadmap (approach https://www.youtube.com/watch?v=SVvr3ZjtjI8)
- Study guide - https://www.techinterviewhandbook.org/grind75?weeks=8
- Course - https://www.educative.io/courses/grokking-coding-interview-patterns-python
- Course - https://www.enjoyalgorithms.com/data-structures-and-algorithms-course/
- Course - https://designgurus.org/course/grokking-the-coding-interview
- Course - https://algo.monster/?sscid=11k7_guvme&
- Leetcode list - https://leetcode.com/list/?selectedList=owsdlmt3
- Mock coding interviews with real engineers - https://interviewing.io/?urc=DMCa


## Exercises

### 🟢 [Two Sum](https://leetcode.com/problems/two-sum/)

__My solution 1__
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

__Good solution__
- O(n)
- use hashmap to store previous values (val:i)
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

### 🟢 [Valid Parentheses](https://leetcode.com/problems/valid-parentheses/)

__My solution 1 (good)__
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
