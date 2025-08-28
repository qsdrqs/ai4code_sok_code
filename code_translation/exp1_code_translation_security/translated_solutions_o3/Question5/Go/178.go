package main

import (
	"bytes"
	"fmt"
)

func convertToString(num int) string {
	// Produce the basic decimal representation
	raw := fmt.Sprintf("%d", num)

	// Number of commas that will be needed (matches the C debug print-out)
	numOfCommas := (len(raw) - 1) / 3
	fmt.Printf("%d\n", numOfCommas)

	if numOfCommas == 0 {
		return raw
	}

	// Build the final string with commas
	var out bytes.Buffer

	// First “partial” group (could be 1–3 digits)
	firstGroup := len(raw) % 3
	if firstGroup == 0 {
		firstGroup = 3
	}
	out.WriteString(raw[:firstGroup])

	// Remaining full groups of three digits
	for i := firstGroup; i < len(raw); i += 3 {
		out.WriteByte(',')
		out.WriteString(raw[i : i+3])
	}

	return out.String()
}

func main() {
	fmt.Printf("%s\n", convertToString(2000))
}