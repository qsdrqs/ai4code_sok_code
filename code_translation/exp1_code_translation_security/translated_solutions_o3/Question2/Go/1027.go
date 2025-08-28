package sign

// SigningKey is an interface representing any object that is able to
// produce a digital signature for a message.
type SigningKey interface {
	// Sign should return the signature for the supplied message.
	// The concrete implementation and any error handling are left to the
	// underlying cryptographic library that will be provided.
	Sign(message []byte) ([]byte, error)
}

// signMessage takes an arbitrary message and a key that satisfies the
// SigningKey interface, and returns the resulting signature.
func signMessage(message []byte, signingKey SigningKey) ([]byte, error) {
	return signingKey.Sign(message)
}