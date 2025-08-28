package main

import (
	"fmt"
)

const MAX_LEN = 16

func numToString(num int) string {
	if num == 0 {
		return "0"
	}
	
	isNeg := false
	if num < 0 {
		isNeg = true
		num = -num
	}
	
	curNum := num
	totalDigits := 0
	for curNum > 0 {
		curNum = curNum / 10
		totalDigits++
	}
	numCommas := (totalDigits - 1) / 3
	strLen := totalDigits + numCommas
	if isNeg {
		strLen += 1
	}
	
	// Create a byte slice to build the string
	str := make([]byte, strLen)
	
	curNum = num
	index := 0
	digits := 0
	for curNum > 0 {
		curChar := byte((curNum % 10) + 48)
		str[strLen-index-1] = curChar
		curNum = curNum / 10
		index++
		digits++
		if digits%3 == 0 && curNum > 0 { // Added curNum > 0 check to avoid leading comma
			str[strLen-index-1] = ','
			index++
		}
	}
	
	if isNeg {
		str[0] = '-'
	}
	
	return string(str)
}

func main() {
	fmt.Print(numToString(-5305000))
}