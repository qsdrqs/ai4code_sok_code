package main

import (
	"fmt"
	"strconv"
)

func change(input int) string {
	count := 0
	tmp := input

	// Calculate the number of digits in the input
	for tmp != 0 {
		tmp /= 10
		count++
	}

	// Calculate the size of the resulting string with commas
	num := count + count/3
	result := make([]byte, num)
	mid := 0

	// Fill the result slice with digits and commas
	for i := num - 1; i >= 0; i-- {
		if mid == 3 {
			mid = 0
			result[i] = ','
			i--
		}
		result[i] = byte(input%10) + '0'
		input /= 10
		mid++
	}

	return string(result)
}

func main() {
	snum := change(700000000)
	fmt.Println(snum)
}