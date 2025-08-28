package main

import "fmt"

func numToString(num int) string {
    if num == 0 {
        return "0"
    }

    isNeg := num < 0
    if isNeg {
        num = -num
    }

    // Calculate total number of digits
    totalDigits := 0
    current := num
    for current > 0 {
        current /= 10
        totalDigits++
    }

    // Calculate number of commas needed
    numCommas := (totalDigits - 1) / 3
    strLen := totalDigits + numCommas
    if isNeg {
        strLen++
    }

    // Initialize the result buffer
    str := make([]byte, strLen)

    current = num
    index := 0
    digits := 0

    for current > 0 {
        d := current % 10
        current /= 10
        c := byte('0' + d)

        // Place the digit at the correct position
        pos := strLen - index - 1
        str[pos] = c
        index++
        digits++

        // Insert comma every 3 digits
        if digits%3 == 0 {
            commaPos := strLen - index - 1
            if commaPos >= 0 {
                str[commaPos] = ','
                index++
            }
        }
    }

    // Add negative sign if needed
    if isNeg {
        str[0] = '-'
    }

    return string(str)
}

func main() {
    fmt.Print(numToString(-5305000)) // Output: -5,305,000
}