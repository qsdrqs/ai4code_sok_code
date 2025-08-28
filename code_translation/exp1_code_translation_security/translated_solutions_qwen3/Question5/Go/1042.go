package main

import (
	"fmt"
)

// intToString converts an integer to a string with commas as thousand separators.
// This function replicates the behavior of the original C function, including the
// handling of zero (which returns an empty string).
func intToString(num int) string {
	// Allocate a fixed-size buffer of 100 bytes
	str := make([]byte, 100)
	i := 0
	temp := num
	count := 0

	// Build the string in reverse order, inserting commas every 3 digits
	for temp != 0 {
		digit := temp % 10
		str[i] = '0' + byte(digit)
		temp = temp / 10
		i++
		count++
		if count == 3 && temp != 0 {
			str[i] = ','
			i++
			count = 0
		}
	}

	// Reverse the string in place to get the correct order
	for left, right := 0, i-1; left < right; left, right = left+1, right-1 {
		str[left], str[right] = str[right], str[left]
	}

	// Return the constructed string up to the current index
	return string(str[:i])
}

// main demonstrates the usage of intToString
func main() {
	fmt.Println(intToString(7000000)) // Output: 7,000,000
}