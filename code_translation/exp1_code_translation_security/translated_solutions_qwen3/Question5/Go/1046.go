package main

import (
	"fmt"
	"strconv"
)

func convert(num int) byte {
	s := strconv.Itoa(num)
	fmt.Print(s)
	return s[0]
}

func main() {
	fmt.Printf("%c", convert(100))
}