private Optional<Style> tryLoadSLD(
            final byte[] bytes, final Integer styleIndex,
            final ClientHttpRequestFactory clientHttpRequestFactory) {
        Assert.isTrue(styleIndex == null || styleIndex > -1,
                      "styleIndex must be > -1 but was: " + styleIndex);

        final Style[] styles;
        try {

            // check if the XML is valid
            // this is only done in a separate step to avoid that fatal errors show up in the logs
            // by setting a custom error handler.
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            DocumentBuilder db = dbf.newDocumentBuilder();
            db.setErrorHandler(new ErrorHandler());
            db.parse(new ByteArrayInputStream(bytes));

            // then read the styles
            final SLDParser sldParser = new SLDParser(CommonFactoryFinder.getStyleFactory());
            sldParser.setOnLineResourceLocator(new DefaultResourceLocator() {
                @Override
                public URL locateResource(final String uri) {
                    try {
                        final URL theUrl = super.locateResource(uri);
                        final URI theUri;
                        if (theUrl != null) {
                            theUri = theUrl.toURI();
                        } else {
                            theUri = URI.create(uri);
                        }
                        if (theUri.getScheme().startsWith("http")) {
                            final ClientHttpRequest request = clientHttpRequestFactory.createRequest(
                                    theUri, HttpMethod.GET);
                            return request.getURI().toURL();
                        }
                        return null;
                    } catch (IOException | URISyntaxException e) {
                        return null;
                    }
                }
            });
            sldParser.setInput(new ByteArrayInputStream(bytes));
            styles = sldParser.readXML();

        } catch (Throwable e) {
            return Optional.empty();
        }

        if (styleIndex != null) {
            Assert.isTrue(styleIndex < styles.length, String.format("There where %s styles in file but " +
                                                                            "requested index was: %s",
                                                                    styles.length, styleIndex + 1));
        } else {
            Assert.isTrue(styles.length < 2, String.format("There are %s therefore the styleRef must " +
                                                                   "contain an index identifying the style." +
                                                                   "  The index starts at 1 for the first " +
                                                                   "style.\n" +
                                                                   "\tExample: thinline.sld##1",
                                                           styles.length));
        }

        if (styleIndex == null) {
            return Optional.of(styles[0]);
        } else {
            return Optional.of(styles[styleIndex]);
        }
    }