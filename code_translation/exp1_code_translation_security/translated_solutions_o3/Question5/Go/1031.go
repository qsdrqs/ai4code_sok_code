package main

import (
	"fmt"
	"strconv"
)

// intToString converts an int to its string representation,
// prints the value, and returns the string.
func intToString(input int) string {
	s := strconv.Itoa(input)     // convert int -> string
	fmt.Printf("you have entered: %s", s)
	return s
}

func main() {
	intToString(7000000)
}