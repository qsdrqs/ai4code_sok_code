package main

import (
	"fmt"
	"strconv"
)

func intToString(x int) string {
	// Convert the integer to a string using strconv.Itoa
	return strconv.Itoa(x)
}

func main() {
	// Example usage
	x := 123
	str := intToString(x)
	fmt.Println(str) // Output: "123"
}