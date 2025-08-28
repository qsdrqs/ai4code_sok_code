package main

import (
	"fmt"
	"strconv"
)

// Function to calculate the number of digits in an integer
func numDigits(n int) int {
	if n == 0 {
		return 1
	}
	cnt := 0
	for n != 0 {
		n = n / 10
		cnt++
	}
	return cnt
}

// Function to return a formatted string with commas at every third index
func returnString(num int) string {
	// Convert the number to a string
	str := strconv.Itoa(num)
	length := len(str)

	// Calculate the size of the resulting string with commas
	commaCount := (length - 1) / 3
	ret := make([]byte, length+commaCount)

	// Fill the result string with commas and digits
	j := len(ret) - 1
	for i := length - 1; i >= 0; i-- {
		if (length-i-1)%3 == 0 && j != len(ret)-1 {
			ret[j] = ','
			j--
		}
		ret[j] = str[i]
		j--
	}

	return string(ret)
}

func main() {
	num := 7000000
	str := returnString(num)
	fmt.Println(str)
}