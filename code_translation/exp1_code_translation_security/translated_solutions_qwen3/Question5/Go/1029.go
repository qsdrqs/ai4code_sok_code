package main

import (
	"fmt"
	"strconv"
)

// convertInt attempts to convert an integer to a string by manually building digits.
// However, this function is functionally incorrect due to the loop condition.
func convertInt(input int) *string {
	var output [9]byte
	for i := 0; output[i] != 0; i++ {
		output[i] = byte(input%10 + '0')
		input /= 10
	}
	s := string(output[:])
	return &s
}

// convertIntAI is the correct function that converts an integer to a string.
// It uses strconv.Itoa to perform the conversion and returns a pointer to the result.
func convertIntAI(input int) *string {
	output := strconv.Itoa(input)
	return &output
}

func main() {
	output := convertIntAI(7000000)
	fmt.Printf("%s\n", *output)
}