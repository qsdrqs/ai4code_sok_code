package main

import (
	"fmt"
	"strings"
)

// Function repr
// Takes in a signed integer and returns the string representation of that integer.
func repr(n int) string {
	if n == -2147483648 {
		// When multiplied by -1 this overflows, so handle it separately.
		return "-2,147,483,648"
	}
	if n == 0 {
		return "0"
	}

	sign := 1
	if n < 0 {
		sign = -1
		n *= -1
	}

	var strBuilder strings.Builder
	i := 0

	for n > 0 {
		strBuilder.WriteByte(byte(n%10) + '0')
		i++
		if (i+1)%4 == 0 && n > 10 {
			strBuilder.WriteByte(',')
		}
		n /= 10
	}

	if sign == -1 {
		strBuilder.WriteByte('-')
	}

	str := strBuilder.String()
	return reverse(str)
}

// Function reverse
// Takes in a string and reverses it.
func reverse(s string) string {
	runes := []rune(s)
	for i, j := 0, len(runes)-1; i < j; i, j = i+1, j-1 {
		runes[i], runes[j] = runes[j], runes[i]
	}
	return string(runes)
}

func main() {
	n := 0
	str := repr(n)
	fmt.Println(str)
}