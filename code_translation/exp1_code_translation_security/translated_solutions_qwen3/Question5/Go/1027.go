package main

import (
	"golang.org/x/text/language"
	"golang.org/x/text/message"
)

func convert(i int) int {
	// Create a printer with the default locale (language.Und uses the system's default)
	p := message.NewPrinter(language.Und)
	p.Printf("%'d\n", i)
	return 0
}

func main() {
	a := 400
	convert(a)
}