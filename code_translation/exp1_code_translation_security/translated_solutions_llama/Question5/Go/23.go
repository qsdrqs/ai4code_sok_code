package main

import (
	"fmt"
	"strconv"
	"strings"
)

func intToString(x int) string {
	tempString := strconv.Itoa(x)
	var result string

	// Add commas to the string
	for i, char := range tempString {
		if (len(tempString) - i) % 3 == 0 && i != 0 {
			result += ","
		}
		result += string(char)
	}

	return result
}

func main() {
	fmt.Println(intToString(100000))
	fmt.Println("test")
}