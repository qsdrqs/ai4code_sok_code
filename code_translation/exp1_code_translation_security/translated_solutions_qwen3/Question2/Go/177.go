package main

// Assuming that the type of sk has a method Sign that takes a []byte and returns a []byte.
// This is a direct translation of the Python function, assuming the existence of a type with a Sign method.

func sign(m []byte, sk SomeType) []byte {
    return sk.Sign(m)
}