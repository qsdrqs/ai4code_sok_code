private Object transform(final Node source) {
        try {
            final DomNode sourceDomNode = source.getDomNodeOrDie();
            Source xmlSource = new DOMSource(sourceDomNode);

            final DomNode xsltDomNode = style_.getDomNodeOrDie();
            final Source xsltSource = new DOMSource(xsltDomNode);

            final TransformerFactory transformerFactory = TransformerFactory.newInstance();

            final SgmlPage page = sourceDomNode.getPage();
            if (page != null && page.getWebClient().getBrowserVersion()
                                            .hasFeature(JS_XSLT_TRANSFORM_INDENT)) {
                final DomNode outputNode = findOutputNode(xsltDomNode);
                if (outputNode != null) {
                    final org.w3c.dom.Node indentNode = outputNode.getAttributes().getNamedItem("indent");
                    if (indentNode != null && "yes".equalsIgnoreCase(indentNode.getNodeValue())) {
                        try {
                            transformerFactory.setAttribute("indent-number", new Integer(2));
                        }
                        catch (final IllegalArgumentException e) {
                            // ignore
                        }
                        final Transformer transformer = transformerFactory.newTransformer(xsltSource);
                        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                        try {
                            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
                        }
                        catch (final IllegalArgumentException e) {
                            // ignore
                        }

                        for (final Map.Entry<String, Object> entry : parameters_.entrySet()) {
                            transformer.setParameter(entry.getKey(), entry.getValue());
                        }

                        // hack to preserve indention
                        // the transformer only accepts the OutputKeys.INDENT setting if
                        // the StreamResult is used
                        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                            transformer.transform(xmlSource, new StreamResult(out));
                            final WebResponseData data =
                                    new WebResponseData(out.toByteArray(), 200, null, Collections.emptyList());
                            final WebResponse response = new WebResponse(data, null, 0);
                            return XmlUtils.buildDocument(response);
                        }
                    }
                }
            }

            final Transformer transformer = transformerFactory.newTransformer(xsltSource);
            for (final Map.Entry<String, Object> entry : parameters_.entrySet()) {
                transformer.setParameter(entry.getKey(), entry.getValue());
            }

            final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            final org.w3c.dom.Document containerDocument = factory.newDocumentBuilder().newDocument();
            final org.w3c.dom.Element containerElement = containerDocument.createElement("container");
            containerDocument.appendChild(containerElement);

            final DOMResult result = new DOMResult(containerElement);
            transformer.transform(xmlSource, result);

            final org.w3c.dom.Node transformedNode = result.getNode();
            final org.w3c.dom.Node transformedFirstChild = transformedNode.getFirstChild();
            if (transformedFirstChild != null && transformedFirstChild.getNodeType() == Node.ELEMENT_NODE) {
                return transformedNode;
            }

            // output is not DOM (text)
            xmlSource = new DOMSource(source.getDomNodeOrDie());
            final StringWriter writer = new StringWriter();
            final Result streamResult = new StreamResult(writer);
            transformer.transform(xmlSource, streamResult);
            return writer.toString();
        }
        catch (final Exception e) {
            throw Context.reportRuntimeError("Exception: " + e);
        }
    }