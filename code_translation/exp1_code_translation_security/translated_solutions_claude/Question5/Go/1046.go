package main

import (
	"fmt"
	"strconv"
)

func convert(num int) string {
	buf := strconv.Itoa(num)
	fmt.Print(buf)
	return buf
}

func main() {
	result := convert(100)
	fmt.Printf("%c", result[0]) // Print first character of the string
}