package main

import (
	"fmt"
	"strconv"
)

func inttostring(x int) string {
	return strconv.Itoa(x)
}

func main() {
	// Example usage:
	x := 123
	str := inttostring(x)
	fmt.Println(str) // Output: 123
}