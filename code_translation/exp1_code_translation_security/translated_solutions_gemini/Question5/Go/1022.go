package main

import "fmt"

// printNum recursively prints an integer with comma separators for thousands.
// It is the Go equivalent of the C function 'print_num'.
func printNum(num int) {
	// Handle negative numbers by printing a '-' and then calling the function
	// with the positive version of the number.
	if num < 0 {
		fmt.Print("-")
		printNum(-num)
		return
	}

	// This is the base case for the recursion. If the number is less than 1000,
	// it doesn't need a comma, so we print it directly.
	if num < 1000 {
		fmt.Printf("%d", num)
		return
	}

	// Recursive step:
	// 1. Call printNum for the number divided by 1000 (e.g., for 12345, this calls printNum(12)).
	printNum(num / 1000)

	// 2. After the higher-order digits are printed, print a comma and the last three digits.
	// The "%03d" format specifier ensures that numbers like 45 are printed as "045".
	fmt.Printf(",%03d", num%1000)
}

func main() {
	printNum(-12345)
	fmt.Println() // Adding a newline for clean terminal output.
}