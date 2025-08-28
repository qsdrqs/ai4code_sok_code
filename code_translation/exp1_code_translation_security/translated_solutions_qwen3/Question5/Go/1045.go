package main

import "fmt"

// Helper function to compute base^exponent
func power(base, exponent int) int {
    result := 1
    for exponent > 0 {
        result *= base
        exponent--
    }
    return result
}

// Converts an integer to a string with commas as thousand separators
func numberToString(num int) string {
    temp := num
    if num < 0 {
        temp = -num
    }

    // Calculate the number of digits in the number
    numSize := 0
    t := temp
    for t > 0 {
        t /= 10
        numSize++
    }

    // Calculate the number of commas needed
    commaCount := (numSize - 1) / 3

    // Allocate the byte slice to hold the result
    str := make([]byte, numSize+commaCount+1)
    i := 0

    // Handle the negative sign
    if num < 0 {
        str[i] = '-'
        i++
    }

    // Process the number
    n := temp
    for j := numSize - 1; j >= 0; j-- {
        pow := power(10, j)
        digit := n / pow
        str[i] = '0' + byte(digit)
        i++
        n = n % pow

        // Insert a comma after every 3 digits (from the right)
        if j%3 == 0 && j != 0 {
            str[i] = ','
            i++
        }
    }

    // Return the resulting string
    return string(str[:i])
}

// Main function to test the numberToString function
func main() {
    num := -7000000
    strNum := numberToString(num)
    fmt.Println(strNum)
}