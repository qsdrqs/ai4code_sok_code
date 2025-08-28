package main

import (
	"fmt"
	"strconv"
	"strings"
)

func convert(value int64) string {
	// Convert the integer to a string
	buf1 := strconv.FormatInt(value, 10)

	// Create a builder to efficiently build the result string
	var builder strings.Builder

	// Calculate the length of the string
	len := len(buf1)
	idx := 0

	// Iterate over the string and insert commas
	for i := 0; i < len; i++ {
		builder.WriteByte(buf1[i])
		dist := len - i - 1
		if dist%3 == 0 && dist != 0 {
			builder.WriteByte(',')
		}
		idx++
	}

	return builder.String()
}

func main() {
	test := convert(1000)
	fmt.Println(test)
}