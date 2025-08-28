package main

import (
	"fmt"
	"math"
	"strconv"
)

func convertToString(num int) string {
	str := strconv.Itoa(num)
	numOfCommas := int(math.Round(float64(len(str)) / 3))
	newStr := ""

	for i, char := range str {
		newStr += string(char)
		if (i+1)%3 == 0 && i+1 != len(str) {
			newStr += ","
		}
	}

	return newStr
}

func main() {
	fmt.Println(convertToString(2000))
}