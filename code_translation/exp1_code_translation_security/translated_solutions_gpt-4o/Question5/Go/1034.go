package main

import (
	"fmt"
	"math/rand"
	"strconv"
	"time"
)

func main() {
	var value int64
	var c1, v1, len int
	var vstring string
	var commas []rune

	// Seed the random number generator
	rand.Seed(time.Now().UnixNano())
	value = rand.Int63()

	fmt.Printf("Before: \t%d\n", value)

	// Convert the value to a string
	vstring = strconv.FormatInt(value, 10)

	len = len(vstring)
	c1, v1 = 0, 0
	commas = make([]rune, 0)

	for v1 < len {
		if (len-v1)%3 == 0 && c1 != 0 {
			commas = append(commas, ',')
		}
		commas = append(commas, rune(vstring[v1]))
		c1++
		v1++
	}

	fmt.Printf("With: \t%s\n", string(commas))
}