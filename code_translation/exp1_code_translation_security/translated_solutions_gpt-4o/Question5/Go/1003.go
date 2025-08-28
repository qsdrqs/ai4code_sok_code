package main

import (
	"fmt"
	"strconv"
)

func int2str(num int) string {
	return strconv.Itoa(num)
}

func main() {
	// Example usage
	num := 42
	str := int2str(num)
	fmt.Println(str) // Output: "42"
}