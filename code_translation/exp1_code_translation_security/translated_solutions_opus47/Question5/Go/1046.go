package main

import (
	"fmt"
)

func convert(num int) string {
	var buf string
	buf = ""
	buf = fmt.Sprintf("%d", num)
	fmt.Printf("%s", buf)
	return buf
}

func main() {
	fmt.Printf("%s", convert(100))
}