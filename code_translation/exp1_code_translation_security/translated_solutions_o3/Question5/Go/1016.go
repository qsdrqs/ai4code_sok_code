package main

import (
	"fmt"
)

/*Translate the C function ‘int_to_string’ to Go.*/
func intToString(num int) string {
	var (
		length  int
		counter int
		temp    = num
	)

	/* If the number is zero we can return immediately. */
	if temp == 0 {
		return "0"
	}

	/* ---- first pass : figure out how long the final string will be ---- */
	// Work with the absolute value while counting digits / commas.
	if temp < 0 {
		temp = -temp
	}
	for temp != 0 {
		temp /= 10
		length++
		counter++

		// “there’s 1 comma for first 4 digits, then 1 for every 3 after”
		if counter == 4 {
			length++   // space for the comma
			counter = 1
		}
	}

	// Extra room for the sign if the original number was negative.
	sign := 0
	if num < 0 {
		sign = 1
		length++
	}

	/* ---- second pass : actually build the string ---- */
	buf := make([]byte, length) // Go strings don’t need a trailing '\0'

	// Place the sign first (if any)
	start := 0
	if sign == 1 {
		buf[0] = '-'
		start = 1
		// Work with the absolute value from now on.
		num = -num
	}

	digitCount := 0
	for i := length - 1; i >= start; i-- {
		digitCount++
		if digitCount == 4 { // time for a comma
			buf[i] = ','
			digitCount = 0
			continue
		}
		// Extract the least-significant digit.
		d := num % 10
		buf[i] = byte(d) + '0'
		num /= 10
	}

	return string(buf)
}

func main() {
	num := -10099870
	str := intToString(num)
	fmt.Println(str) // shows the same result the C version produced
}