package main

import (
	"fmt"
)

const MAX_STR_LEN = 128

func reverseStr(str string) string {
	length := len(str)
	runes := []rune(str)
	newRunes := make([]rune, length)
	
	for i := 0; i < length; i++ {
		newRunes[i] = runes[length-i-1]
	}
	
	return string(newRunes)
}

/*
Return a string representation of a signed integer

Args:
    int num: input number as a signed integer
    
Return:
    string representation of the signed integer
*/
func strOfInt(num int) string {
	str := make([]byte, 0, MAX_STR_LEN)
	isNeg := false
	
	if num < 0 {
		isNeg = true
		num = -num
	}
	
	for {
		str = append(str, byte(num%10)+'0')
		num /= 10
		if num == 0 {
			break
		}
	}
	
	if isNeg {
		str = append(str, '-')
	}
	
	return reverseStr(string(str))
}

func main() {
	fmt.Print(strOfInt(7000000))
	fmt.Print("\n")
}