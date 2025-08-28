package main

import (
    "fmt"
    "math"
    "strconv"
)

// number_to_string converts an integer into a string with commas as thousand separators.
func number_to_string(num int) string {
    // Determine if the number is negative
    isNegative := num < 0
    if isNegative {
        num = -num
    }

    // Convert the absolute value of the number to a string
    strNum := strconv.Itoa(num)

    // Initialize the result string and the index
    result := ""
    if isNegative {
        result += "-"
    }

    // Add commas as thousand separators
    for i := len(strNum) - 1; i >= 0; i-- {
        result = strNum[i] + result
        if (len(strNum)-i)%3 == 0 && i != 0 {
            result = "," + result
        }
    }

    return result
}

func main() {
    num := -7000000
    strNum := number_to_string(num)
    fmt.Println(strNum)
}