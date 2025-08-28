package main

import (
	"fmt"
	"math/rand"
	"strconv"
	"time"
)

func main() {
	// Seed the random number generator
	rand.Seed(time.Now().UnixNano())

	// Generate a random 64-bit integer
	value := rand.Int63()

	// Print the original value
	fmt.Printf("Before:\t%d\n", value)

	// Convert the value to a string
	vstring := strconv.FormatInt(value, 10)

	// Initialize variables
	lenV := len(vstring)
	var commas [14]byte
	c1, v1 := 0, 0

	// Process each character to insert commas
	for v1 < len(vstring) {
		if lenV%3 != 0 {
			commas[c1] = vstring[v1]
			c1++
			v1++
			lenV--
		} else {
			if c1 != 0 {
				commas[c1] = ','
				c1++
			}
			commas[c1] = vstring[v1]
			c1++
			v1++
			lenV--
		}
	}

	// Print the formatted result
	fmt.Printf("After:\t%s\n", string(commas[:c1]))
}