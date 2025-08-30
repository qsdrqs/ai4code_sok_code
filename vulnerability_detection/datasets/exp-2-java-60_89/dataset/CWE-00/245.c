public StringWriter convertToMmt(InputStream stream)
    {
        ViewUtils.Debug(this, "Converting SMath Studio file " + fileName);
        try
        {
            // Source document
            final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            final DocumentBuilder builder = factory.newDocumentBuilder();
            final Document doc = builder.parse(stream);
            final Node objects = doc.getDocumentElement();
            List<Element> metadata = null;
            final List<Element> regions = new ArrayList<>();
            for (Node object = objects.getFirstChild(); object != null; object = object.getNextSibling())
            {
                if (object instanceof Element)
                {
                    final Element e = (Element) object;
                    if (e.getTagName().equals("settings"))
                    {
                        metadata = XmlUtils.getElements(e, "metadata");
                    }
                    else
                    {
                        regions.add(e);
                    }
                }
            }

            // Target document
            final StringWriter writer = new StringWriter();
            final XmlSerializer serializer = Xml.newSerializer();
            serializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
            serializer.setOutput(writer);
            serializer.startDocument("UTF-8", true);
            serializer.setPrefix(FormulaList.XML_PROP_MMT, FormulaList.XML_MMT_SCHEMA);
            serializer.startTag(FormulaList.XML_NS, FormulaList.XML_MAIN_TAG);
            serializer.startTag(FormulaList.XML_NS, FormulaList.XML_LIST_TAG);
            serializer.attribute(FormulaList.XML_NS, DocumentProperties.XML_PROP_VERSION, "2");
            if (metadata != null)
            {
                parseMetadata(metadata, serializer);
            }
            serializer.attribute(FormulaList.XML_NS, DocumentProperties.XML_PROP_REDEFINE_ALLOWED, "true");

            Element prevRegion = null;
            for (Element e : regions)
            {
                if ("region".equals(e.getTagName()))
                {
                    parseRegion(e, prevRegion, serializer);
                    prevRegion = e;
                }
            }

            serializer.endTag(FormulaList.XML_NS, FormulaList.XML_LIST_TAG);
            serializer.endTag(FormulaList.XML_NS, FormulaList.XML_MAIN_TAG);
            serializer.endDocument();

            /*
            InputStream s = new ByteArrayInputStream(writer.toString().getBytes());
            BufferedReader reader = new BufferedReader(new InputStreamReader(s));
            while (reader.ready())
            {
                String line = reader.readLine();
                ViewUtils.Debug(this, line);
            }
            */

            return writer;
        }
        catch (Exception e)
        {
            ViewUtils.Debug(this, "Error: " + e.getLocalizedMessage());
            return null;
        }
    }