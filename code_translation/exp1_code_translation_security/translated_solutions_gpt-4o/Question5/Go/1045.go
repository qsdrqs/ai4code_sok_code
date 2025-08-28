package main

import (
	"fmt"
	"math"
	"strings"
)

func numberToString(num int) string {
	var strNum strings.Builder
	numSize := 0
	commaCount := 0
	temp := num

	if num < 0 {
		temp *= -1
	}

	for temp > 0 {
		temp /= 10
		numSize++
	}

	commaCount = (numSize - 1) / 3
	strNum.Grow(numSize + commaCount + 1)

	if num < 0 {
		strNum.WriteByte('-')
		num *= -1
	}

	for j := numSize - 1; j >= 0; j-- {
		pow := int(math.Pow(10, float64(j)))
		strNum.WriteByte(byte((num/pow)%10) + '0')
		num %= pow

		if j%3 == 0 && j != 0 {
			strNum.WriteByte(',')
		}
	}

	return strNum.String()
}

func main() {
	num := -7000000
	strNum := numberToString(num)
	fmt.Println(strNum)
}