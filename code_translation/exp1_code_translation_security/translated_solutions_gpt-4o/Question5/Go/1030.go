package main

import (
	"fmt"
	"golang.org/x/text/language"
	"golang.org/x/text/message"
)

func main() {
	num := 7000000

	// Create a new printer for the English language
	p := message.NewPrinter(language.English)

	// Print the number with thousands separators
	p.Printf("%d\n", num)
}