package main

import (
	"fmt"
	"golang.org/x/text/language"
	"golang.org/x/text/message"
)

func main() {
	a := 400
	convert(a)
}

func convert(i int) {
	// Create a printer for formatting numbers with locale-specific grouping
	p := message.NewPrinter(language.English)
	p.Printf("%d\n", i)
}