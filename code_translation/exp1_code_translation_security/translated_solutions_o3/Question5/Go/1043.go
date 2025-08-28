package main

import (
	"fmt"
	"strconv"
)

// numToString converts an int to its string representation.
func numToString(num int) string {
	return strconv.Itoa(num)
}

func main() {
	fmt.Print(numToString(7000000))
}