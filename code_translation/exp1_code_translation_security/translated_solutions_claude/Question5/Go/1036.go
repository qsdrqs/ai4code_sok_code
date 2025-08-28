package main

import (
    "fmt"
    "strconv"
)

func numDigits(n int) int {
    if n == 0 {
        return 1
    }
    if n < 0 {
        n = -n
    }
    cnt := 0
    for n != 0 {
        n = n / 10
        cnt++
    }
    return cnt
}

func returnString(num int) string {
    // Convert number to string to get individual digits
    numStr := strconv.Itoa(num)
    digits := numDigits(num)
    
    // Create a slice to hold the result
    ret := make([]rune, 0)
    
    i := 0
    for i != (digits + 1) {
        // Add a comma at every third index
        if i%3 == 0 {
            ret = append(ret, ',')
        } else {
            if i-i/3-1 < len(numStr) { // Adjust index to account for commas
                ret = append(ret, rune(numStr[i-i/3-1]))
            }
        }
        fmt.Printf("%c", ret[len(ret)-1])
        i++
    }
    
    return string(ret)
}

func main() {
    num := 7000000
    str := returnString(num)
    fmt.Printf("%s", str)
}