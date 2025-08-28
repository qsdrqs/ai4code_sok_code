package main

import (
    "fmt"
    "math"
    "strconv"
)

// MAX_STR_LEN represents the maximum length of a string
const MAX_STR_LEN = 128

// reverseStr returns the reversed string of the input string
func reverseStr(str string) string {
    runes := []rune(str)
    for i, j := 0, len(runes)-1; i < j; i, j = i+1, j-1 {
        runes[i], runes[j] = runes[j], runes[i]
    }
    return string(runes)
}

// strOfInt returns a string representation of a signed integer
func strOfInt(num int) string {
    str := ""
    isNeg := false
    if num < 0 {
        isNeg = true
        num = -num
    }
    if num == 0 {
        return "0"
    }
    for num > 0 {
        str += strconv.Itoa(num % 10)
        num /= 10
    }
    if isNeg {
        str += "-"
    }
    return reverseStr(str)
}

func main() {
    fmt.Println(strOfInt(7000000))
}