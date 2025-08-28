package main

import (
    "fmt"
    "strconv"
)

func intToStr(n int) string {
    return strconv.Itoa(n)
}

func main() {
    num := 123
    str := intToStr(num)
    fmt.Println(str)
}