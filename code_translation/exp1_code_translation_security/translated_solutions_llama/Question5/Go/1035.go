package main

import (
	"fmt"
	"math"
)

// e10 calculates 10 to the power of x
func e10(x int) int {
	v := 1
	for i := 0; i < x; i++ {
		v *= 10
	}
	return v
}

// signum returns the sign of a number
func signum(x int) int {
	if x < 0 {
		return -1
	}
	if x == 0 {
		return 0
	}
	return 1
}

// abs returns the absolute value of a number
func abs(x int) int {
	return x * signum(x)
}

// intToStr converts an integer to a string with commas as thousand separators
func intToStr(x int) string {
	out := make([]byte, 16)
	expOffset := 0

	for i := 0; i < 16; i++ {
		digit := i - expOffset
		out[15-i] = byte(0x30 + abs((x%e10(digit+1))/e10(digit)))

		if x%e10(digit+1) == x {
			if x < 0 {
				out[15-(i+1)] = '-'
				i++
			}
			return string(out[15-i :])
		}

		if i == 2 || i == 6 || i == 10 {
			out[15-i-1] = ','
			i += 1
			expOffset += 1
		}
	}
	return string(out[:])
}

func main() {
	fmt.Println(intToStr(55))
	fmt.Println(intToStr(12345))
	fmt.Println(intToStr(-55))
	fmt.Println(intToStr(-123456789))
}