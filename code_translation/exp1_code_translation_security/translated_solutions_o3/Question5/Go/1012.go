// Package main is a direct, line-for-line translation of the
// C snippet you supplied.  It keeps the same function names and
// (roughly) the same algorithm, but uses Go idioms.
package main

import (
	"fmt"
	"os"
)

// insert adds a comma into str at the supplied zero-based index
// and returns the resulting string.
func insert(str string, index int) string {
	if index <= 0 || index >= len(str) {
		return str
	}
	return str[:index] + "," + str[index:]
}

// stringRepre returns the decimal representation of num with
// commas every three digits (counting from the right).
func stringRepre(num int) string {
	// Handle zero explicitly.
	if num == 0 {
		return "0"
	}

	// Deal with negative numbers so the comma logic can assume
	// a non-negative value.
	neg := false
	if num < 0 {
		neg = true
		num = -num
	}

	// 1. Convert the absolute value to a plain string.
	strNum := fmt.Sprintf("%d", num)

	// 2. Working from the right, insert a comma every three digits.
	for i := len(strNum) - 3; i > 0; i -= 3 {
		strNum = insert(strNum, i)
	}

	// 3. Re-attach the minus sign if we stripped it earlier.
	if neg {
		strNum = "-" + strNum
	}
	return strNum
}

func main() {
	// In C the first parameter to main, argc, is the argument count.
	// In Go the slice os.Args holds the arguments, and its length
	// is the direct equivalent.
	argc := len(os.Args)

	// Produce the formatted representation of argc and print it.
	result := stringRepre(argc)
	fmt.Println(result)
}