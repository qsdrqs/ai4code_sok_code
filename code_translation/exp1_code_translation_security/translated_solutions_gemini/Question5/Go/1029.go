package main

import (
	"fmt"
	"strconv"
)

// convertInt translates the *intent* of the original C function.
// The C version of this function was critically flawed:
// 1. It returned a pointer to a local variable, which is undefined behavior.
// 2. Its loop condition was based on uninitialized memory.
// 3. It would have generated the digits in reverse order.
//
// The correct and idiomatic way to convert an integer to a string in Go
// is to use `strconv.Itoa`.
func convertInt(input int) string {
	return strconv.Itoa(input)
}

// convertIntAI translates the C function that uses `sprintf`.
// The direct equivalent in Go is `fmt.Sprintf`.
//
// In Go, strings are managed by the garbage collector, so there is no need
// for manual memory allocation (`malloc`) or deallocation (`free`).
func convertIntAI(input int) string {
	return fmt.Sprintf("%d", input)
}

func main() {
	// The C code calls convertIntAI, so we do the same here.
	output := convertIntAI(7000000)

	// The C code uses `printf`, the Go equivalent is `fmt.Printf`.
	fmt.Printf("%s", output)
}