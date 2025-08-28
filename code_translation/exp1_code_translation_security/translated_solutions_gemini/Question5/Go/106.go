package main

import (
	"fmt"
)

// The C code defined MAX_STR_LEN, which is not needed in Go
// as slices can grow dynamically, preventing buffer overflows.

// reverseStr translates the C function `reverseStr`.
// It takes a string and returns a new string with the characters in reverse order.
// In C, a new block of memory was allocated with malloc. In Go, a new string
// is returned, and memory is managed by the garbage collector.
func reverseStr(str string) string {
	// Convert the string to a slice of runes to handle Unicode characters correctly.
	runes := []rune(str)
	// Use a two-pointer approach to swap characters from the ends towards the center.
	for i, j := 0, len(runes)-1; i < j; i, j = i+1, j-1 {
		runes[i], runes[j] = runes[j], runes[i]
	}
	// Convert the rune slice back to a string.
	return string(runes)
}

/*
Return a string representation of a signed integer

Args:
    int num: input number as a signed integer

Return:
    string representation of the signed integer
*/
func strOfInt(num int) string {
	// In C, memory was allocated with malloc. In Go, we use a dynamic byte slice.
	// The original C code had a memory leak, as the memory allocated for the
	// intermediate reversed string was never freed. Go's garbage collector
	// handles this automatically.
	var str []byte

	isNeg := false
	if num < 0 {
		isNeg = true
		num = -num
	}

	// This loop simulates the C `do-while` loop. It extracts digits
	// from the number, building a reversed string representation.
	// It correctly handles the case where num is 0.
	for {
		// Get the last digit and convert it to its character representation.
		str = append(str, byte(num%10)+'0')
		// Remove the last digit.
		num /= 10
		// Break the loop if all digits have been processed.
		if num == 0 {
			break
		}
	}

	if isNeg {
		str = append(str, '-')
	}

	// The C code returns the result of calling reverseStr on the intermediate
	// (and reversed) string. We follow the same pattern here.
	// First, convert the byte slice to a string.
	reversedIntermediateStr := string(str)
	// Then, call reverseStr to get the final, correctly ordered string.
	return reverseStr(reversedIntermediateStr)
}

func main() {
	// The C code calls printf with the result of strOfInt.
	// fmt.Print is the Go equivalent.
	fmt.Print(strOfInt(7000000))
	// The C code then prints a newline.
	fmt.Print("\n")
}