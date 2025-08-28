package main

import (
	"fmt"
)

func change(input int) string {
	// Count the number of digits in the input
	count := 0
	tmp := input
	if tmp == 0 {
		count = 1
	} else {
		for tmp != 0 {
			tmp /= 10
			count++
		}
	}

	// Calculate the total length of the result string
	num := count + count/3
	// Create a byte slice to hold the formatted string
	b := make([]byte, num)

	// Initialize variables for digit and comma insertion
	mid := 0
	for i := num - 1; i >= 0; {
		if mid == 3 {
			// Insert a comma
			b[i] = ','
			mid = 0
			i--
		}
		// Insert the current digit
		b[i] = byte(input%10) + '0'
		input /= 10
		mid++
		i--
	}

	// Convert the byte slice to a string and return
	return string(b)
}

func main() {
	snum := change(700000000)
	fmt.Println(snum)
}