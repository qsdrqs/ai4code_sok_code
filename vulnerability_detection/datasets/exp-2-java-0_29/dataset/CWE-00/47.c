private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();

        token = parseToken(tokenString, AccessToken.class);
        idToken = parseToken(idTokenString, IDToken.class);
    }