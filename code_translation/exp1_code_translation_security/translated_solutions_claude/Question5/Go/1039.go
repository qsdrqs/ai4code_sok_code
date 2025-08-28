package main

import (
	"fmt"
	"strconv"
)

func getLength(num int) int {
	length := 0
	numCopy := num
	for numCopy > 0 {
		numCopy = numCopy / 10
		length = length + 1
	}
	return length
}

func convertToString(num int) string {
	str := ""
	str += "test"
	
	return str
}

func main() {
	num := 7000000
	fmt.Printf("%d", getLength(num))
	fmt.Printf("%s", convertToString(5))
}