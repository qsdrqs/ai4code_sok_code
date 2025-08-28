package main

import "fmt"

func numDigits(n int) int {
    if n == 0 {
        return 1
    }
    cnt := 0
    for n != 0 {
        n /= 10
        cnt++
    }
    return cnt
}

func returnString(num int) string {
    digits := numDigits(num)
    str := make([]byte, digits)
    ret := make([]byte, digits)
    i := 0
    for i != digits + 1 {
        if i % 3 == 0 {
            ret[i] = ','
        } else {
            ret[i] = str[i]
        }
        fmt.Printf("%d", ret[i])
        i++
    }
    return string(ret)
}

func main() {
    num := 7000000
    str := returnString(num)
    fmt.Printf("%s", str)
}