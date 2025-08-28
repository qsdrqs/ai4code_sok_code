package main

import (
	"fmt"
	"math/rand"
	"strconv"
	"time"
)

func main() {
	var value int64
	var c1, v1, length int
	var vstring string
	commas := make([]byte, 14)

	rand.Seed(time.Now().UnixNano())
	value = int64(rand.Int31())

	fmt.Printf("Before: \t%d\n", value)
	vstring = strconv.FormatInt(value, 10)

	length = len(vstring)
	c1 = 0
	v1 = 0

	for v1 < len(vstring) {
		if length%3 != 0 {
			commas[c1] = vstring[v1]
		} else {
			if c1 != 0 {
				commas[c1] = ','
				c1++
			}
			commas[c1] = vstring[v1]
		}
		c1++
		v1++
		length--
	}

	fmt.Printf("With: \t%s\n", string(commas[:c1]))
}