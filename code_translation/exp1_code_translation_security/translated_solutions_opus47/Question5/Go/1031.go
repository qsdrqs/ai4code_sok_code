package main

import (
	"fmt"
)

func intToString(input int) string {
	buffer := fmt.Sprintf("%d", input)
	fmt.Printf("you have entered: %s", buffer)
	return buffer
}

func main() {
	intToString(7000000)
}