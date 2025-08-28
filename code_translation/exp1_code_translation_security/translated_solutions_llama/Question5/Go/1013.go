package main

import (
	"fmt"
	"strconv"
)

func convert(num int) string {
	return strconv.Itoa(num)
}

func formatWithCommas(num int) string {
	return strconv.FormatInt(int64(num), 10)
}

func main() {
	num := 700000
	ans := convert(num)
	fmt.Println("Without commas:", ans)

	ansWithCommas := formatWithCommas(num)
	fmt.Println("With commas (not directly supported):", ansWithCommas)

	// Alternatively, you can use a custom function to add commas
	ansWithCommasCustom := addCommas(num)
	fmt.Println("With commas (custom):", ansWithCommasCustom)
}

func addCommas(n int) string {
	s := strconv.Itoa(n)
	l := len(s)
	for i := l - 3; i > 0; i -= 3 {
		s = s[:i] + "," + s[i:]
	}
	return s
}