package main

import (
	"fmt"
	"strconv"
)

func numToString(num int) string {
	// Convert the integer to a string using strconv.Itoa
	return strconv.Itoa(num)
}

func main() {
	// Call numToString and print the result
	fmt.Println(numToString(7000000))
}