package main

import (
	"fmt"
)

// intToString converts an integer to a string with commas inserted as thousand separators.
// It replicates the behavior of the original C function, including the comma-insertion logic.
func intToString(num int) string {
	// Handle the special case of zero
	if num == 0 {
		return "0"
	}

	// Determine if the number is negative
	isNegative := num < 0
	if isNegative {
		num = -num // Take the absolute value
	}

	// Step 1: Calculate the length of the resulting string, including commas
	temp := num
	length := 0
	counter := 0

	for temp != 0 {
		temp = temp / 10
		length++
		counter++
		if counter == 4 {
			length++       // Add space for a comma
			counter = 1    // Reset counter after every 4 digits
		}
	}

	// Step 2: Add space for the minus sign if the number is negative
	if isNegative {
		length++
	}

	// Step 3: Create the byte slice to hold the result
	str := make([]byte, length)
	if isNegative {
		str[0] = '-' // Set the minus sign at the beginning
	}

	// Step 4: Fill the string from the end to the beginning
	count := 0
	stop := 0
	if isNegative {
		stop = 1
	}

	temp = num // Reset temp to the absolute value of the original number

	for i := length - 1; i >= stop; i-- {
		count++
		if count == 4 {
			str[i] = ',' // Insert a comma
			count = 0
			continue
		}
		str[i] = '0' + byte(temp%10) // Convert digit to character
		temp = temp / 10
	}

	// Step 5: Return the resulting string
	return string(str)
}

// main function to test the intToString function
func main() {
	num := -10099870
	str := intToString(num)
	fmt.Println(str) // Expected output: -10,099,870
}