package main

import (
	"fmt"
	"math/rand"
	"strconv"
	"strings"
	"time"
)

func main() {
	// In Go, we seed the random number generator, typically using the current time.
	// Using UnixNano() provides a more unique seed than the C version.
	rand.Seed(time.Now().UnixNano())

	// Generate a random 64-bit integer. rand.Int63() is used to avoid negative numbers.
	// This is equivalent to C's `long` on a 64-bit system.
	value := rand.Int63()

	fmt.Printf("Before:\t%d\n", value)

	// Convert the integer to a string. This is equivalent to C's `sprintf`.
	vstring := strconv.FormatInt(value, 10)
	n := len(vstring)

	// If the number has 3 or fewer digits, no commas are needed.
	if n <= 3 {
		fmt.Printf("With:\t%s\n", vstring)
		return
	}

	// Use a strings.Builder for efficient string construction, which is safer
	// and more performant than manipulating a fixed-size char array.
	var commas strings.Builder

	// Calculate the length of the first group of digits (which can be 1, 2, or 3).
	firstLen := n % 3
	if firstLen == 0 {
		firstLen = 3
	}

	// Write the first group of digits to the builder.
	commas.WriteString(vstring[:firstLen])

	// Loop through the rest of the string in chunks of 3, adding a comma before each chunk.
	for i := firstLen; i < n; i += 3 {
		commas.WriteRune(',')
		commas.WriteString(vstring[i : i+3])
	}

	// Print the final, formatted string.
	fmt.Printf("With:\t%s\n", commas.String())
}