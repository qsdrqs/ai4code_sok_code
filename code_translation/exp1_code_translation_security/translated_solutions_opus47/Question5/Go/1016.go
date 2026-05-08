package main

import "fmt"

func intToString(num int) string {
	length := 0
	temp := num
	counter := 0

	for temp != 0 {
		temp = temp / 10
		length++
		counter++
		// there's 1 comma for first 4 digits, then 1 for every 3 after
		if counter == 4 {
			length++
			counter = 1
		}
	}

	stop := 0
	if num < 0 {
		length++ // +1 for the minus sign character
		stop = 1 // add in the minus sign at start of string
	}

	str := make([]byte, length)
	if num < 0 {
		str[0] = '-'
	}

	count := 0
	for i := length - 1; i >= stop; i-- {
		count++
		if count == 4 {
			str[i] = ','
			count = 0
			continue
		}
		str[i] = byte(num%10 + '0')
		num = num / 10
	}

	return string(str)
}

func main() {
	num := -10099870
	str := intToString(num)
	fmt.Println(str)
}