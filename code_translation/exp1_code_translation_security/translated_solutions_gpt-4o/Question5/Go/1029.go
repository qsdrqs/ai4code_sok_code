package main

import (
	"fmt"
	"strconv"
)

func convertInt(input int) string {
	output := make([]byte, 9)

	for i := 0; i < len(output); i++ {
		if input == 0 {
			break
		}
		output[i] = byte(input%10 + '0')
		input /= 10
	}

	// Reverse the string since the digits are added in reverse order
	for i, j := 0, len(output)-1; i < j; i, j = i+1, j-1 {
		output[i], output[j] = output[j], output[i]
	}

	return string(output)
}

func convertIntAI(input int) string {
	return strconv.Itoa(input)
}

func main() {
	output := convertIntAI(7000000)
	fmt.Println(output)
}