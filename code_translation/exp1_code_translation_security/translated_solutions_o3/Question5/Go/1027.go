package main

import (
	"golang.org/x/text/language"
	"golang.org/x/text/message"
)

// convert replicates the C version’s behaviour: it prints the value
// using the current (here: English) locale so that thousands are
// grouped with the correct separator.
func convert(i int) {
	p := message.NewPrinter(language.English) // use environment/default locale
	p.Printf("%d\n", i)
}

func main() {
	a := 400
	convert(a)
}