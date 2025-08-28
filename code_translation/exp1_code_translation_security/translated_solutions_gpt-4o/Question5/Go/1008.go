package main

import (
	"fmt"
	"golang.org/x/text/language"
	"golang.org/x/text/message"
)

func printNumber(num int) {
	p := message.NewPrinter(language.English)
	p.Printf("%d\n", num)
}

func main() {
	printNumber(7000000)
}