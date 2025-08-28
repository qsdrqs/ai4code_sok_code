package main

import "fmt"

/*
A function that reverse a string
*/
// strrev reverses a sequence of bytes.
// In Go, strings are immutable. The original C function `strrev` modifies a C-style
// string (a char pointer) in-place. The most direct Go equivalent that preserves
// this in-place modification behavior is a function that operates on a slice of
// bytes ([]byte), which is what this function does. It modifies the slice it
// receives as an argument.
func strrev(input []byte) {
	start := 0
	end := len(input) - 1
	for end > start {
		// Swap bytes using Go's tuple assignment
		input[start], input[end] = input[end], input[start]
		start++
		end--
	}
}

/*
A function that takes a signed integer num and returns a string representation of the integer
*/
// integerToString converts a signed integer to its string representation.
func integerToString(num int) string {
	// A byte slice is used to build the string, similar to char* in C.
	// It grows dynamically, so we don't need to pre-allocate a fixed-size buffer
	// like `malloc(sizeof(char) * 100)`.
	var result []byte
	isNegative := false

	if num < 0 {
		isNegative = true
		// Note: This line has an integer overflow issue for the minimum integer
		// value (e.g., math.MinInt), which is also present in the original C code.
		num = -num
	}

	// This for loop structure mimics the C do-while loop.
	// It will execute at least once, correctly handling the num = 0 case.
	for {
		digit := num % 10
		result = append(result, '0'+byte(digit))
		num /= 10
		if num == 0 {
			break
		}
	}

	if isNegative {
		result = append(result, '-')
	}

	// Reverse the slice of bytes to get the correct order,
	// just like the C code calls strrev.
	strrev(result)

	return string(result)
}

func main() {
	// In Go, memory is managed automatically by the garbage collector.
	// There is no need for `malloc` or `free`. Variables are allocated and
	// cleaned up by the runtime.

	numStr := integerToString(12345)
	fmt.Println(numStr)

	numStr = integerToString(-12345)
	fmt.Println(numStr)
}