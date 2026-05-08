package main

import "fmt"

// strrev reverses a byte slice in place
func strrev(input []byte) []byte {
	start := 0
	end := len(input) - 1
	for end > start {
		input[start], input[end] = input[end], input[start]
		start++
		end--
	}
	return input
}

// integerToString takes a signed integer num and returns a string representation of the integer
func integerToString(num int) string {
	result := make([]byte, 0, 100)
	isNegative := false

	if num < 0 {
		isNegative = true
		num = -num
	}

	for {
		digit := num % 10
		result = append(result, byte('0'+digit))
		num /= 10
		if num == 0 {
			break
		}
	}

	if isNegative {
		result = append(result, '-')
	}

	return string(strrev(result))
}

func main() {
	num := integerToString(12345)
	fmt.Println(num)

	num = integerToString(-12345)
	fmt.Println(num)
}