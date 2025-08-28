package main

import (
	"fmt"
	"strconv"
)

func intToString(num int) string {
	// Create a slice of bytes to store the result
	str := make([]byte, 0, 100)
	temp := num
	count := 0

	// Handle the case where the number is 0
	if temp == 0 {
		return "0"
	}

	// Process the number and build the string in reverse order
	for temp != 0 {
		// Append the current digit to the slice
		str = append(str, byte(temp%10)+'0')
		temp = temp / 10
		count++

		// Add a comma after every 3 digits (if there are more digits left)
		if count == 3 && temp != 0 {
			str = append(str, ',')
			count = 0
		}
	}

	// Reverse the slice to get the correct order
	for i, j := 0, len(str)-1; i < j; i, j = i+1, j-1 {
		str[i], str[j] = str[j], str[i]
	}

	// Convert the byte slice to a string and return it
	return string(str)
}

func main() {
	// Test the intToString function
	fmt.Println(intToString(7000000))
}