package main

import (
	"fmt"
	"strconv"
)

func numDigits(n int) int {
	if n == 0 {
		return 1
	}
	cnt := 0
	for n != 0 {
		n /= 10
		cnt++
	}
	return cnt
}

func returnString(num int) string {
	numStr := strconv.Itoa(num)
	var ret string
	for i, char := range numStr {
		if (numDigits(num) - i) % 3 == 0 && i != 0 {
			ret += ","
		}
		ret += string(char)
	}
	return ret
}

func main() {
	num := 7000000
	str := returnString(num)
	fmt.Println(str)
}