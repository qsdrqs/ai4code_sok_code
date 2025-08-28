package main

import (
	"fmt"
	"math/rand"
	"time"
)

func main() {
	// Seed the PRNG exactly like  srand((unsigned)time(NULL));
	rand.Seed(time.Now().UnixNano())

	// value = rand();
	value := rand.Int63() // use 63-bit positive integer (fits in C's long on 64-bit)

	fmt.Printf("Before:\t%d\n", value)

	// sprintf(vstring, "%ld", value);
	vstring := fmt.Sprintf("%d", value)

	// len = strlen(vstring);
	length := len(vstring)

	// The two indices c1 and v1 of the C code become implicit
	// inside the loop. We build the result in a byte slice.
	var commas []byte

	// while (vstring[v1]) { ... }
	for v1 := 0; v1 < length; v1++ {
		// digits left including current one
		remaining := length - v1

		if remaining%3 == 0 {
			// Same logic as the C version: insert a comma *before*
			// the digit if it's not the first digit we copy.
			if v1 != 0 {
				commas = append(commas, ',')
			}
		}
		// copy the digit itself
		commas = append(commas, vstring[v1])
	}

	fmt.Printf("With:\t%s\n", string(commas))
}