package main

import (
	"fmt"
	"math/rand"
	"strconv"
	"time"
)

func main() {
	rand.Seed(time.Now().UnixNano())
	value := int64(rand.Int31())

	fmt.Printf("Before: \t%d\n", value)
	vstring := strconv.FormatInt(value, 10)

	length := len(vstring)
	commas := make([]byte, 14)
	c1 := 0
	v1 := 0

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

	fmt.Printf("with: \t%s\n", string(commas[:c1]))
}