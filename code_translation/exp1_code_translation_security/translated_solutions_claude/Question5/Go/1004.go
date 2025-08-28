package main

import (
	"fmt"
)

const MAX_DIGITS = 100

/*
Function repr

Takes in a signed integer and returns the string representation of that integer.
*/
func repr(n int) string {
	str := make([]byte, MAX_DIGITS)
	i := 0
	sign := 1
	
	if n == -2147483648 {
		// When multiplied by -1 this overflows, so handle it separately.
		return "-2,147,483,648"
	}
	
	if n == 0 {
		str[0] = '0'
		return string(str[:1])
	}
	
	if n < 0 {
		sign = -1
		n *= -1
	}
	
	for n > 0 {
		str[i] = byte((n % 10) + '0')
		i++
		if (i+1)%4 == 0 && n > 10 {
			str[i] = ','
			i++
		}
		n /= 10
	}
	
	if sign == -1 {
		str[i] = '-'
		i++
	}
	
	result := string(str[:i])
	return reverse(result)
}

/*
Function reverse

Takes in a string and reverses it.
*/
func reverse(str string) string {
	runes := []rune(str)
	i := 0
	j := len(runes) - 1
	
	for i < j {
		temp := runes[i]
		runes[i] = runes[j]
		runes[j] = temp
		i++
		j--
	}
	
	return string(runes)
}

func main() {
	n := 0
	str := repr(n)
	fmt.Print(str)
}