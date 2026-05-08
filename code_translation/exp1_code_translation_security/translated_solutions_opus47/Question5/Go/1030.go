package main

import (
	"fmt"

	"golang.org/x/text/language"
	"golang.org/x/text/message"
)

// TODO: Verify

func main() {
	num := 7000000 //-703840

	// Go doesn't use setlocale; instead we use the x/text/message package
	// to get locale-aware formatting (equivalent to the "%'d" format specifier in C).
	p := message.NewPrinter(language.English)
	snum := p.Sprintf("%d", num)

	fmt.Print(snum)
}