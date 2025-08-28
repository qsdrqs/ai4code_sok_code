package main

import (
	"fmt"

	"golang.org/x/text/language"
	"golang.org/x/text/message"
)

// sigNumToStr is a Go translation of the original C function.
//
// It formats an integer using the current (host-defined) numeric locale so
// that every three digits are grouped with the locale’s thousands separator
// (just like “%’d” in C with setlocale()).
func sigNumToStr(num int) string {
	// In the C version the empty string ("") passed to setlocale tells
	// the runtime to keep whatever locale the process is already using.
	// message.NewPrinter(language.Und) does the same thing in Go: it
	// inherits the process’ locale settings.
	printer := message.NewPrinter(language.Und)

	// The printer automatically inserts the proper thousands separator
	// for the active locale, so a simple “%d” is enough.
	return printer.Sprintf("%d", num)
}

func main() {
	fmt.Println(sigNumToStr(1234567))   // e.g. "1,234,567" in an en_US locale
	fmt.Println(sigNumToStr(-98765432)) // e.g. "-98,765,432"
}