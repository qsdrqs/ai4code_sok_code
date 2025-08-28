package main

import (
    "fmt"
    "math"
    "strconv"
    "strings"
)

// Function repr
// Takes in a signed integer and returns the string representation of that integer.
func repr(n int) string {
    if n == math.MinInt32 {
        // When multiplied by -1 this overflows, so handle it separately.
        return "-2,147,483,648"
    }
    if n == 0 {
        return "0"
    }

    sign := ""
    if n < 0 {
        sign = "-"
        n = -n
    }

    str := strconv.Itoa(n)
    if len(str) > 3 {
        // Add commas to the string
        str = addCommas(str)
    }

    return sign + str
}

// Function addCommas
// Adds commas to a string representation of an integer.
func addCommas(str string) string {
    result := ""
    count := 0
    for i := len(str) - 1; i >= 0; i-- {
        if count == 3 {
            result = "," + result
            count = 0
        }
        result = string(str[i]) + result
        count++
    }
    return result
}

// Function reverse
// Takes in a string and reverses it.
func reverse(str string) string {
    runes := []rune(str)
    for i, j := 0, len(runes)-1; i < j; i, j = i+1, j-1 {
        runes[i], runes[j] = runes[j], runes[i]
    }
    return string(runes)
}

func main() {
    n := 0
    str := repr(n)
    fmt.Println(str)
}