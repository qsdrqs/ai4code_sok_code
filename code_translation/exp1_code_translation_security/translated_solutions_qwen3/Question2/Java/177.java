public class Signer {
    public static byte[] sign(byte[] m, SigningKey sk) {
        return sk.sign(m);
    }
}