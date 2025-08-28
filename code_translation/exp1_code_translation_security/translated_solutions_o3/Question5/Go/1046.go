package main

import (
	"fmt"
	"strconv"
)

// convert turns an integer into its decimal string
// representation, prints that string, and returns it.
func convert(num int) string {
	buf := strconv.Itoa(num) // same as sprintf("%d", num)
	fmt.Print(buf)           // prints inside convert
	return buf
}

func main() {
	// Prints the result again (so the overall output will be 100100,
	// similar to what the author probably wanted).
	fmt.Print(convert(100))
}