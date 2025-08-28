package main

import "fmt"

// strOfInt converts a signed integer to its string representation.
// It handles negative numbers and ensures the digits are in the correct order.
func strOfInt(num int) string {
    isNeg := false
    if num < 0 {
        isNeg = true
        num = -num
    }

    // Handle the special case of zero
    if num == 0 {
        return "0"
    }

    // Build the digits in reverse order
    var digits []byte
    for num > 0 {
        digit := num % 10
        digits = append(digits, byte(digit)+'0')
        num /= 10
    }

    // Add the negative sign if needed
    if isNeg {
        digits = append(digits, '-')
    }

    // Reverse the digits to get the correct order
    reversed := make([]byte, len(digits))
    for i := 0; i < len(digits); i++ {
        reversed[i] = digits[len(digits)-i-1]
    }

    return string(reversed)
}

// main function prints the string representation of 7000000
func main() {
    fmt.Println(strOfInt(7000000))
}