package main

import (
	"fmt"
	"golang.org/x/text/language"
	"golang.org/x/text/message"
)

// TODO: Verify

func main() {
	num := 7000000 // -703840

	// Create a printer for the current locale (English US in this case)
	p := message.NewPrinter(language.English)
	
	// Format the number with thousand separators
	snum := p.Sprintf("%d", num)
	
	fmt.Print(snum)
}