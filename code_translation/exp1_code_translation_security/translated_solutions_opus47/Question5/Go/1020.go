package main

import (
	"fmt"
)

func main() {
	// define the integer you want to convert
	var number int
	number = -12

	// define the string you want to store the integer in
	var s string

	// convert the integer to a string
	s = fmt.Sprintf("%d", number)

	// print the string
	fmt.Printf("%s\n", s)
}