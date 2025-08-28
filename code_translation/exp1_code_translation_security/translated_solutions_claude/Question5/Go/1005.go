package main

import "fmt"

const MAX_LEN = 14

func stringify(i int, buf []byte) int {
	tmp := []byte("00000000000000")
	j := i
	if i < 0 {
		j = -1 * i
	}
	k := MAX_LEN - 1
	c := 0
	
	for j > 0 && k >= 0 {
		if c != 0 && c%3 == 0 {
			tmp[k] = ','
			k--
			c = 0
		}
		// Insert comma
		// len_written := MAX_LEN - 1 - k  // This variable was unused in original C code
		// Convert digit to char
		digit := j % 10
		d_char := byte(digit + '0')
		// Add to string
		tmp[k] = d_char
		// Move to next digit
		j /= 10
		k--
		c++
	}
	
	if i < 0 {
		tmp[k] = '-'
		k--
	}
	
	for n := k; n < MAX_LEN; n++ {
		buf[n-k] = tmp[n+1]
	}
	
	return MAX_LEN - 1 - k
}

func main() {
	buf := make([]byte, 14)
	copy(buf, "00000000000000")
	input := -2147483647
	l := stringify(input, buf)
	fmt.Printf("%d :: %s\n", l, string(buf[:l]))
}