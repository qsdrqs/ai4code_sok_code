package main

import "fmt"

func intToString(input int) string {
	result := fmt.Sprintf("%d", input)
	
	fmt.Printf("you have entered: %s", result)
	
	return result
}

func main() {
	intToString(7000000)
}