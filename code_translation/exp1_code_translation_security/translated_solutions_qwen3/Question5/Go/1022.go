package main

import "fmt"

// printNum recursively prints an integer with commas separating every three digits.
// It handles negative numbers by printing a minus sign and then calling itself
// with the absolute value of the number.
func printNum(num int) {
    // Handle negative numbers
    if num < 0 {
        fmt.Printf("-")
        printNum(-num)
        return
    }

    // Base case: if the number is less than 1000, print it directly
    if num < 1000 {
        fmt.Printf("%d", num)
        return
    }

    // Recursive case: process the higher digits first
    printNum(num / 1000)

    // Then print the current three-digit group with a leading comma
    fmt.Printf(",%03d", num%1000)
}

func main() {
    // Example usage: print the number -12345 with commas
    printNum(-12345)
    fmt.Println() // Add a newline after the output
}