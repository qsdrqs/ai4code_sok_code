package main

import (
	"fmt"
	"strconv"
)

func main() {
	num := uint(1000000)
	str := addCommas(num)
	fmt.Println(str)
}

func reverseStr(str string) string {
	runes := []rune(str)
	for i, j := 0, len(runes)-1; i < j; i, j = i+1, j-1 {
		runes[i], runes[j] = runes[j], runes[i]
	}
	return string(runes)
}

func addCommas(num uint) string {
	if num == 0 {
		return "0"
	}
	
	var result []rune
	count := 0
	
	for num != 0 {
		digit := num % 10
		result = append(result, rune('0'+digit))
		num /= 10
		count++
		
		if count == 3 && num != 0 {
			result = append(result, ',')
			count = 0
		}
	}
	
	return reverseStr(string(result))
}