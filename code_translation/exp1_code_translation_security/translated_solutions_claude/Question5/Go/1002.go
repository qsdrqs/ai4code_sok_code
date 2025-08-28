package main

import (
	"fmt"
	"strconv"
)

const BUFFER_SIZE = 1024

func convert(value int64) string {
	// Convert int64 to string (equivalent to snprintf)
	buf1 := strconv.FormatInt(value, 10)
	
	// Create a byte slice to build the result
	buf2 := make([]byte, 0, BUFFER_SIZE)
	
	length := len(buf1)
	
	for i := 0; i < length; i++ {
		buf2 = append(buf2, buf1[i])
		dist := length - i - 1
		if dist%3 == 0 && dist != 0 {
			buf2 = append(buf2, ',')
		}
	}
	
	return string(buf2)
}

func main() {
	test := convert(1000)
	fmt.Println(test)
}