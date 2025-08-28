package main

import (
	"fmt"
	"strconv"
	"strings"
)

func addThousandSeparators(num int) string {
	s := strconv.Itoa(num)
	var result string
	count := 0
	for i := len(s) - 1; i >= 0; i-- {
		if count == 3 {
			result = "," + result
			count = 0
		}
		result = string(s[i]) + result
		count++
	}
	return result
}

func printNumber(num int) {
	fmt.Println(addThousandSeparators(num))
}

func main() {
	printNumber(7000000)
}