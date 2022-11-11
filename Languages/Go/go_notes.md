## Variables

### Types
`string`

`bool`

`int int8 int16 int32 int64` // signed

`uint uint8 uint16 uint32 uint64` // unsined

`byte` - alias for uint8

`rune` - alias for int32

`float32 float64`

`complex64 complex128` // large numbers


### Declaration
`var` / `const`
```go
var name string = "Alice"
var name = "Alice" // type inference
name := "Alice" // shorthand (var)
```

### Pointers
Go is pass by value

```go
a := 42     // int, 42 - value
b := &a     // *int, 0xc00001231 - pointer to memory address of `a`
print(*b)   // 42 - read value from memory address
print(*&a)  // 42 - same, reference of pointer to value

*b = 69     // change value at memory address of `a`
print(a)    // 69
```

## Structs

### Declaration
```go
type Person struct { // declare
  name string
  age int
}

person := Person{name: "Alice", age: 30} // init, named
person := Person{"Alice", 30} // init, short
```

### Methods
```go
// Value receiver
func (p Person) greet() string {
  return "My name is " + p.name
}
// Pointer receiver
func (p *Person) incrementAge() {
  p.age++
}
```

### Interfaces
```go
type Shape interface {
  area() float64
}
type Circle struct {
  r float64
}
func getArea(s Shape) float64 {
  return s.area()
}
circle := Circle{r: 10}
getArea(circle)
```

## Collections

### Arrays
```go
var arr [2]string // declare
arr[0] = "foo" // assign

arr := [2]string{"foo", "bar"} // declare and assign
arr := []string{"foo", "bar", "baz"} // unbounded array

len(arr) // get length
arr[1:2] // slice array (incl, excl)
```

### Maps
```go
m := make(map[string]string) // declare
m["key"] = "value" // assign

m := map[string]string{"foo":"bar"} // declare and assign

len(m) // length of map
delete(m, "key") // deletes k-v pair
```

## Functions
```go
func foo(arg string) string { // blank return for void
  return "foo"
}

func sum(num1, num2 int) int { // can omit arg type if they are the same
  return num1 + num2
}

// HOFs and closures
func adder() func(int) int {
  sum := 0
  return func (x int) int {
    sum += x
    return sum
  }
}
```

## Conditionals
```go
if x < y {} // common convention is to not use ()
if (x < y) {} // not an error

switch color {
case "red": //
case "blue": //
default: //
}
```

## Loops
```go
// Long version ("while" version)
i := 0
for i < 10 {
  i++
}

// Short version
for i := 1; i < 10; i++ {}

// Loop range
nums := []int{100,200}
for i, num := range nums { // use `_` instead of `i` if index is not needed
  // 0, 100
  // 1, 200
}

// Loop map
for k, v := range m {} // or only `k` for just keys
```

## Goroutines
- Goroutine is a lightweight thread managed by the Go runtime (!= OS threads)
- Concurrent
- Efficient and can be executed in hundreds and even thousands
```go
// Sequential
runLongTask(1)
runLongTask(2)
// Concurrent
go runLongTask(1)
go runLongTask(2)
```

### Channels
- Channels are typed
- Sending and receiving are blocking operations and require both to be set up to complete
- Sync nature of channels allows to synchronize several goroutines
```go
func main() {
  channel := make(chan string)
  bufferedChannel := make(chan string, 2) // buffered channel with a capacity of 2, will not block until capacity is full
  go runTask(channel)

  msg, open := <- c // receive a message from a channel and open state  
  for msg := range c { // alternatively we can wrap receiver in for range, it will loop until the channel is closed (no need to check for open)  
  }
  select { // use select to wait for whatever message comes first from several channels (so receivers don't block each other)
    case msg1 := <- c1
    case msg2 := <- c2
  }
}

func runTask(channel chan string) {
  c <- "message" // send a message to the main channel from a goroutine
  close(c) // as a sender you can close the channel (receivers should not close channels)
}

// Specifying direction of a channel
jobs <-chan int // only to receive
results chan<- int // only to send
```


## Main
```go
// main.go
package main
func main() {...}
```
`go run main.go`
