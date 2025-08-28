package main

import (
    "fmt"
)

func intToString(num int) string {
    if num == 0 {
        return "0"
    }
    
    str := make([]byte, 0, 100)
    count := 0
    temp := num
    
    for temp != 0 {
        str = append(str, byte(temp%10)+'0')
        temp = temp / 10
        count++
        if count == 3 && temp != 0 {
            str = append(str, ',')
            count = 0
        }
    }
    
    // Reverse the string
    for i, j := 0, len(str)-1; i < j; i, j = i+1, j-1 {
        str[i], str[j] = str[j], str[i]
    }
    
    return string(str)
}

func main() {
    fmt.Print(intToString(7000000))
}