package main

import (
	"fmt"
	"strconv"
)

func int2str(num int) string {
	str := strconv.Itoa(num)
	length := len(str)

	var newl int
	if length%3 == 0 {
		newl = length + length/3 - 1
	} else {
		newl = length + length/3
	}

	result := make([]byte, newl)
	counter := 0
	j := newl - 1
	for i := length - 1; i >= 0; i-- {
		counter++
		result[j] = str[i]
		j--
		if counter%3 == 0 && i > 0 {
			result[j] = ','
			j--
		}
	}
	return string(result)
}

func main() {
	fmt.Println(int2str(1234567)) // 1,234,567
	fmt.Println(int2str(1234))    // 1,234
	fmt.Println(int2str(123))     // 123
	fmt.Println(int2str(123456))  // 123,456
}