package main

import (
	"fmt"
	"strconv"
)

func convert(num int) string {
	// Step 1: Calculate the length of the number as a string
	lenStr := len(strconv.Itoa(num))

	// Step 2: Simulate buffer allocation of size 3 (as in C)
	buf := make([]byte, 3)

	// Step 3: Simulate writing the number into the buffer
	// This will truncate the result if the number has more than 2 digits
	n := copy(buf, strconv.Itoa(num))

	// Step 4: Return the string representation of the buffer
	// Note: This does not include a null terminator, as Go strings are not null-terminated
	return string(buf[:n])
}

func main() {
	ans := convert(700000)
	fmt.Println(ans)
}