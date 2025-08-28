package main

import (
	"fmt"
)

func change(input int) string {
	// --- count the number of digits ---
	tmp, count := input, 0
	for {
		tmp /= 10
		count++
		if tmp == 0 {
			break
		}
	}

	// number of runes (digits + commas)
	num := count + count/3
	fmt.Println(num)

	// --- build the result string (right-to-left) ---
	result := make([]byte, num)
	mid := 0

	for i := num - 1; i >= 0; i-- {
		if mid == 3 {
			mid = 0
			result[i] = ','
			i-- // skip one position after writing the comma
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