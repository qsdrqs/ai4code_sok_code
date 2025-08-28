package main

import (
	"fmt"
	"strconv"
	"strings"
)

// convert formats an int64 into a decimal string with thousands separators.
func convert(value int64) string {
	// First turn the number into a decimal string.
	s := strconv.FormatInt(value, 10)

	n := len(s)
	var b strings.Builder
	b.Grow(n + n/3) // rough pre-allocation

	for i := 0; i < n; i++ {
		b.WriteByte(s[i])

		dist := n - i - 1
		if dist%3 == 0 && dist != 0 {
			b.WriteByte(',')
		}
	}
	return b.String()
}

func main() {
	test := convert(1000)
	fmt.Println(test)
}