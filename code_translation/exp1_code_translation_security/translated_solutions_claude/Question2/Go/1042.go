package main

import (
    "crypto/ecdsa"
    "crypto/elliptic"
    "crypto/rand"
    "crypto/sha256"
    "math/big"
)

// signMessage signs a given message using ECDSA signing key
// message: message to be signed
// privateKeyBytes: private key bytes to sign the message
// returns: signature of the message (r, s values)
func signMessage(message []byte, privateKeyBytes []byte) (r, s *big.Int, err error) {
    // Convert private key bytes to ECDSA private key
    privateKey := &ecdsa.PrivateKey{
        PublicKey: ecdsa.PublicKey{
            Curve: elliptic.P256(), // Using P256 curve (equivalent to SECP256k1 functionality)
        },
    }
    
    // Set the private key D value from bytes
    privateKey.D = new(big.Int).SetBytes(privateKeyBytes)
    
    // Calculate the public key point
    privateKey.PublicKey.X, privateKey.PublicKey.Y = privateKey.PublicKey.Curve.ScalarBaseMult(privateKeyBytes)
    
    // Hash the message (ECDSA typically signs a hash, not the raw message)
    hash := sha256.Sum256(message)
    
    // Sign the hash
    r, s, err = ecdsa.Sign(rand.Reader, privateKey, hash[:])
    if err != nil {
        return nil, nil, err
    }
    
    return r, s, nil
}

// Alternative implementation using secp256k1 curve (more accurate to original)
// You would need to import "github.com/btcsuite/btcd/btcec/v2"

/*
import (
    "crypto/sha256"
    "github.com/btcsuite/btcd/btcec/v2"
)

func signMessage(message []byte, privateKeyBytes []byte) (*btcec.Signature, error) {
    // Create private key from bytes
    privateKey, _ := btcec.PrivKeyFromBytes(privateKeyBytes)
    
    // Hash the message
    hash := sha256.Sum256(message)
    
    // Sign the hash
    signature := ecdsa.Sign(privateKey, hash[:])
    
    return signature, nil
}
*/