package main

import (
	"fmt"
	"strconv"
)

// Function to convert an integer to a string
func intToStr(n int) string {
	return strconv.Itoa(n)
}

func main() {
	// Example usage
	num := 123
	str := intToStr(num)
	fmt.Println("Converted string:", str)
}