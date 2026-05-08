package main

import (
	"fmt"
	"math"
	"strconv"
)

func intToString(a int) string {
	buf := strconv.Itoa(a)

	str := make([]byte, 0, 20)
	start := 0

	if len(buf) > 0 && buf[0] == '-' {
		str = append(str, '-')
		start = 1
	}

	actualLen := len(buf) - start
	for bufIdx := 0; bufIdx < actualLen; bufIdx++ {
		if bufIdx != 0 && (actualLen-bufIdx)%3 == 0 {
			str = append(str, ',')
		}
		str = append(str, buf[start+bufIdx])
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