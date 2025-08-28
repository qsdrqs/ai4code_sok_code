package main

import (
	"fmt"
)

/*
The original C code comment:
Write a function in C that takes a signed integer num and returns a string representation of that integer. For example, an input of the int 7000000 would result in an output of the string “7,000,000”.
*/

// reverse reverses a slice of bytes in place.
// This is a direct translation of the C `reverse` function.
func reverse(s []byte) {
	for i, j := 0, len(s)-1; i < j; i, j = i+1, j-1 {
		s[i], s[j] = s[j], s[i]
	}
}

// itoa converts an integer n to its character representation in the byte slice s.
// It mimics the C version by generating digits in reverse order.
// It returns the number of bytes written to s, which is how Go handles
// dynamic string length instead of C's null terminator.
func itoa(n int, s []byte) int {
	var sign int
	if sign = n; n < 0 { // record sign
		n = -n // make n positive
	}

	i := 0
	// This loop mimics the C do-while loop, which runs at least once.
	// This correctly handles the case where n = 0.
	for {
		s[i] = byte(n%10 + '0') // get next digit
		i++
		n /= 10 // delete it
		if n == 0 {
			break
		}
	}

	if sign < 0 {
		s[i] = '-'
		i++
	}
	return i
}

// insertComma inserts a comma into a byte slice at a given position.
// In Go, it's idiomatic to return a new slice, as the operation might
// require reallocating the underlying array. This is the Go equivalent
// of the C `insert_comma` function that modifies a char pointer in place.
func insertComma(str []byte, pos int) []byte {
	// Grow slice by one to make space for the new character.
	str = append(str, 0)
	// Shift elements to the right, starting from the end.
	copy(str[pos+1:], str[pos:])
	// Insert the comma at the desired position.
	str[pos] = ','
	return str
}

// IntToString converts a signed integer to a string with comma separators,
// following the logic of the original C implementation.
func IntToString(num int) string {
	// In C, a fixed-size buffer is used. In Go, we can create a slice
	// with a reasonable capacity. A 64-bit int needs at most ~26 chars
	// with sign and commas, so 100 is plenty.
	s := make([]byte, 100)

	// 1. Convert int to a reversed string of digits, like in the C code.
	// The result is in s, and `length` is the number of bytes written.
	length := itoa(num, s)
	s = s[:length] // Trim the slice to its actual content.

	// 2. Insert commas into the reversed string.
	// This loop replicates the logic from the C code. It iterates based on the
	// original length and inserts commas at intervals of 4 (3 digits + 1 comma).
	originalLength := len(s)
	for i := 3; i < originalLength; i += 4 {
		// The C code's `insert_comma` would be called with an index `i` into
		// a string that is growing. We replicate this by inserting into our
		// slice `s` at the same index `i`.
		if i < len(s) {
			s = insertComma(s, i)
		}
	}

	// 3. The C code checks for and removes a trailing comma. This can happen if
	// the number of digits is a multiple of 3 (e.g., 123 -> "321,").
	if len(s) > 0 && s[len(s)-1] == ',' {
		s = s[:len(s)-1]
	}

	// 4. Reverse the final string to get the correct order.
	reverse(s)

	return string(s)
}

// main function to demonstrate the usage, similar to the C main.
func main() {
	// Test case from the original C main function
	num1 := 7000
	str1 := IntToString(num1)
	fmt.Printf("C code's example: intToString(%d) -> \"%s\"\n", num1, str1)

	// Test case from the original C code's comment
	num2 := 7000000
	str2 := IntToString(num2)
	fmt.Printf("Comment's example: intToString(%d) -> \"%s\"\n", num2, str2)

	// Additional test cases to show behavior
	num3 := -123456
	str3 := IntToString(num3)
	fmt.Printf("Negative number:   intToString(%d) -> \"%s\"\n", num3, str3)

	num4 := 123456789
	str4 := IntToString(num4)
	fmt.Printf("Long number:       intToString(%d) -> \"%s\"\n", num4, str4)

	num5 := 0
	str5 := IntToString(num5)
	fmt.Printf("Zero:              intToString(%d) -> \"%s\"\n", num5, str5)
}