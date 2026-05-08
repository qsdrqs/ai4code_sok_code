package main

import (
	"fmt"
	"os"
	"strconv"
)

// insert is a helper function to insert a comma into the string at the given index
func insert(str string, index int) string {
	// use string slicing to copy the original string up to the index,
	// then add a comma, then append the rest
	return str[:index] + "," + str[index:]
}

// stringRepre converts an integer to its string representation with comma separators
func stringRepre(num int) string {
	strnum := strconv.Itoa(num)

	l := len(strnum) // length before the insertion
	conum := l / 3   // number of commas needed

	// Special case: if length is exactly divisible by 3, we need one less comma
	// (e.g., "123" should remain "123", not get a leading comma)
	if l%3 == 0 {
		conum--
	}

	buff := strnum
	for i := 1; i <= conum; i++ {
		// l - 3*i is the index to be inserted (from right to left)
		buff = insert(buff, l-3*i)
	}
	return buff
}

func main() {
	// argc in C corresponds to len(os.Args) in Go
	argc := len(os.Args)
	str := stringRepre(argc)
	fmt.Println(str)
}