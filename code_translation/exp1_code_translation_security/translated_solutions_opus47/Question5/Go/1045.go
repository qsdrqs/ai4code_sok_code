package main

import "fmt"

func power(base, exponent int) int {
	result := 1
	for exponent > 0 {
		result *= base
		exponent--
	}
	return result
}

func numberToString(num int) string {
	numSize := 0
	i := 0
	temp := num
	if num < 0 {
		temp *= -1
	}
	for temp > 0 {
		temp /= 10
		numSize++
	}
	commaCount := (numSize - 1) / 3
	strNum := make([]byte, numSize+commaCount+1)
	if num < 0 {
		strNum[0] = '-'
		num *= -1
		i++
	}
	for j := numSize - 1; j >= 0; j-- {
		strNum[i] = byte(num/power(10, j)) + '0'
		num %= power(10, j)
		i++
		if (j%3 == 0) && (j != 0) {
			strNum[i] = ','
			i++
		}
	}
	return string(strNum[:i])
}

func main() {
	num := -7000000
	strNum := numberToString(num)
	fmt.Println(strNum)
}