package main

import (
	"os"
	"strings"

	"golang.org/x/text/language"
	"golang.org/x/text/message"
)

func printNumber(num int) {
	// Determine the locale from environment variables
	locale := os.Getenv("LC_NUMERIC")
	if locale == "" {
		locale = os.Getenv("LC_ALL")
	}
	if locale == "" {
		locale = os.Getenv("LANG")
	}

	var tag language.Tag
	if locale != "" {
		// Split locale at the first dot (e.g., "en_US.UTF-8" -> "en_US")
		parts := strings.SplitN(locale, ".", 2)
		langPart := parts[0]
		// Convert to BCP 47 format (e.g., "en_US" -> "en-US")
		langTag := strings.ReplaceAll(langPart, "_", "-")
		tag = language.MustParse(langTag)
	} else {
		// Default to English if no locale is set
		tag = language.English
	}

	// Create a printer for the determined locale
	p := message.NewPrinter(tag)
	p.Printf("%'d\n", num)
}

func main() {
	printNumber(7000000)
}