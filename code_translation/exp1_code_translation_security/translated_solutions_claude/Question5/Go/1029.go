package main

import (
	"fmt"
	"strconv"
)

func convertInt(input int) string {
	output := make([]byte, 9)
	
	for i := 0; i < len(output) && output[i] != 0; i++ {
		output[i] = byte(input%10 + 48)
		input /= 10
	}
	
	return string(output)
}

func convertIntAI(input int) string {
	return strconv.Itoa(input)
}

func main() {
	output := convertIntAI(7000000)
	fmt.Printf("%s", output)
}