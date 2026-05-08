package main

import (
	"golang.org/x/text/language"
	"golang.org/x/text/message"
)

func convert(i int) int {
	p := message.NewPrinter(language.English)
	p.Printf("%d\n", i)
	return 0
}

func main() {
	a := 400
	convert(a)
}