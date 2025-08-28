package main

import (
	"fmt"

	"golang.org/x/text/language"
	"golang.org/x/text/message"
)

// printNumber prints num using the current (here: English) locale,
// automatically inserting the proper thousands separator.
func printNumber(num int) {
	p := message.NewPrinter(language.English) // choose/​match whatever locale you need
	p.Printf("%d\n", num)
}

func main() {
	printNumber(7_000_000)
}