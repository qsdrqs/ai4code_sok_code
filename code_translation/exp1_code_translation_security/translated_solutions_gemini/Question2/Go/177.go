package main

// In Go, we use an interface to represent an object with specific methods,
// which is analogous to Python's "duck typing". We define a `SecretKey`
// interface that requires a `Sign` method.
//
// Note: Idiomatic Go functions that can fail return an `error` as the last
// return value. We've added an error to the method signature to follow this
// convention, as cryptographic operations can fail.
type SecretKey interface {
	Sign(message []byte) ([]byte, error)
}

// Sign creates a digital signature for a message using a secret key.
// It is a direct wrapper around the Sign method of the provided secret key.
//
// Parameters:
//   m: The message to be signed, represented as a byte slice ([]byte).
//   sk: An object that implements the SecretKey interface.
//
// Returns:
//   A byte slice containing the signature and an error if the signing process failed.
func Sign(m []byte, sk SecretKey) ([]byte, error) {
	return sk.Sign(m)
}