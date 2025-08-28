package main

import "fmt"

// e10 returns 10^x
func e10(x int) int {
	v := 1
	for i := 0; i < x; i++ {
		v *= 10
	}
	return v
}

// signum returns -1, 0, or 1 based on the sign of x
func signum(x int) int {
	if x < 0 {
		return -1
	}
	if x == 0 {
		return 0
	}
	return 1
}

// abs returns the absolute value of x
func abs(x int) int {
	return x * signum(x)
}

// intToStr converts an integer to a string with commas as thousand separators
func intToStr(x int) string {
	out := make([]byte, 16)
	expOffset := 0

	for i := 0; i < 16; i++ {
		digit := i - expOffset
		val := (x % e10(digit+1)) / e10(digit)
		charDigit := byte(0x30 + abs(val))
		pos := 15 - i
		out[pos] = charDigit

		if x%e10(digit+1) == x {
			if x < 0 {
				out[pos-1] = '-'
				i++
			}
			return string(out[pos-i : 16])
		}

		if i == 2 || i == 6 || i == 10 {
			commaPos := 15 - i - 1
			out[commaPos] = ','
			i++
			expOffset++
		}
	}

	return string(out)
}

func main() {
	fmt.Println(intToStr(55))         // Output: 55
	fmt.Println(intToStr(12345))      // Output: 12,345
	fmt.Println(intToStr(-55))        // Output: -55
	fmt.Println(intToStr(-123456789)) // Output: -123,456,789
}