protected void configureParser(final XMLReader parser, final SAXHandler contentHandler)
			throws JDOMException {

		// Setup SAX handlers.

		parser.setContentHandler(contentHandler);

		if (saxEntityResolver != null) {
			parser.setEntityResolver(saxEntityResolver);
		}

		if (saxDTDHandler != null) {
			parser.setDTDHandler(saxDTDHandler);
		} else {
			parser.setDTDHandler(contentHandler);
		}

		if (saxErrorHandler != null) {
			parser.setErrorHandler(saxErrorHandler);
		} else {
			parser.setErrorHandler(new BuilderErrorHandler());
		}

		boolean success = false;

		try {
			parser.setProperty(SAX_PROPERTY_LEXICAL_HANDLER,
					contentHandler);
			success = true;
		} catch (final SAXNotSupportedException e) {
			// No lexical reporting available
		} catch (final SAXNotRecognizedException e) {
			// No lexical reporting available
		}

		// Some parsers use alternate property for lexical handling (grr...)
		if (!success) {
			try {
				parser.setProperty(SAX_PROPERTY_LEXICAL_HANDLER_ALT,
						contentHandler);
				success = true;
			} catch (final SAXNotSupportedException e) {
				// No lexical reporting available
			} catch (final SAXNotRecognizedException e) {
				// No lexical reporting available
			}
		}

		// Set any user-specified features on the parser.
		for (final Map.Entry<String, Boolean> me : features.entrySet()) {
			internalSetFeature(parser, me.getKey(), me.getValue().booleanValue(), me.getKey());
		}

		// Set any user-specified properties on the parser.
		for (final Map.Entry<String, Object> me : properties.entrySet()) {
			internalSetProperty(parser, me.getKey(), me.getValue(), me.getKey());
		}

		// Set entity expansion
		// Note SAXHandler can work regardless of how this is set, but when
		// entity expansion it's worth it to try to tell the parser not to
		// even bother with external general entities.
		// Apparently no parsers yet support this feature.
		// XXX It might make sense to setEntityResolver() with a resolver
		// that simply ignores external general entities
		try {
			if (parser.getFeature(SAX_FEATURE_EXTERNAL_ENT) != expand) {
				parser.setFeature(SAX_FEATURE_EXTERNAL_ENT, expand);
			}
		} catch (final SAXException e) { /* Ignore... */
		}

		// Try setting the DeclHandler if entity expansion is off
		if (!expand) {
			try {
				parser.setProperty(SAX_PROPERTY_DECLARATION_HANDLER,
						contentHandler);
				success = true;
			} catch (final SAXNotSupportedException e) {
				// No lexical reporting available
			} catch (final SAXNotRecognizedException e) {
				// No lexical reporting available
			}
		}

	}