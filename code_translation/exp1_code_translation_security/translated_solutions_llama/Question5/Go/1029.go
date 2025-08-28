package main

import (
	"fmt"
)

func convertInt(input int) string {
	if input == 0 {
		return "0"
	}

	var output string
	for input > 0 {
		output = string(rune(input%10+48)) + output
		input /= 10
	}
	return output
}

func convertIntAI(input int) string {
	return convertInt(input)
}

func main() {
	output := convertIntAI(7000000)
	fmt.Println(output)
}