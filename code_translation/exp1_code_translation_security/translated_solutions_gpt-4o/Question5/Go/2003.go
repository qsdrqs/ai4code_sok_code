package main

import (
	"fmt"
	"strconv"
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
		strLen++
	}

	str := make([]byte, strLen)
	curNum = num
	index := strLen - 1
	digits := 0
	for curNum > 0 {
		curChar := byte(curNum%10) + '0'
		str[index] = curChar
		curNum = curNum / 10
		index--
		digits++
		if digits%3 == 0 && index >= 0 {
			str[index] = ','
			index--
		}
	}

	if isNeg {
		str[0] = '-'
	}

	return string(str)
}

func main() {
	fmt.Println(numToString(-5305000))
}