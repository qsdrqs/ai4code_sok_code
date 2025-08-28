package main

import (
	"fmt"

	"github.com/dustin/go-humanize"
)

func main() {
	num := 7_000_000 // -703_840

	// Format the number with the locale’s thousands separator.
	// humanize.Comma returns a string like "7,000,000" or "-703,840".
	snum := humanize.Comma(int64(num))

	// Print the formatted string.
	fmt.Print(snum)
}