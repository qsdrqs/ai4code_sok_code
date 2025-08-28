package main

import (
	"fmt"
	"math"
	"strconv"
)

// use log from math package (equivalent to netlib.org/fdlibm)

func stringRep(num int) *string {
	// calculate size needed (adding 1 for null terminator equivalent)
	size := int(math.Log10(float64(num))) + 1
	
	// convert number to string
	str := strconv.Itoa(num)
	
	// simulate heap allocation by returning pointer to string
	return &str
}

func main() {
	leet := 1337
	leetStr := stringRep(leet)
	fmt.Printf("%s", *leetStr)
	// Note: Go has garbage collection, so no explicit free() needed
}