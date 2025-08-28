package main

import (
	"fmt"
)

func intToString(num int) string {
	temp := num
	counter := 0
	length := 0

	// Handle zero case
	if num == 0 {
		return "0"
	}

	// Handle negative numbers
	if num < 0 {
		num = -num
		temp = num
	}

	// Calculate length needed
	for temp != 0 {
		temp = temp / 10
		length++
		counter++
		// there's 1 comma for first 4 digits, then 1 for every 3 after
		if counter == 4 {
			length++
			counter = 1
		}
	}

	// Create slice to build string
	str := make([]byte, length)

	stop := 0
	if num < 0 {
		length++ // +1 for the minus sign character
		stop = 1  // add in the minus sign at start of string
		str = make([]byte, length)
		str[0] = '-'
		num = -num // make positive for processing
	}

	// Handle negative case properly
	originalNum := num
	if len(str) > 0 && str[0] == '-' {
		// num is already positive from above
	}

	count := 0
	for i := length - 1; i >= stop; i-- {
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