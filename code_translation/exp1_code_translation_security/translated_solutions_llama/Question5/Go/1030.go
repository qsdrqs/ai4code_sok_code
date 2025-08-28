package main

import (
	"fmt"
)

func main() {
	num := 7000000
	fmt.Printf("%v\n", fmtWithCommas(num)) // prints 7,000,000
}

func fmtWithCommas(n int) string {
	return fmt.Sprintf("%,d", n)
}