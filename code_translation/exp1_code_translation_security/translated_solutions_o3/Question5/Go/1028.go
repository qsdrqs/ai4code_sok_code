package main

import (
	"fmt"
)

// reverse reverses the content of b in-place.
func reverse(b []byte) {
	for i, j := 0, len(b)-1; i < j; i, j = i+1, j-1 {
		b[i], b[j] = b[j], b[i]
	}
}

/*
	itoa replicates the tiny version used in the C code:
	– it produces the digits in *reverse* order
	– if the number is negative it appends '-' as the last byte
*/
func itoa(n int) []byte {
	if n == 0 {
		return []byte{'0'}
	}

	sign := 1
	if n < 0 {
		sign = -1
		n = -n
	}

	var out []byte
	for {
		out = append(out, byte(n%10)+'0') // next digit (reversed)
		n /= 10
		if n == 0 {
			break
		}
	}

	if sign < 0 {
		out = append(out, '-')
	}
	return out
}

// insertComma inserts a comma at position pos inside slice b and returns the new slice.
func insertComma(b []byte, pos int) []byte {
	// enlarge by one
	b = append(b, 0)               // dummy to grow the slice
	copy(b[pos+1:], b[pos:])       // shift right
	b[pos] = ','                   // place comma
	return b
}

// intToString converts num to a human-readable string with thousands separators.
func intToString(num int) string {
	// step 1: obtain reversed digit string
	b := itoa(num)

	// step 2: sprinkle commas every 3 positions (still reversed)
	length := len(b)
	for i := 3; i <= length; i += 4 { // identical loop to the C version
		b = insertComma(b, i)
	}

	// step 3: remove a trailing comma that may appear for negative numbers like "-1000"
	if len(b) > 0 && b[len(b)-1] == ',' {
		b = b[:len(b)-1]
	}

	// step 4: reverse everything to get the final order
	reverse(b)

	return string(b)
}

func main() {
	num := 7000
	result := intToString(num)
	fmt.Println(result) // prints: 7,000
}