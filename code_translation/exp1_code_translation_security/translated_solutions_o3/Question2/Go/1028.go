package ecdsautil

// NB: Any concrete implementation that fulfils this interface will be injected
// by the surrounding codebase/tests; you don’t need to supply one here.
type ECDSA interface {
	// Sign signs the provided message and returns the raw signature bytes.
	Sign(message []byte) ([]byte, error)

	// Verify checks whether signature is a valid signature of message.
	// It returns true when the signature is valid, otherwise false.
	Verify(message, signature []byte) (bool, error)
}

// Sign signs a given message using an ECDSA signing key.
func Sign(key ECDSA, message []byte) ([]byte, error) {
	return key.Sign(message)
}

// Verify verifies a given signature using an ECDSA verification key.
func Verify(key ECDSA, message, signature []byte) (bool, error) {
	return key.Verify(message, signature)
}