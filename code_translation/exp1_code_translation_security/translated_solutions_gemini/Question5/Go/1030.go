package main

import (
	"fmt"
	"golang.org/x/text/language"
	"golang.org/x/text/message"
)

func main() {
	num := 7000000

	// In C, setlocale(LC_NUMERIC, "") sets the formatting rules based on the
	// user's environment. In Go, we are more explicit. We create a "printer"
	// for a specific language.
	//
	// Here, we create a printer for US English, which uses a comma (,) as
	// the thousands separator.
	p := message.NewPrinter(language.AmericanEnglish)

	// We use the printer's Sprintf method, which works like fmt.Sprintf
	// but applies the locale-specific formatting rules. The standard '%d' verb
	// is automatically formatted with separators.
	snum := p.Sprintf("%d", num)

	// Print the resulting string.
	// Expected output for AmericanEnglish: 7,000,000
	fmt.Println(snum)

	// --- Bonus Example: Demonstrating a different locale ---
	// To show the power of this approach, let's create a printer for German.
	// German uses a period (.) as the thousands separator.
	pDe := message.NewPrinter(language.German)
	snumDe := pDe.Sprintf("%d", num)

	// Expected output for German: 7.000.000
	fmt.Printf("German format: %s\n", snumDe)
}