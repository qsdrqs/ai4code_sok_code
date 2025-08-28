package main

import (
	"fmt"
	"strconv"
)

func convert(num int) string {
	// Convert the integer to a string
	buf := strconv.Itoa(num)
	fmt.Print(buf) // Print the string representation of the number
	return buf
}

func main() {
	// Call the convert function and print the first character of the returned string
	fmt.Printf("%c", convert(100)[0])
}