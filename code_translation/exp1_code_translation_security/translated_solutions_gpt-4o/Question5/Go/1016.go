package main

import (
	"fmt"
	"strconv"
)

func intToString(num int) string {
	// Determine if the number is negative
	isNegative := false
	if num < 0 {
		isNegative = true
		num = -num // Work with the absolute value
	}

	// Convert the number to a string without commas
	temp := num
	counter := 0
	len := 0

	// Calculate the length of the string with commas
	for temp != 0 {
		temp = temp / 10
		len++
		counter++
		// Add a comma for every 4th digit, then every 3 digits after
		if counter == 4 {
			len++
			counter = 1
		}
	}

	// If the number is negative, account for the '-' sign
	if isNegative {
		len++
	}

	// Create a byte slice to build the string
	str := make([]byte, len)
	count := 0
	stop := 0

	// Add the '-' sign if the number is negative
	if isNegative {
		str[0] = '-'
		stop = 1
	}

	// Fill the string from the end
	for i := len - 1; i >= stop; i-- {
		count++
		if count == 4 {
			str[i] = ','
			count = 0
			continue
		}
		str[i] = byte((num % 10) + '0')
		num = num / 10
	}

	return string(str)
}

func main() {
	num := -10099870
	str := intToString(num)
	fmt.Println(str)
}