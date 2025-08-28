package main

import (
    "fmt"
    "strconv"
)

func int2str(num int) string {
    // Convert number to string
    str := strconv.Itoa(num)
    length := len(str)
    
    // Handle negative numbers - work with absolute value for formatting
    negative := false
    if num < 0 {
        negative = true
        str = str[1:] // Remove the negative sign temporarily
        length = len(str)
    }
    
    // Calculate new length with commas
    var newl int
    if length%3 == 0 {
        newl = length + length/3 - 1
    } else {
        newl = length + (length / 3)
    }
    
    // Add space for negative sign if needed
    if negative {
        newl++
    }
    
    // Create result slice
    result := make([]byte, newl)
    counter := 0
    j := newl - 1
    
    // Process digits from right to left
    for i := length - 1; i >= 0; i-- {
        counter++
        result[j] = str[i]
        j--
        
        // Add comma every 3 digits (but not at the beginning)
        if counter%3 == 0 && i > 0 {
            result[j] = ','
            j--
        }
    }
    
    // Add negative sign if needed
    if negative {
        result[0] = '-'
    }
    
    return string(result)
}

func main() {
    // Test the function
    fmt.Println(int2str(1234567))    // Should output: 1,234,567
    fmt.Println(int2str(-1234567))   // Should output: -1,234,567
    fmt.Println(int2str(123))        // Should output: 123
    fmt.Println(int2str(1234))       // Should output: 1,234
}