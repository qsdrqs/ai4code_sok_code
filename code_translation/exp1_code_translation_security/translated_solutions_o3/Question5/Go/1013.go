package main

import (
	"fmt"
	"strconv"
)

// convert turns an int into its string representation.
func convert(num int) string {
	// strconv.Itoa is the canonical way to convert an int to a string in Go.
	return strconv.Itoa(num)
}

func main() {
	ans := convert(700000)
	fmt.Println(ans) // Prints: 700000
}