package main

import (
	"fmt"
	"strconv"
)

func intToString(num int) string {
	length := 0
	temp := num
	for temp != 0 {
		temp /= 10
		length++
	}
	
	str := make([]byte, length)
	for i := length - 1; i >= 0; i-- {
		str[i] = byte((num % 10) + '0')
		num /= 10
	}
	return string(str)
}

func intToStringWithCommas(num int) string {
	str := intToString(num)
	length := len(str)
	commaCount := (length - 1) / 3
	newStr := make([]byte, length+commaCount)
	
	j := 0
	k := 0
	for i := 0; i < length+commaCount; i++ {
		if (length-j)%3 == 0 && k != 1 && i != 0 {
			newStr[i] = ','
			k = 1
		} else {
			newStr[i] = str[j]
			j++
			if k == 1 {
				k = 0
			}
		}
	}
	
	return string(newStr)
}

func main() {
	num := 7654321
	str := intToStringWithCommas(num)
	fmt.Println(str)
}