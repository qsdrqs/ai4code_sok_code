package main

import (
	"fmt"
	"strconv"
)

func main() {
	fmt.Printf("   %s\n", stringIt(-123457))
}

// Return a string containing the expansion of the signed int
func stringIt(value int) string {
	// Even if 64 bit int... only about 18 characters.
	// Add room for sign and 6 commas.
	// Far less than 30 total.
	returned := make([]byte, 30)
	pos := 0

	if value < 0 {
		returned[pos] = '-'
		pos++
		value = -value
	}

	// Write the number into the buffer (equivalent to sprintf in C).
	valStr := strconv.Itoa(value)
	copy(returned[pos:], valStr)

	length := len(valStr)
	commaCount := (length - 1) / 3
	totalLen := pos + length
	end := pos + length

	var buffer [30]byte
	for commaCount > 0 {
		start := end - 3
		fmt.Printf("%s\n", string(returned[start:totalLen]))

		// Mimic C: save substring, insert comma, restore shifted substring.
		copy(buffer[:], returned[start:totalLen])
		returned[start] = ','
		copy(returned[start+1:], buffer[:totalLen-start])
		totalLen++

		commaCount--
		end = start
	}

	return string(returned[:totalLen])
}