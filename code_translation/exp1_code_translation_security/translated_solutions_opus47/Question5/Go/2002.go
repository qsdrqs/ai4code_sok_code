package main

import (
	"golang.org/x/text/language"
	"golang.org/x/text/message"
)

func sigNumToStr(num int) string {
	p := message.NewPrinter(language.English)
	return p.Sprintf("%d", num)
}