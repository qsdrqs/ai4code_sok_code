package main

import "fmt"

func reverseStr(str []byte) []byte {
	for i, j := 0, len(str)-1; i < j; i, j = i+1, j-1 {
		str[i], str[j] = str[j], str[i]
	}
	return str
}

func addCommas(num int) string {
	var str []byte
	count := 0
	for num != 0 {
		str = append(str, byte(num%10)+'0')
		num /= 10
		count++
		if count == 3 && num != 0 {
			str = append(str, ',')
			count = 0
		}
	}
	return string(reverseStr(str))
}

func main() {
	num := 1000000
	str := addCommas(num)
	fmt.Printf("%s\n", str)
}