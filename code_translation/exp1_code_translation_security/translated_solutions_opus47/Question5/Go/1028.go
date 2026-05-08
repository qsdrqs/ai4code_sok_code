/* A function in Go that takes a signed integer num and returns a string representation of that integer. 
   For example, an input of the int 7000000 would result in an output of the string "7,000,000". */

package main

import "fmt"

func reverse(s []byte) {
	for i, j := 0, len(s)-1; i < j; i, j = i+1, j-1 {
		s[i], s[j] = s[j], s[i]
	}
}

// itoa converts n to characters (in reverse order, sign appended at end)
func itoa(n int) []byte {
	sign := n
	if n < 0 {
		n = -n // make n positive
	}
	var s []byte
	for { // generate digits in reverse order
		s = append(s, byte(n%10)+'0') // get next digit
		n /= 10                       // delete it
		if n <= 0 {
			break
		}
	}
	if sign < 0 {
		s = append(s, '-')
	}
	//reverse(s)
	return s
}

func insertComma(s []byte, pos int) []byte {
	result := make([]byte, len(s)+1)
	copy(result, s[:pos])
	result[pos] = ','
	copy(result[pos+1:], s[pos:])
	return result
}

func intToString(num int) string {
	s := itoa(num)
	//fmt.Printf("test1: %s", s)
	length := len(s)
	//fmt.Printf("test2: %d", length)
	for i := 3; i <= length; i += 4 {
		s = insertComma(s, i)
	}
	//fmt.Printf("test3: %s", s)
	if len(s) > 0 && s[len(s)-1] == ',' {
		s = s[:len(s)-1]
	}
	reverse(s)
	//fmt.Printf("test4: %s\n", s)

	return string(s)
}

func main() {
	num := 7000
	str := intToString(num)
	fmt.Print(str)
}