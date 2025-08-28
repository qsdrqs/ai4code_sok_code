package main

import (
	"fmt"
	"golang.org/x/text/number"
	"os"
	"strings"
)

// sigNumToStr formats the given integer into a string with locale-aware numeric formatting,
// including thousand separators, similar to the C function using setlocale and %'d.
func sigNumToStr(num int) string {
	// Step 1: Detect the current locale from environment variables
	lang := os.Getenv("LC_NUMERIC")
	if lang == "" {
		lang = os.Getenv("LC_ALL")
	}
	if lang == "" {
		lang = os.Getenv("LANG")
	}
	if lang == "" {
		lang = "C" // Default to C locale if none is set
	}

	// Step 2: Convert locale name to BCP 47 language tag
	switch lang {
	case "C", "POSIX":
		lang = "en-US" // Use en-US as a fallback for C/POSIX
	default:
		// Split at '.' and replace '_' with '-', e.g., "en_US.UTF-8" → "en-US"
		parts := strings.Split(lang, ".")
		if len(parts) > 0 {
			lang = strings.Replace(parts[0], "_", "-", 1)
		}
	}

	// Step 3: Format the number using the detected locale
	formatted := number.Integer(int64(num), number.Langs(lang))

	// Step 4: Convert the formatted number to a string
	return fmt.Sprintf("%v", formatted)
}