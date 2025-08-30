@Override
    @SuppressWarnings("unchecked")
    public <T> T fromString(String content, Class<T> classOfT) {
        try (StringReader reader = new StringReader(content)) {
            JAXBContext jaxbContext = JAXBContext.newInstance(classOfT);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            return (T) jaxbUnmarshaller.unmarshal(reader);
        } catch (JAXBException e) {
            throw new PippoRuntimeException(e, "Failed to deserialize content to '{}'", classOfT.getName());
        }
    }