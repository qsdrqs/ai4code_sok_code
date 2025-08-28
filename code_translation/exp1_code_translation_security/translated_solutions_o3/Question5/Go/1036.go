package main

import (
	"fmt"
)

/*
 * Equivalent of the C function
 *     int num_digits(int n)
 */
func numDigits(n int) int {
	if n == 0 { // NOTE: '==' instead of the accidental '=' in the C code
		return 1
	}
	cnt := 0
	for n != 0 {
		n = n / 10
		cnt++
	}
	return cnt
}

/*
 * Equivalent of the C function
 *     char *return_string(int num)
 *
 * In C the function returned a pointer to a local array (undefined behaviour).
 * In Go we simply build two byte-slices (str and ret) having the same rôle,
 * print every element of ret as it is filled, and finally return ret as a
 * string – which is perfectly safe, because Go copies the slice data when the
 * conversion to string is performed.
 */
func returnString(num int) string {
	digits := numDigits(num)

	// Variable-length arrays are not allowed in Go, so we create slices.
	str := make([]byte, digits)
	ret := make([]byte, digits)

	i := 0
	for i != (digits + 1 - 1) { // mirrors the original while-condition
		/*
		   add a comma at every third index
		*/
		if i%3 == 0 {
			ret[i] = ','
		} else {
			ret[i] = str[i]
		}
		fmt.Printf("%c", ret[i])
		i++
	}

	return string(ret)
}

func main() {
	num := 7000000
	str := returnString(num)
	fmt.Printf("%s", str)
}