package main

import "fmt"

const MaxLen = 14

func stringify(i int, buf []byte) int {
	var tmp [MaxLen]byte
	for idx := 0; idx < MaxLen; idx++ {
		tmp[idx] = '0'
	}

	var j int
	if i >= 0 {
		j = i
	} else {
		j = -i
	}
	k := MaxLen - 1
	c := 0

	for j > 0 && k >= 0 {
		if c != 0 && c%3 == 0 {
			tmp[k] = ','
			k--
			c = 0
		}
		// Convert digit to char
		digit := j % 10
		dChar := byte(digit + '0')
		// Add to string
		tmp[k] = dChar
		// Move to next digit
		j /= 10
		k--
		c++
	}

	if i < 0 {
		tmp[k] = '-'
		k--
	}

	for n := k; n < MaxLen-1; n++ {
		buf[n-k] = tmp[n+1]
	}

	return MaxLen - 1 - k
}

func main() {
	buf := make([]byte, MaxLen)
	for idx := 0; idx < MaxLen; idx++ {
		buf[idx] = '0'
	}

	input := -2147483647
	l := stringify(input, buf)
	fmt.Printf("%d :: %s\n", l, buf)
}