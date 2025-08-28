package main

import (
	"fmt"
	"golang.org/x/text/language"
	"golang.org/x/text/message"
)

func sigNumToStr(num int) string {
	// Create a new printer for the desired locale
	p := message.NewPrinter(language.English)
	// Format the number with commas as thousand separators
	text := p.Sprintf("%d", num)
	return text
}

func main() {
	num := 1234567
	str := sigNumToStr(num)
	fmt.Println(str) // Output: 1,234,567
}