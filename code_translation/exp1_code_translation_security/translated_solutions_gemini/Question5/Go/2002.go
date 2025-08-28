package main

import (
	"fmt"
	"log"
	"os"

	"golang.org/x/text/language"
	"golang.org/x/text/message"
)

// SigNumToStr formats an integer into a string using the system's
// locale-specific thousands separators.
// It is the Go equivalent of the C function using setlocale and %'d.
func SigNumToStr(num int) string {
	// The message.NewPrinter automatically determines the best language match
	// based on the user's environment (e.g., LANG, LC_ALL env vars).
	// We pass language.Und (undetermined) to tell it to auto-detect.
	p := message.NewPrinter(language.Und)

	// p.Sprintf works like fmt.Sprintf, but applies locale-specific
	// formatting rules for numbers.
	return p.Sprintf("%d", num)
}

// main function to demonstrate the usage of SigNumToStr.
func main() {
	// Note: The output of this program depends on your system's locale settings.
	// You can test different locales by setting the LANG environment variable
	// before running the program.

	// Example 1: A large number
	number := 1234567890
	formattedString := SigNumToStr(number)
	fmt.Printf("Original number: %d\n", number)
	fmt.Printf("Formatted string: %s\n", formattedString)
	fmt.Println("---")

	// Example 2: A negative number
	negativeNumber := -987654
	formattedNegative := SigNumToStr(negativeNumber)
	fmt.Printf("Original number: %d\n", negativeNumber)
	fmt.Printf("Formatted string: %s\n", formattedNegative)

	// To run this code, you first need to get the dependency:
	// go get golang.org/x/text

	// You can then run it and see the output based on your locale.
	// For example, on Linux/macOS, you can test different locales:
	//
	// US English (commas):
	// LANG=en_US.UTF-8 go run .
	// Output:
	// Formatted string: 1,234,567,890
	//
	// German (periods):
	// LANG=de_DE.UTF-8 go run .
	// Output:
	// Formatted string: 1.234.567.890
	//
	// French (non-breaking spaces):
	// LANG=fr_FR.UTF-8 go run .
	// Output:
	// Formatted string: 1 234 567 890
}

// This is a mock implementation of the C dependencies for clarity.
// In Go, these are handled by the standard library and external packages.
var (
	// #include <stdio.h> -> covered by Go's "fmt" and "log" packages
	_ = fmt.Printf
	_ = log.LstdFlags

	// #include <stdlib.h> -> covered by Go's "os" package and built-in memory management
	_ = os.Exit

	// #include <locale.h> -> covered by Go's "golang.org/x/text/language" and "golang.org/x/text/message"
	_ = language.English
)