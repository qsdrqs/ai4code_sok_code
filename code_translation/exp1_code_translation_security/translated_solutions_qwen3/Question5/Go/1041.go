package main

import "fmt"

// reverseStr reverses the input string and returns the new string.
func reverseStr(s string) string {
	runes := []rune(s)
	for i, j := 0, len(runes)-1; i < j; i, j = i+1, j-1 {
		runes[i], runes[j] = runes[j], runes[i]
	}
	return string(runes)
}

// addCommas converts an integer to a string with commas as thousand separators.
func addCommas(num int) string {
	if num == 0 {
		return "0"
	}
	var res []byte
	count := 0
	for num != 0 {
		res = append(res, byte(num%10)+'0')
		num = num / 10
		count++
		if count == 3 && num != 0 {
			res = append(res, ',')
			count = 0
		}
	}
	return reverseStr(string(res))
}

func main() {
	num := 1000000
	str := addCommas(num)
	fmt.Println(str)
}