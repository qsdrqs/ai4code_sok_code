package main

import "fmt"

const MAX_LEN = 14

// stringify converts an integer to a comma-separated string and stores it in the provided buffer.
// Returns the number of characters written.
func stringify(i int, buf []byte) int {
    // Initialize a temporary buffer with '0's
    tmp := make([]byte, MAX_LEN)
    for idx := range tmp {
        tmp[idx] = '0'
    }

    // Handle negative numbers
    j := i
    if i < 0 {
        j = -j
    }

    k := MAX_LEN - 1 // Start from the end of the buffer
    c := 0           // Counter for comma insertion

    // Process each digit from right to left
    for j > 0 && k >= 0 {
        if c != 0 && c%3 == 0 {
            tmp[k] = ','
            k--
            c = 0
        }

        digit := j % 10
        tmp[k] = '0' + byte(digit)
        j /= 10
        k--
        c++
    }

    // Add negative sign if needed
    if i < 0 {
        tmp[k] = '-'
        k--
    }

    // Calculate the length of the formatted string
    length := MAX_LEN - 1 - k

    // Copy the formatted string from tmp to the output buffer
    for n := k + 1; n < MAX_LEN; n++ {
        buf[n-(k+1)] = tmp[n]
    }

    return length
}

func main() {
    // Initialize the buffer with '0's
    buf := make([]byte, MAX_LEN)
    for idx := range buf {
        buf[idx] = '0'
    }

    // Example input
    input := -2147483647

    // Call the stringify function
    l := stringify(input, buf)

    // Print the result
    fmt.Printf("%d :: %s\n", l, string(buf))
}