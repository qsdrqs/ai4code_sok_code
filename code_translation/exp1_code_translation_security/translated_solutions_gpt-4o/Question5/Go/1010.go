package main

import (
	"fmt"
	"strconv"
	"strings"
)

func main() {
	fmt.Printf("   %s\n", stringIt(-123457))
}

// stringIt returns a string containing the expansion of the signed int
func stringIt(value int) string {
	var str string
	if value < 0 {
		str = "-"
		value = -value
	}
	str += strconv.Itoa(value)

	length := len(str)
	if str[0] == '-' {
		length-- // Adjust length if there's a negative sign
	}

	commaCount := (length - 1) / 3
	if commaCount > 0 {
		var sb strings.Builder
		if str[0] == '-' {
			sb.WriteByte('-')
			str = str[1:]
		}
		start := length % 3
		if start == 0 {
			start = 3
		}
		sb.WriteString(str[:start])
		for i := start; i < length; i += 3 {
			sb.WriteByte(',')
			sb.WriteString(str[i : i+3])
		}
		return sb.String()
	}

	return str
}