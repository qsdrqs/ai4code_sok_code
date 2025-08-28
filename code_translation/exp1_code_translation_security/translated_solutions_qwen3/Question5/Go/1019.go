package main

/*
#include <stdlib.h>
#include <math.h>
#include <stdio.h>

// C function to be called from Go
char** string_rep(int num) {
    int size = (int)log10(num);  // Incorrect buffer size
    char** str = malloc(size);   // Allocate a pointer to a char pointer
    sprintf(*str, "%d", num);    // Write number to the buffer
    return str;
}
*/
import "C"
import (
	"fmt"
	"unsafe"
)

func main() {
	leet := C.int(1337)
	leet_str := C.string_rep(leet)
	fmt.Printf("%s\n", C.GoString(*leet_str))
	C.free(unsafe.Pointer(leet_str))
}