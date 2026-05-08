func signMessage(message []byte, signingKey ed25519.PrivateKey) []byte {
      signature := ed25519.Sign(signingKey, message)
      return append(signature, message...)
  }