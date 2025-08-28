package main

import (
	"fmt"
	"strconv"
)

func main() {
	// define the integer you want to convert
	number := -12

	// convert the integer to a string
	str := strconv.Itoa(number) // or fmt.Sprintf("%d", number)

	// print the string
	fmt.Println(str)
}