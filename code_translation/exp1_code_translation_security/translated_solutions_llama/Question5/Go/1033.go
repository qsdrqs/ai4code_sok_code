package main

import (
	"fmt"
	"strconv"
	"strings"
)

// numToStr converts an integer to a string with commas as thousand separators.
func numToStr(num int, str *string) {
	numStr := strconv.Itoa(num)
	if numStr[0] == '-' {
		parts := strings.Split(numStr, "-")
		parts[1] = addCommas(parts[1])
		*str = "-" + parts[1]
	} else {
		*str = addCommas(numStr)
	}
}

// addCommas adds commas as thousand separators to a string of digits.
func addCommas(numStr string) string {
	runes := []rune(numStr)
	length := len(runes)
	if length <= 3 {
		return numStr
	}

	var result []rune
	for i := length - 1; i >= 0; i-- {
		result = append(result, runes[i])
		if (length-i)%3 == 0 && i != 0 {
			result = append(result, ',')
		}
	}

	// Reverse the result
	for i := 0; i < len(result)/2; i++ {
		result[i], result[len(result)-1-i] = result[len(result)-1-i], result[i]
	}

	return string(result)
}

func main() {
	num := 7042
	var str string
	numToStr(num, &str)

	fmt.Printf("Output: %s\n", str)
}