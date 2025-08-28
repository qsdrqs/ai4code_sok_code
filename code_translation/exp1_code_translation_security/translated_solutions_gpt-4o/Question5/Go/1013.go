package main

import (
	"fmt"
	"strconv"
)

func convert(num int) string {
	// Convert the integer to a string
	str := strconv.Itoa(num)
	return str
}

func main() {
	// Call the convert function
	ans := convert(700000)
	fmt.Println(ans)
}