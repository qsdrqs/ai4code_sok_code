package main

// All necessary dependencies are included in this import block.
import (
	"golang.org/x/text/language"
	"golang.org/x/text/message"
)

// printNumber prints an integer with locale-specific thousands separators.
// This function is the Go equivalent of the C version.
func printNumber(num int) {
	// message.NewPrinter(language.Und) creates a printer that automatically
	// detects and uses the system's locale, similar to C's setlocale(LC_NUMERIC, "").
	// "Und" stands for "undefined language".
	p := message.NewPrinter(language.Und)

	// The printer's Printf method correctly formats numbers according to the
	// detected locale, adding thousands separators. This is equivalent to C's "%'d".
	p.Printf("%d\n", num)
}

func main() {
	printNumber(7000000)
}