package main

import (
	"fmt"
	"math"
)

/*
Function repr

Takes in a signed 32-bit integer and returns the string representation of that
integer, including commas as thousands separators.
*/
func repr(n int32) string {
	// Handle the special case for the minimum integer value, as multiplying it
	// by -1 would cause an overflow.
	if n == math.MinInt32 {
		return "-2,147,483,648"
	}

	if n == 0 {
		return "0"
	}

	// In Go, it's more idiomatic to use a boolean flag for negative numbers.
	isNegative := false
	if n < 0 {
		isNegative = true
		n = -n // Make n positive for processing
	}

	// Go strings are immutable. We build the string in reverse using a byte slice,
	// which is mutable and efficient. This avoids repeated string allocations.
	var result []byte

	for n > 0 {
		// Append the character for the last digit.
		result = append(result, byte(n%10)+'0')

		// The original C code's comma logic `(i+1) % 4 == 0` is equivalent to
		// checking if we have just added the 3rd, 7th, 11th, etc., digit.
		// `len(result) % 4 == 3` achieves the same in the reversed context.
		// We also check if there are more digits to prevent a leading comma (e.g., ",123").
		if len(result)%4 == 3 && n/10 > 0 {
			result = append(result, ',')
		}

		n /= 10
	}

	if isNegative {
		result = append(result, '-')
	}

	// The byte slice was built in reverse, so we need to reverse it to get the correct order.
	// We first convert the slice to a string, then pass it to our reverse function.
	return reverse(string(result))
}

/*
Function reverse

Takes in a string and returns a new, reversed string.
Go strings are immutable, so unlike the C version which modifies the string in place,
this function creates and returns a new one.
*/
func reverse(s string) string {
	// To handle Unicode characters correctly, we convert the string to a slice of runes.
	runes := []rune(s)
	// The classic two-pointer swap algorithm.
	// Go's syntax allows for a more compact for loop and parallel assignment for the swap.
	for i, j := 0, len(runes)-1; i < j; i, j = i+1, j-1 {
		runes[i], runes[j] = runes[j], runes[i]
	}
	return string(runes)
}

func main() {
	// The original main function only tested n = 0.
	// Here are a few more test cases to demonstrate the function's behavior.
	testCases := []int32{
		0,
		12345,
		-123,
		123456789,
		-987654,
		math.MaxInt32,
		math.MinInt32,
	}

	for _, n := range testCases {
		str := repr(n)
		// Using fmt.Printf for clear, formatted output.
		// The original `printf(str)` is unsafe in C; fmt.Println or fmt.Printf are safe in Go.
		fmt.Printf("repr(%d) -> %s\n", n, str)
	}
}