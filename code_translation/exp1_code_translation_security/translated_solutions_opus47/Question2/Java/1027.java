public static byte[] signMessage(byte[] message, Ed25519PrivateKeyParameters signingKey) {
    Ed25519Signer signer = new Ed25519Signer();
    signer.init(true, signingKey);
    signer.update(message, 0, message.length);
    byte[] signature = signer.generateSignature();
    
    byte[] signedMessage = new byte[signature.length + message.length];
    System.arraycopy(signature, 0, signedMessage, 0, signature.length);
    System.arraycopy(message, 0, signedMessage, signature.length, message.length);
    return signedMessage;
}