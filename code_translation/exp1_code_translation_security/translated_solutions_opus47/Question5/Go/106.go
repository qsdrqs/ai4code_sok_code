package main

import "fmt"

const MaxStrLen = 128

func reverseStr(str string) string {
	length := len(str)
	newStr := make([]byte, length)
	for i := 0; i < length; i++ {
		newStr[i] = str[length-i-1]
	}
	return string(newStr)
}

/*
Return a string representation of a signed integer

Args:
    num int: input number as a signed integer

Return:
    string representation of the signed integer
*/
func strOfInt(num int) string {
	str := make([]byte, MaxStrLen)
	i := 0
	isNeg := false
	if num < 0 {
		isNeg = true
		num = -num
	}
	// Simulates a do-while loop (runs at least once)
	for {
		str[i] = byte(num%10 + '0')
		i++
		num /= 10
		if num == 0 {
			break
		}
	}
	if isNeg {
		str[i] = '-'
		i++
	}
	return reverseStr(string(str[:i]))
}

func main() {
	fmt.Println(strOfInt(7000000))
}