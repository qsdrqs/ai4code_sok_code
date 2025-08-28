package main

import (
	"fmt"
	"math"
	"strconv"
)

func intToString(a int) string {
	str := make([]byte, 0, 20)
	
	buf := strconv.Itoa(a)
	bufIdx := 0
	length := len(buf)
	
	actualBuf := buf
	if length > 0 && buf[0] == '-' {
		str = append(str, buf[0])
		actualBuf = buf[1:]
		length -= 1
	}
	
	for bufIdx < length {
		if bufIdx != 0 && (length-bufIdx)%3 == 0 {
			str = append(str, ',')
		}
		str = append(str, actualBuf[bufIdx])
		bufIdx++
	}
	
	return string(str)
}

func main() {
	fmt.Println(intToString(7000000))
	fmt.Println(intToString(700000))
	fmt.Println(intToString(70000))
	fmt.Println(intToString(7000))
	fmt.Println(intToString(700))
	fmt.Println(intToString(70))
	fmt.Println(intToString(7))
	fmt.Println(intToString(0))
	fmt.Println(intToString(-7000000))
	fmt.Println(intToString(-700000))
	fmt.Println(intToString(-70000))
	fmt.Println(intToString(-7000))
	fmt.Println(intToString(-700))
	fmt.Println(intToString(-70))
	fmt.Println(intToString(-7))
	fmt.Println(intToString(math.MaxInt32))
	fmt.Println(intToString(math.MinInt32))
}