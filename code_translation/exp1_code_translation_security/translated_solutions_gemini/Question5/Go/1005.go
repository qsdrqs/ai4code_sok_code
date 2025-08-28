package main

import (
	"fmt"
)

// maxLen is set to a size sufficient to hold a 32-bit signed integer
// formatted with commas (e.g., "-2,147,483,648", which is 14 characters).
const maxLen = 14

// stringify converts an integer to a string with comma separators for thousands.
//
// This Go version is a translation of the C code's algorithm but fixes
// several bugs present in the original:
// 1. Correctly handles the input `0`.
// 2. Fixes the comma insertion logic to correctly group by thousands.
// 3. Safely handles the most negative integer value without overflow.
// 4. Uses Go's idiomatic string and slice handling, avoiding buffer overflows.
func stringify(i int) string {
	// Handle the edge case of 0, which the original C code handles incorrectly.
	if i == 0 {
		return "0"
	}

	// Use a byte slice as a buffer. In Go, it's common to build a slice
	// and then convert it to a string.
	tmp := make([]byte, maxLen)
	k := maxLen - 1 // Index to write to, starting from the end.

	isNegative := i < 0
	// Use a large enough unsigned type to safely handle the absolute value
	// of the most negative integer (e.g., math.MinInt32).
	var j uint64
	if isNegative {
		j = uint64(-i)
	} else {
		j = uint64(i)
	}

	c := 0 // Digit counter for comma insertion.
	for j > 0 {
		// Insert a comma after every 3 digits have been placed.
		if c > 0 && c%3 == 0 {
			// This check prevents buffer overflow, though it's unlikely with
			// maxLen=14 for a 32-bit int.
			if k < 0 {
				break
			}
			tmp[k] = ','
			k--
		}

		if k < 0 {
			break
		}

		// Convert the last digit to a character and add it to the buffer.
		digit := j % 10
		tmp[k] = byte(digit) + '0'
		k--

		// Move to the next digit.
		j /= 10
		c++
	}

	// Add the negative sign if the original number was negative.
	if isNegative {
		if k < 0 {
			// This would indicate a buffer overflow.
			return "error: buffer overflow"
		}
		tmp[k] = '-'
		k--
	}

	// The resulting string is the portion of the slice we've filled,
	// which starts at index k+1.
	return string(tmp[k+1:])
}

func main() {
	// The C code uses a specific 32-bit int value.
	var input int32 = -2147483647

	// The Go function takes an int, so we cast it.
	// It returns the formatted string directly.
	result := stringify(int(input))

	// The length of the string is obtained with the built-in len() function.
	l := len(result)

	// Print in the same format as the C example.
	fmt.Printf("%d :: %s\n", l, result)
}