package main

import (
    "fmt"
    "strconv"
)

// intToString converts an integer into a string with commas as thousand separators.
func intToString(num int) string {
    // Convert the integer to a string
    str := strconv.Itoa(num)

    // Initialize variables
    result := ""

    // Add commas as thousand separators
    for i := len(str) - 1; i >= 0; i-- {
        result = str[i] + result
        if (len(str)-i) % 3 == 0 && i != 0 {
            result = "," + result
        }
    }

    return result
}

func main() {
    // Test the intToString function
    fmt.Println(intToString(7000000))
}