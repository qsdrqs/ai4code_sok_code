package main

import (
	"fmt"
	"strconv"
)

func convert(num int) string {
	return strconv.Itoa(num)
}

func main() {
	fmt.Println(convert(100))
}