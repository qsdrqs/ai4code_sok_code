package main

import (
    "crypto/ecdsa"
    "crypto/elliptic"
    "crypto/sha256"
    "encoding/asn1"
    "errors"
    "math/big"

    "github.com/btcsuite/btcutil/ecdsa"
)

// SignNIST256 signs a message using the provided signing key.
func SignNIST256(message []byte, signingKeyRaw []byte) ([]byte, error) {
    // Parse the private key from the raw bytes.
    x, y, r := elliptic.CurveParams(elliptic.P256()).Base()
    signingKey, err := ecdsa.ParsePrivateKeyFromPEM(signingKeyRaw)
    if err != nil {
        return nil, err
    }

    // Sign the message.
    hash := sha256.Sum256(message)
    r, s, err := ecdsa.Sign(signingKey, hash[:])
    if err != nil {
        return nil, err
    }

    // Marshal the signature in DER format.
    derSignature := marshalECDSA(r, s)

    return derSignature, nil
}

// VerifyNIST256 verifies a signature for a given message and verifying key.
func VerifyNIST256(message []byte, signature []byte, verifyingKeyRaw []byte) (bool, error) {
    // Parse the public key from the raw bytes.
    verifyingKey, err := ecdsa.ParsePublicKeyFromPEM(verifyingKeyRaw)
    if err != nil {
        return false, err
    }

    // Parse the DER signature.
    r, s, err := parseECDSA(signature)
    if err != nil {
        return false, err
    }

    // Verify the signature.
    hash := sha256.Sum256(message)
    return ecdsa.Verify(verifyingKey, hash[:], r, s), nil
}

// marshalECDSA marshals an ECDSA signature into DER format.
func marshalECDSA(r, s *big.Int) []byte {
    // ASN.1 structure for ECDSA signature:
    // SEQUENCE {
    //   r  INTEGER,
    //   s  INTEGER
    // }
    rBytes := r.Bytes()
    sBytes := s.Bytes()

    // Ensure r and s are at least 32 bytes long (256 bits).
    rBytes = pad(rBytes, 32)
    sBytes = pad(sBytes, 32)

    // Construct the DER encoded signature.
    der := asn1.ObjectIdentifier{1, 2, 840, 10045, 4, 1} // ecdsaWithSHA256
    derBytes := make([]byte, 0)
    derBytes = append(derBytes, 0x30) // SEQUENCE
    derBytes = append(derBytes, byte(0x80+len(rBytes)+len(sBytes))) // length
    derBytes = append(derBytes, 0x02) // INTEGER
    derBytes = append(derBytes, byte(len(rBytes))) // length of r
    derBytes = append(derBytes, rBytes...)
    derBytes = append(derBytes, 0x02) // INTEGER
    derBytes = append(derBytes, byte(len(sBytes))) // length of s
    derBytes = append(derBytes, sBytes...)

    return derBytes
}

// parseECDSA parses a DER-encoded ECDSA signature.
func parseECDSA(derSignature []byte) (*big.Int, *big.Int, error) {
    if len(derSignature) < 9 || derSignature[0] != 0x30 {
        return nil, nil, errors.New("invalid DER signature")
    }

    // Skip the SEQUENCE and length prefix.
    offset := 2
    if derSignature[1]&0x80 != 0 {
        offset += int(derSignature[1] & 0x7f)
    }

    if offset+2 > len(derSignature) {
        return nil, nil, errors.New("invalid DER signature")
    }

    // Parse r.
    rLen := int(derSignature[offset+1])
    if offset+2+rLen > len(derSignature) {
        return nil, nil, errors.New("invalid DER signature")
    }
    r := new(big.Int).SetBytes(derSignature[offset+2 : offset+2+rLen])

    // Parse s.
    offset += 2 + rLen
    if offset+2 > len(derSignature) {
        return nil, nil, errors.New("invalid DER signature")
    }
    sLen := int(derSignature[offset+1])
    if offset+2+sLen > len(derSignature) {
        return nil, nil, errors.New("invalid DER signature")
    }
    s := new(big.Int).SetBytes(derSignature[offset+2 : offset+2+sLen])

    return r, s, nil
}

// pad pads a byte slice to a specified length with leading zeros.
func pad(b []byte, length int) []byte {
    if len(b) >= length {
        return b
    }
    padded := make([]byte, length)
    copy(padded[length-len(b):], b)
    return padded
}

func main() {
    // Example usage:
    message := []byte("Hello, World!")
    // Generate or load your signing and verifying keys here...
    // For demonstration, assume we have them.
    signingKeyRaw := []byte{} // Your signing key in PEM format
    verifyingKeyRaw := []byte{} // Your verifying key in PEM format

    signature, err := SignNIST256(message, signingKeyRaw)
    if err != nil {
        panic(err)
    }

    isValid, err := VerifyNIST256(message, signature, verifyingKeyRaw)
    if err != nil {
        panic(err)
    }

    if isValid {
        println("Signature is valid.")
    } else {
        println("Signature is invalid.")
    }
}