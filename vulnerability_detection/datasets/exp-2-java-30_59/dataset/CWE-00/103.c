public void readDescriptor(InputStream stream) throws XarException, IOException
    {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new XarException("Failed to create a new Document builder", e);
        }

        Document doc;
        try {
            // DocumentBuilder#parse close the passed stream which is not what we want
            doc = dBuilder.parse(new CloseShieldInputStream(stream));
        } catch (SAXException e) {
            throw new XarException("Failed to parse XML document", e);
        }

        // Normalize the document
        doc.getDocumentElement().normalize();

        // Read the document
        NodeList children = doc.getChildNodes();
        for (int i = 0; i < children.getLength(); ++i) {
            Node node = children.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                if (element.getTagName().equals(XarModel.ELEMENT_PACKAGE)) {
                    readDescriptorPackage(element);
                    break;
                }
            }
        }
    }