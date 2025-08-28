package main

import (
	"fmt"
	"math"
	"strconv"
)

// stringRep returns a string representation of the given number.
func stringRep(num int) *string {
	// Convert the number to a string
	str := strconv.Itoa(num)
	return &str
}

func main() {
	leet := 1337
	leetStr := stringRep(leet)
	fmt.Println(*leetStr)
	// No need to free in Go, as it has a garbage collector
}