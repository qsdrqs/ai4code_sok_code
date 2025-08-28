package main

import (
    "fmt"
    "math/rand"
    "strconv"
    "time"
)

func main() {
    // Initialize random number generator
    rand.Seed(time.Now().UnixNano())

    // Generate a random number
    value := rand.Int63()
    fmt.Printf("Before: \t%d\n", value)

    // Convert the number to a string
    vstring := strconv.FormatInt(value, 10)

    // Initialize variables
    var commas string

    // Iterate over the string from right to left
    for i := len(vstring) - 1; i >= 0; i-- {
        commas = vstring[i] + commas
        if (len(vstring) - i) % 3 == 0 && i != 0 {
            commas = "," + commas
        }
    }

    fmt.Printf("With commas: \t%s\n", commas)
}