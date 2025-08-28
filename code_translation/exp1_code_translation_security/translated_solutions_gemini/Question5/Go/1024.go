package main

import (
	"fmt"
	"strconv"
)

// IntToStr converts an integer to its string representation.
//
// Note on the original C code:
// The C function has a critical bug. It returns a pointer to a local variable (`str`)
// that is allocated on the stack. Once the function returns, that memory is no longer
// valid, and using the returned pointer leads to undefined behavior.
//
// Go's memory management and string type completely prevent this class of error.
// The returned string is safely managed by the Go runtime.
func IntToStr(n int) string {
	// The most idiomatic way to convert an int to a string in Go.
	return strconv.Itoa(n)
}

func main() {
	number := 12345
	str := IntToStr(number)

	fmt.Printf("The integer is: %d\n", number)
	fmt.Printf("The converted string is: \"%s\"\n", str)

	// You can verify the type
	fmt.Printf("Type of 'number' is %T\n", number)
	fmt.Printf("Type of 'str' is %T\n", str)
}