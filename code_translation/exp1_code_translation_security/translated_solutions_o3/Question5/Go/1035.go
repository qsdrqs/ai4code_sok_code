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

// signum reproduces the C version (-1, 0, 1)
func signum(x int) int {
	if x < 0 {
		return -1
	}
	if x == 0 {
		return 0
	}
	return 1
}

// abs for ints (to stay close to the C code – math.Abs is float64)
func abs(x int) int {
	return x * signum(x)
}

// intToStr mimics the original int_to_str function, including
// the thousands-separator commas and the “static buffer” layout.
func intToStr(x int) string {
	const baseIdx = 16                // corresponds to 16 in C code
	out := make([]byte, baseIdx+1)    // room for indices 0 … 16
	expOffset := 0
	i := 0

	for i < 16 {
		digit := i - expOffset
		val := abs((x % e10(digit+1)) / e10(digit))
		out[baseIdx-i] = byte('0' + val)

		// we reached the most-significant digit
		if x%e10(digit+1) == x {
			start := baseIdx - i
			if x < 0 { // add leading minus
				start--
				out[start] = '-'
				i++ // mirror C: consume one more position
			}
			return string(out[start : baseIdx+1])
		}

		// place commas after 3, 7, 11 digits (i == 2, 6, 10)
		if i == 2 || i == 6 || i == 10 {
			out[baseIdx-i-1] = ','
			i++          // C code did “i += 1”
			expOffset++  // adjust digit offset
			continue     // go to next loop iteration
		}

		i++
	}

	return string(out) // fallback (shouldn’t be reached for given inputs)
}

func main() {
	fmt.Println(intToStr(55))
	fmt.Println(intToStr(12345))
	fmt.Println(intToStr(-55))
	fmt.Println(intToStr(-123456789))
}