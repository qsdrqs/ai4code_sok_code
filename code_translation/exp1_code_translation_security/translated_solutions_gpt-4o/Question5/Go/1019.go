package main

import (
	"fmt"
	"math"
	"strconv"
)

func stringRep(num int) *string {
	// Calculate the size of the string representation
	size := int(math.Log10(float64(num))) + 1 // +1 for the null terminator equivalent in C

	// Allocate memory for the string (not explicitly needed in Go, but for clarity)
	str := make([]byte, size)

	// Convert the number to a string
	result := strconv.Itoa(num)

	// Copy the result into the allocated memory
	copy(str, result)

	// Return a pointer to the string
	return &result
}

func main() {
	leet := 1337
	leetStr := stringRep(leet)
	fmt.Println(*leetStr)
}