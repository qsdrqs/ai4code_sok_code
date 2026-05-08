package main

import (
	"fmt"
	"strconv"
)

func numToString(num int) string {
	return strconv.Itoa(num)
}

func main() {
	fmt.Print(numToString(7000000))
}