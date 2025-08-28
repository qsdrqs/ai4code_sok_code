package main

import (
	"fmt"
	"unsafe"
)

func change(input int) string {
	count := 0
	tmp := input
	for {
		tmp /= 10
		count++
		if tmp == 0 {
			break
		}
	}

	num := count + count/3
	result := make([]byte, num+2)
	mid := 0
	fmt.Printf("%d\n", num)
	
	for i := num - 1; i >= 0; i-- {
		if mid == 3 {
			mid = 0
			result[i] = ','
			i--
		}
		result[i] = byte(input%10) + '0'
		input /= 10
		mid += 1
	}
	
	// Convert byte slice to string
	return string(result[:num])
}

func main() {
	snum := change(700000000)
	fmt.Printf("%s\n", snum)
}