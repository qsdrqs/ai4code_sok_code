public Object read(final InputSource in) throws SAXException,
                                            IOException {
        if ( this.docFragment == null ) {
            DocumentBuilderFactory f;
            try {
                f =  DocumentBuilderFactory.newInstance();
            } catch ( FactoryConfigurationError e ) {
                // obscure JDK1.5 bug where FactoryFinder in the JRE returns a null ClassLoader, so fall back to hard coded xerces.
                // https://stg.network.org/bugzilla/show_bug.cgi?id=47169
                // http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=4633368
                try {
                    f = (DocumentBuilderFactory) Class.forName( "org.apache.xerces.jaxp.DocumentBuilderFactoryImpl" ).newInstance();
                } catch ( Exception e1 ) {
                    throw new RuntimeException( "Unable to create new DOM Document",
                                                e1 );
                }
            } catch ( Exception e ) {
                throw new RuntimeException( "Unable to create new DOM Document",
                                            e );
            }
            
            try {
                this.document = f.newDocumentBuilder().newDocument();
            } catch ( Exception e ) {
                throw new RuntimeException( "Unable to create new DOM Document",
                                            e );
            }
            this.docFragment = this.document.createDocumentFragment();
        }

        SAXParser localParser = null;
        if ( this.parser == null ) {
            SAXParserFactory factory = null;
            try {
                factory = SAXParserFactory.newInstance();
            } catch ( FactoryConfigurationError e) {
                // obscure JDK1.5 bug where FactoryFinder in the JRE returns a null ClassLoader, so fall back to hard coded xerces.
                // https://stg.network.org/bugzilla/show_bug.cgi?id=47169
                // http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=4633368                
                try {
                    factory = (SAXParserFactory) Class.forName( "org.apache.xerces.jaxp.SAXParserFactoryImpl" ).newInstance();
                } catch ( Exception e1 ) {
                    throw new RuntimeException( "Unable to create new DOM Document",
                                                e1 );
                }
            } catch ( Exception e ) {
                throw new RuntimeException( "Unable to create new DOM Document",
                                            e );
            }
            
            factory.setNamespaceAware( true );

            final String isValidatingString = System.getProperty( "drools.schema.validating" );
            if ( System.getProperty( "drools.schema.validating" ) != null ) {
                this.isValidating = Boolean.getBoolean( "drools.schema.validating" );
            }

            if ( this.isValidating == true ) {
                factory.setValidating( true );
                try {
                    localParser = factory.newSAXParser();
                } catch ( final ParserConfigurationException e ) {
                    throw new RuntimeException( e.getMessage() );
                }

                try {
                    localParser.setProperty( ExtensibleXmlParser.JAXP_SCHEMA_LANGUAGE,
                                             ExtensibleXmlParser.W3C_XML_SCHEMA );
                } catch ( final SAXNotRecognizedException e ) {
                    boolean hideWarnings = Boolean.getBoolean( "drools.schema.hidewarnings" );
                    if ( !hideWarnings ) {
                        logger.warn( "Your SAX parser is not JAXP 1.2 compliant - turning off validation." );
                    }
                    localParser = null;
                }
            }

            if ( localParser == null ) {
                // not jaxp1.2 compliant so turn off validation
                try {
                    this.isValidating = false;
                    factory.setValidating( this.isValidating );
                    localParser = factory.newSAXParser();
                } catch ( final ParserConfigurationException e ) {
                    throw new RuntimeException( e.getMessage() );
                }
            }
        } else {
            localParser = this.parser;
        }

        if ( !localParser.isNamespaceAware() ) {
            throw new RuntimeException( "parser must be namespace-aware" );
        }

        localParser.parse( in,
                           this );

        return this.data;
    }