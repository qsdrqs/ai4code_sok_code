public static byte[] signUsingECDSA(PrivateKey key, byte[] message) 
        throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
    Signature signer = Signature.getInstance("SHA256withECDSA");
    signer.initSign(key);
    signer.update(message);
    return signer.sign();
}