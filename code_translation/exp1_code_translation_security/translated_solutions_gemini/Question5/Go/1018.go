package main

import (
	"fmt"
)

// change translates the C function 'change'.
// It attempts to format an integer with thousand separators by building a string in reverse.
//
// NOTE: This is a direct and faithful translation of the provided C code.
// The original C code has several bugs and quirks that are replicated here
// for the sake of accuracy in translation:
// 1. The calculation for the final string length (`num`) is incorrect for many
//    inputs, resulting in a buffer that is too large. This can lead to the final
//    string having leading garbage characters (often null bytes).
// 2. The logic can place a comma at the very beginning of the string for certain
//    input lengths (e.g., for 700,000,000).
func change(input int) string {
	// --- Part 1: Count the number of digits ---
	// The C code uses a do-while loop. A `for` loop with a break at the end
	// is the idiomatic Go equivalent. This correctly handles input = 0, resulting in count = 1.
	count := 0
	tmp := input
	for {
		tmp /= 10
		count++
		if tmp == 0 {
			break
		}
	}

	// --- Part 2: Calculate the size of the output string ---
	// This calculation is directly translated from the C code. As noted, it can be buggy.
	num := count + count/3

	// This is the equivalent of `printf("%d\n", num);` from the C function.
	fmt.Println(num)

	// In C, `malloc` allocates a block of memory. In Go, we create a byte slice.
	// Go's garbage collector handles memory management, so no `free` is needed.
	result := make([]byte, num)

	mid := 0

	// --- Part 3: Build the string backwards ---
	// This `for` loop replicates the C code's logic, including the tricky `i--`
	// inside the conditional block.
	for i := num - 1; i >= 0; i-- {
		if mid == 3 {
			mid = 0
			result[i] = ','
			// This `i--` is crucial to replicating the C code's behavior.
			// It moves the index back an extra spot when a comma is placed.
			i--
		}

		// This check is added to prevent a panic. In C, writing to result[i]
		// when i is -1 would be undefined behavior. In Go, it would cause a
		// runtime panic. We break the loop instead to prevent a crash.
		if i < 0 {
			break
		}

		// Place the digit in the result slice.
		result[i] = byte(input%10 + '0')
		input /= 10
		mid++
	}

	// Convert the byte slice to a string and return it.
	return string(result)
}

func main() {
	// Equivalent to: char* snum = change(700000000);
	snum := change(700000000)

	// Equivalent to: printf("%s\n", snum);
	fmt.Printf("%s\n", snum)
}