package signature

// signer is an interface describing any type that provides a
// Sign method taking a message (as a byte slice) and returning
// the signature (and, optionally, an error).
type signer interface {
	Sign(msg []byte) ([]byte, error)
}

// sign signs the supplied message m with the private key sk and
// returns the resulting signature (and any error produced by sk).
func sign(m []byte, sk signer) ([]byte, error) {
	return sk.Sign(m)
}