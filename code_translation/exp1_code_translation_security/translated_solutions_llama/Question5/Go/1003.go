package main

import (
	"fmt"
	"strconv"
)

// int2str converts an integer to a string.
func int2str(num int) string {
	return strconv.Itoa(num)
}

func main() {
	num := 123
	str := int2str(num)
	fmt.Println(str) // prints: 123
}