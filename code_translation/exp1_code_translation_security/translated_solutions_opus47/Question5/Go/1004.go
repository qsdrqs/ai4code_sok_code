package main

import (
	"fmt"
	"math"
)

const MaxDigits = 100

/*
Function repr

Takes in a signed integer and returns the string representation of that integer.
*/
func repr(n int) string {
	if n == math.MinInt32 {
		// When multiplied by -1 this overflows, so handle it separately.
		return "-2,147,483,648"
	}
	if n == 0 {
		return "0"
	}

	str := make([]byte, 0, MaxDigits)
	sign := 1
	if n < 0 {
		sign = -1
		n *= -1
	}

	for n > 0 {
		str = append(str, byte(n%10)+'0')
		if (len(str)+1)%4 == 0 && n > 10 {
			str = append(str, ',')
		}
		n /= 10
	}
	if sign == -1 {
		str = append(str, '-')
	}

	reverse(str)
	return string(str)
}

/*
Function reverse

Takes in a byte slice and reverses it in place.
*/
func reverse(str []byte) {
	i := 0
	j := len(str) - 1
	for i < j {
		str[i], str[j] = str[j], str[i]
		i++
		j--
	}
}

func main() {
	n := 0
	str := repr(n)
	fmt.Print(str)
}