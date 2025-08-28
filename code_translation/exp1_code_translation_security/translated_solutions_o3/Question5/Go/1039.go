package main

import (
	"fmt"
)

// getLength returns the number of decimal digits in num.
func getLength(num int) int {
	length := 0
	numCopy := num
	for numCopy > 0 {
		numCopy /= 10
		length++
	}
	return length
}

// convertToString appends the constant string "test" to an (initially)
// empty string and returns it.
// The input argument is kept only so the function matches the original
// signature; it is not otherwise used.
func convertToString(num int) string {
	_ = num               // suppress “unused variable” error
	str := ""             // equivalent to char str[100]; str[0] = '\0';
	str += "test"         // strcat(str, "test");
	return str
}

func main() {
	num := 7000000
	fmt.Printf("%d", getLength(num))
	fmt.Print(convertToString(5))
}