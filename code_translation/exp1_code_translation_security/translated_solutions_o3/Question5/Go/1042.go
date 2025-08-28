package main

import (
	"fmt"
)

func intToString(num int) string {
	// Special-case zero so we don’t fall through the main loop
	if num == 0 {
		return "0"
	}

	negative := false
	if num < 0 {
		negative = true
		num = -num          // work with a positive value
	}

	// We’ll build the representation backwards
	var buf []byte
	count := 0
	for n := num; n != 0; n /= 10 {
		digit := byte(n%10) + '0'
		buf = append(buf, digit)
		count++
		if count == 3 && n/10 != 0 { // add comma every 3 digits, but not at the end
			buf = append(buf, ',')
			count = 0
		}
	}

	if negative {
		buf = append(buf, '-') // add the minus sign if needed
	}

	// Reverse the buffer in-place
	for i, j := 0, len(buf)-1; i < j; i, j = i+1, j-1 {
		buf[i], buf[j] = buf[j], buf[i]
	}

	return string(buf)
}

func main() {
	fmt.Println(intToString(7_000_000))   // 7,000,000
	fmt.Println(intToString(-123456789))  // -123,456,789
	fmt.Println(intToString(0))           // 0
}