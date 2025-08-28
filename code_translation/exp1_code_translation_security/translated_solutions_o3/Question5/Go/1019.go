package main

import (
	"fmt"
	"math"
	"strconv"
)

// stringRep creates a string representation of an integer.
// The explicit math.Log10 call is only here to parallel the
// original C code’s intent (it isn’t needed for strconv.Itoa).
func stringRep(num int) string {
	_ = int(math.Log10(float64(num))) + 1 // keeps the “log10” flavor
	return strconv.Itoa(num)
}

func main() {
	leet := 1337
	leetStr := stringRep(leet)
	fmt.Print(leetStr)
}