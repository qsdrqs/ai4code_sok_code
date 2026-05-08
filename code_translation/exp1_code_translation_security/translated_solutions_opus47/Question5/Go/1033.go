package main

import (
	"bytes"
	"fmt"
	"strconv"
)

func numToStr(num int, str []byte) {
	s := strconv.Itoa(num)
	copy(str, s)

	length := len(s)

	if num == 0 {
		return
	} else if num > 0 {
		digitsMoved := 0

		for i := length; i > 0; i-- {
			if digitsMoved < 3 {
				str[i] = str[i-1]
				digitsMoved++
			} else {
				str[i] = ','
				digitsMoved = 0
			}
		}
	} else {
		digitsMoved := 0

		for i := length; i > 1; i-- {
			if digitsMoved < 3 {
				str[i] = str[i-1]
				digitsMoved++
			} else {
				str[i] = ','
				digitsMoved = 0
			}
		}
	}
}

func main() {
	num := 7042
	str := make([]byte, 24)

	numToStr(num, str)

	// Find the null terminator (equivalent to C's %s behavior)
	end := bytes.IndexByte(str, 0)
	if end < 0 {
		end = len(str)
	}

	fmt.Printf("Output: %s", string(str[:end]))
}