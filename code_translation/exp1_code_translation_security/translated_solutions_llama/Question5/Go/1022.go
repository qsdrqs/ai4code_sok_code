package main

import "fmt"

// printNum prints a number with commas as thousand separators
func printNum(num int) {
    if num < 0 {
        fmt.Print("-")
        printNum(-num)
        return
    }
    if num < 1000 {
        fmt.Print(num)
        return
    }

    printNum(num / 1000)
    fmt.Printf(",%03d", num%1000)
}

func main() {
    printNum(-12345)
    fmt.Println() // Add a newline at the end
}