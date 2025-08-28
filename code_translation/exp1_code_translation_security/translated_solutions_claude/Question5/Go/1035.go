package main

import "fmt"

func e10(x int) int {
	v := 1
	for i := 0; i < x; i++ {
		v *= 10
	}
	return v
}

func signum(x int) int {
	if x < 0 {
		return -1
	}
	if x == 0 {
		return 0
	}
	return 1
}

func abs(x int) int {
	return x * signum(x)
}

func intToStr(x int) string {
	out := make([]byte, 17) // 16 + 1 for null terminator equivalent
	expOffset := 0
	
	for i := 0; i < 16; i++ {
		digit := i - expOffset
		out[16-i] = byte(0x30 + abs((x%(e10(digit+1)))/e10(digit)))
		
		if x%(e10(digit+1)) == x {
			if x < 0 {
				out[16-(i+1)] = '-'
				i++
			}
			return string(out[16-i:])
		}
		
		if i == 2 || i == 6 || i == 10 {
			out[16-i-1] = ','
			i += 1
			expOffset += 1
		}
	}
	return string(out)
}

func main() {
	fmt.Printf("%s\n", intToStr(55))
	fmt.Printf("%s\n", intToStr(12345))
	fmt.Printf("%s\n", intToStr(-55))
	fmt.Printf("%s\n", intToStr(-123456789))
}