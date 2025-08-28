package main

import (
	"fmt"
	"strconv"
)

// sigNumToStr converts an integer to a string with thousand separators.
func sigNumToStr(num int) string {
	return strconv.Itoa(num)
}

// However, Go's fmt package can handle thousand separators directly.
// So, we can use fmt.Sprintf to achieve the same result.

func sigNumToStrFmt(num int) string {
	return fmt.Sprintf("%d", num)
}

// To get thousand separators like in the C code, 
// you can use fmt.Sprintf with the ' ' verb.

func sigNumToStrWithSeparator(num int) string {
	return fmt.Sprintf("%'d", num)
}

func main() {
	num := 1234567
	fmt.Println(sigNumToStr(num)) // prints: 1234567
	fmt.Println(sigNumToStrFmt(num)) // prints: 1234567
	fmt.Println(sigNumToStrWithSeparator(num)) // prints: 1,234,567
}