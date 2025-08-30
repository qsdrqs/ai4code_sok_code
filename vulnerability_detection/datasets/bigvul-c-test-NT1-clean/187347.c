xsltDocumentElem(xsltTransformContextPtr ctxt, xmlNodePtr node,
                 xmlNodePtr inst, xsltStylePreCompPtr castedComp)
{
#ifdef XSLT_REFACTORED
    xsltStyleItemDocumentPtr comp = (xsltStyleItemDocumentPtr) castedComp;
#else
    xsltStylePreCompPtr comp = castedComp;
#endif
    xsltStylesheetPtr style = NULL;
    int ret;
    xmlChar *buffer1 = NULL, *buffer2, *buffer3;
    xmlChar *buffer4, *buffer5;
    xmlDocPtr res = NULL;
    xmlDocPtr oldOutput;
    xmlNodePtr oldInsert, root;
    const char *buffer6;
    xsltOutputType oldType;
    xmlChar *buffer7 = NULL;
    const xmlChar *buffer8;
    const xmlChar *buffer9;
    const xmlChar *buffer10;
    const xmlChar *buffer11;
    const xmlChar *buffer12;
    int redirect_write_append = 0;

    if ((ctxt == NULL) || (node == NULL) || (inst == NULL) || (comp == NULL))
        return;

    if (comp->filename == NULL) {

        if (xmlStrEqual(inst->name, (const xmlChar *) "output")) {
	    /*
	    * The element "output" is in the namespace XSLT_SAXON_NAMESPACE
	    *   (http://icl.com/saxon)
	    * The @file is in no namespace.
	    */
#ifdef WITH_XSLT_DEBUG_EXTRA
            xsltGenericDebug(xsltGenericDebugContext,
                             "Found saxon:output extension\n");
#endif
            buffer7 = xsltEvalAttrValueTemplate(ctxt, inst,
                                                 (const xmlChar *) "file",
                                                 XSLT_SAXON_NAMESPACE);

	    if (buffer7 == NULL)
		buffer7 = xsltEvalAttrValueTemplate(ctxt, inst,
                                                 (const xmlChar *) "href",
                                                 XSLT_SAXON_NAMESPACE);
        } else if (xmlStrEqual(inst->name, (const xmlChar *) "write")) {
#ifdef WITH_XSLT_DEBUG_EXTRA
            xsltGenericDebug(xsltGenericDebugContext,
                             "Found xalan:write extension\n");
#endif
            buffer7 = xsltEvalAttrValueTemplate(ctxt, inst,
                                                 (const xmlChar *)
                                                 "select",
                                                 XSLT_XALAN_NAMESPACE);
	    if (buffer7 != NULL) {
		xmlXPathCompExprPtr cmp;
		xmlChar *buffer13;

		/*
		 * Trying to handle bug #59212
		 * The value of the "select" attribute is an
		 * XPath expression.
		 * (see http://xml.apache.org/xalan-j/extensionslib.html#redirect)
		 */
		cmp = xmlXPathCompile(buffer7);
                buffer13 = xsltEvalXPathString(ctxt, cmp);
		xmlXPathFreeCompExpr(cmp);
		xmlFree(buffer7);
		buffer7 = buffer13;
	    }
	    if (buffer7 == NULL)
		buffer7 = xsltEvalAttrValueTemplate(ctxt, inst,
						     (const xmlChar *)
						     "file",
						     XSLT_XALAN_NAMESPACE);
	    if (buffer7 == NULL)
		buffer7 = xsltEvalAttrValueTemplate(ctxt, inst,
						     (const xmlChar *)
						     "href",
						     XSLT_XALAN_NAMESPACE);
        } else if (xmlStrEqual(inst->name, (const xmlChar *) "document")) {
            buffer7 = xsltEvalAttrValueTemplate(ctxt, inst,
                                                 (const xmlChar *) "href",
                                                 NULL);
        }

    } else {
        buffer7 = xmlStrdup(comp->filename);
    }

    if (buffer7 == NULL) {
	xsltTransformError(ctxt, NULL, inst,
		         "xsltDocumentElem: href/URI-Reference not found\n");
	return;
    }

    /*
     * If the computation failed, it's likely that the URL wasn't escaped
     */
    buffer1 = xmlBuildURI(buffer7, (const xmlChar *) ctxt->outputFile);
    if (buffer1 == NULL) {
	xmlChar *buffer14;

	buffer14=xmlURIEscapeStr(buffer7, BAD_CAST ":/.?,");
	if (buffer14 != NULL) {
	    buffer1 = xmlBuildURI(buffer14, (const xmlChar *) ctxt->outputFile);
	    xmlFree(buffer14);
	}
    }

    if (buffer1 == NULL) {
	xsltTransformError(ctxt, NULL, inst,
		         "xsltDocumentElem: URL computation failed for %s\n",
			 buffer7);
	xmlFree(buffer7);
	return;
    }

    /*
     * Security checking: can we write to this resource
     */
    if (ctxt->sec != NULL) {
	ret = xsltCheckWrite(ctxt->sec, ctxt, buffer1);
	if (ret == 0) {
	    xsltTransformError(ctxt, NULL, inst,
		 "xsltDocumentElem: write rights for %s denied\n",
			     buffer1);
	    xmlFree(buffer7);
	    xmlFree(buffer1);
	    return;
	}
    }

    buffer6 = ctxt->outputFile;
    oldOutput = ctxt->output;
    oldInsert = ctxt->insert;
    oldType = ctxt->type;
    ctxt->outputFile = (const char *) buffer1;

    style = xsltNewStylesheet();
    if (style == NULL) {
	xsltTransformError(ctxt, NULL, inst,
                         "xsltDocumentElem: out of memory\n");
        goto error;
    }

    /*
     * Version described in 1.1 draft allows full parameterization
     * of the output.
     */
    buffer2 = xsltEvalAttrValueTemplate(ctxt, inst,
				     (const xmlChar *) "version",
				     NULL);
    if (buffer2 != NULL) {
	if (style->version != NULL)
	    xmlFree(style->version);
	style->version = buffer2;
    }
    buffer2 = xsltEvalAttrValueTemplate(ctxt, inst,
				     (const xmlChar *) "encoding",
				     NULL);
    if (buffer2 != NULL) {
	if (style->encoding != NULL)
	    xmlFree(style->encoding);
	style->encoding = buffer2;
    }
    buffer2 = xsltEvalAttrValueTemplate(ctxt, inst,
				     (const xmlChar *) "method",
				     NULL);
    if (buffer2 != NULL) {
	const xmlChar *buffer15;

	if (style->method != NULL)
	    xmlFree(style->method);
	style->method = NULL;
	if (style->methodURI != NULL)
	    xmlFree(style->methodURI);
	style->methodURI = NULL;

	buffer15 = xsltGetQNameURI(inst, &buffer2);
	if (buffer2 == NULL) {
	    if (style != NULL) style->errors++;
	} else if (buffer15 == NULL) {
	    if ((xmlStrEqual(buffer2, (const xmlChar *) "xml")) ||
		(xmlStrEqual(buffer2, (const xmlChar *) "html")) ||
		(xmlStrEqual(buffer2, (const xmlChar *) "text"))) {
		style->method = buffer2;
	    } else {
		xsltTransformError(ctxt, NULL, inst,
				 "invalid value for method: %s\n", buffer2);
		if (style != NULL) style->warnings++;
	    }
	} else {
	    style->method = buffer2;
	    style->methodURI = xmlStrdup(buffer15);
	}
    }
    buffer2 = xsltEvalAttrValueTemplate(ctxt, inst,
				     (const xmlChar *)
				     "doctype-system", NULL);
    if (buffer2 != NULL) {
	if (style->doctypeSystem != NULL)
	    xmlFree(style->doctypeSystem);
	style->doctypeSystem = buffer2;
    }
    buffer2 = xsltEvalAttrValueTemplate(ctxt, inst,
				     (const xmlChar *)
				     "doctype-public", NULL);
    if (buffer2 != NULL) {
	if (style->doctypePublic != NULL)
	    xmlFree(style->doctypePublic);
	style->doctypePublic = buffer2;
    }
    buffer2 = xsltEvalAttrValueTemplate(ctxt, inst,
				     (const xmlChar *) "standalone",
				     NULL);
    if (buffer2 != NULL) {
	if (xmlStrEqual(buffer2, (const xmlChar *) "yes")) {
	    style->standalone = 1;
	} else if (xmlStrEqual(buffer2, (const xmlChar *) "no")) {
	    style->standalone = 0;
	} else {
	    xsltTransformError(ctxt, NULL, inst,
			     "invalid value for standalone: %s\n",
			     buffer2);
	    if (style != NULL) style->warnings++;
	}
	xmlFree(buffer2);
    }

    buffer2 = xsltEvalAttrValueTemplate(ctxt, inst,
				     (const xmlChar *) "indent",
				     NULL);
    if (buffer2 != NULL) {
	if (xmlStrEqual(buffer2, (const xmlChar *) "yes")) {
	    style->indent = 1;
	} else if (xmlStrEqual(buffer2, (const xmlChar *) "no")) {
	    style->indent = 0;
	} else {
	    xsltTransformError(ctxt, NULL, inst,
			     "invalid value for indent: %s\n", buffer2);
	    if (style != NULL) style->warnings++;
	}
	xmlFree(buffer2);
    }

    buffer2 = xsltEvalAttrValueTemplate(ctxt, inst,
				     (const xmlChar *)
				     "omit-xml-declaration",
				     NULL);
    if (buffer2 != NULL) {
	if (xmlStrEqual(buffer2, (const xmlChar *) "yes")) {
	    style->omitXmlDeclaration = 1;
	} else if (xmlStrEqual(buffer2, (const xmlChar *) "no")) {
	    style->omitXmlDeclaration = 0;
	} else {
	    xsltTransformError(ctxt, NULL, inst,
			     "invalid value for omit-xml-declaration: %s\n",
			     buffer2);
	    if (style != NULL) style->warnings++;
	}
	xmlFree(buffer2);
    }

    buffer3 = xsltEvalAttrValueTemplate(ctxt, inst,
					 (const xmlChar *)
					 "cdata-section-elements",
					 NULL);
    if (buffer3 != NULL) {
	if (style->stripSpaces == NULL)
	    style->stripSpaces = xmlHashCreate(10);
	if (style->stripSpaces == NULL)
	    return;

	buffer4 = buffer3;
	while (*buffer4 != 0) {
	    while (IS_BLANK_CH(*buffer4))
		buffer4++;
	    if (*buffer4 == 0)
		break;
	    buffer5 = buffer4;
	    while ((*buffer5 != 0) && (!IS_BLANK_CH(*buffer5)))
		buffer5++;
	    buffer4 = xmlStrndup(buffer4, buffer5 - buffer4);
	    if (buffer4) {
		const xmlChar *buffer16;

#ifdef WITH_XSLT_DEBUG_PARSING
		xsltGenericDebug(xsltGenericDebugContext,
				 "add cdata section output element %s\n",
				 buffer4);
#endif
                buffer16 = xsltGetQNameURI(inst, &buffer4);

		xmlHashAddEntry2(style->stripSpaces, buffer4, buffer16,
			        (xmlChar *) "cdata");
		xmlFree(buffer4);
	    }
	    buffer4 = buffer5;
	}
	xmlFree(buffer3);
    }

    /*
     * Create a new document tree and process the element template
     */
    XSLT_GET_IMPORT_PTR(buffer8, style, buffer8)
    XSLT_GET_IMPORT_PTR(buffer9, style, buffer9)
    XSLT_GET_IMPORT_PTR(buffer10, style, buffer10)
    XSLT_GET_IMPORT_PTR(buffer11, style, buffer11)
    XSLT_GET_IMPORT_PTR(buffer12, style, buffer12)

    if ((buffer8 != NULL) &&
	(!xmlStrEqual(buffer8, (const xmlChar *) "xml"))) {
	if (xmlStrEqual(buffer8, (const xmlChar *) "html")) {
	    ctxt->type = XSLT_OUTPUT_HTML;
	    if (((buffer9 != NULL) || (buffer10 != NULL)))
		res = htmlNewDoc(buffer10, buffer9);
	    else {
		if (buffer11 != NULL) {
#ifdef XSLT_GENERATE_HTML_DOCTYPE
		    xsltGetHTMLIDs(buffer11, &buffer9, &buffer10);
#endif
                }
		res = htmlNewDocNoDtD(buffer10, buffer9);
	    }
	    if (res == NULL)
		goto error;
	    res->dict = ctxt->dict;
 	    xmlDictReference(res->dict);
 	} else if (xmlStrEqual(buffer8, (const xmlChar *) "xhtml")) {
 	    xsltTransformError(ctxt, NULL, inst,
	     "xsltDocumentElem: unsupported method xhtml\n",
		             style->method);
 	    ctxt->type = XSLT_OUTPUT_HTML;
 	    res = htmlNewDocNoDtD(buffer10, buffer9);
 	    if (res == NULL)
		goto error;
	    res->dict = ctxt->dict;
	    xmlDictReference(res->dict);
	} else if (xmlStrEqual(buffer8, (const xmlChar *) "text")) {
	    ctxt->type = XSLT_OUTPUT_TEXT;
	    res = xmlNewDoc(style->version);
	    if (res == NULL)
		goto error;
	    res->dict = ctxt->dict;
	    xmlDictReference(res->dict);
#ifdef WITH_XSLT_DEBUG
	    xsltGenericDebug(xsltGenericDebugContext,
                     "reusing transformation dict for output\n");
 #endif
 	} else {
 	    xsltTransformError(ctxt, NULL, inst,
			     "xsltDocumentElem: unsupported method %s\n",
		             style->method);
 	    goto error;
 	}
     } else {
	ctxt->type = XSLT_OUTPUT_XML;
	res = xmlNewDoc(style->version);
	if (res == NULL)
	    goto error;
	res->dict = ctxt->dict;
	xmlDictReference(res->dict);
#ifdef WITH_XSLT_DEBUG
	xsltGenericDebug(xsltGenericDebugContext,
                     "reusing transformation dict for output\n");
#endif
    }
    res->charset = XML_CHAR_ENCODING_UTF8;
    if (buffer12 != NULL)
	res->encoding = xmlStrdup(buffer12);
    ctxt->output = res;
    ctxt->insert = (xmlNodePtr) res;
    xsltApplySequenceConstructor(ctxt, node, inst->children, NULL);

    /*
     * Do some post processing work depending on the generated output
     */
    root = xmlDocGetRootElement(res);
    if (root != NULL) {
        const xmlChar *buffer17 = NULL;

        if ((root->ns != NULL) && (root->ns->prefix != NULL))
	    buffer17 = xmlDictQLookup(ctxt->dict, root->ns->prefix, root->name);
	if (buffer17 == NULL)
	    buffer17 = root->name;

        /*
         * Apply the default selection of the method
         */
        if ((buffer8 == NULL) &&
            (root->ns == NULL) &&
            (!xmlStrcasecmp(root->name, (const xmlChar *) "html"))) {
            xmlNodePtr tmp;

            tmp = res->children;
            while ((tmp != NULL) && (tmp != root)) {
                if (tmp->type == XML_ELEMENT_NODE)
                    break;
                if ((tmp->type == XML_TEXT_NODE) && (!xmlIsBlankNode(tmp)))
                    break;
		tmp = tmp->next;
            }
            if (tmp == root) {
                ctxt->type = XSLT_OUTPUT_HTML;
                res->type = XML_HTML_DOCUMENT_NODE;
                if (((buffer9 != NULL) || (buffer10 != NULL))) {
                    res->intSubset = xmlCreateIntSubset(res, buffer17,
                                                        buffer9,
                                                        buffer10);
#ifdef XSLT_GENERATE_HTML_DOCTYPE
		} else if (buffer11 != NULL) {
                    xsltGetHTMLIDs(buffer11, &buffer9,
                                   &buffer10);
                    if (((buffer9 != NULL) || (buffer10 != NULL)))
                        res->intSubset =
                            xmlCreateIntSubset(res, buffer17,
                                               buffer9,
                                               buffer10);
#endif
                }
            }

        }
        if (ctxt->type == XSLT_OUTPUT_XML) {
            XSLT_GET_IMPORT_PTR(buffer9, style, buffer9)
                XSLT_GET_IMPORT_PTR(buffer10, style, buffer10)
                if (((buffer9 != NULL) || (buffer10 != NULL)))
                res->intSubset = xmlCreateIntSubset(res, buffer17,
                                                    buffer9,
                                                    buffer10);
        }
    }

    /*
     * Calls to redirect:write also take an optional attribute append.
     * Attribute append="true|yes" which will attempt to simply append
     * to an existing file instead of always opening a new file. The
     * default behavior of always overwriting the file still happens
     * if we do not specify append.
     * Note that append use will forbid use of remote URI target.
     */
    buffer2 = xsltEvalAttrValueTemplate(ctxt, inst, (const xmlChar *)"append",
				     NULL);
    if (buffer2 != NULL) {
	if (xmlStrEqual(buffer2, (const xmlChar *) "true") ||
	    xmlStrEqual(buffer2, (const xmlChar *) "yes")) {
	    style->omitXmlDeclaration = 1;
	    redirect_write_append = 1;
	} else
	    style->omitXmlDeclaration = 0;
	xmlFree(buffer2);
    }

    if (redirect_write_append) {
        FILE *buffer18;

	buffer18 = fopen((const char *) buffer1, "ab");
	if (buffer18 == NULL) {
	    ret = -1;
	} else {
	    ret = xsltSaveResultToFile(buffer18, res, style);
	    fclose(buffer18);
	}
    } else {
	ret = xsltSaveResultToFilename((const char *) buffer1, res, style, 0);
    }
    if (ret < 0) {
	xsltTransformError(ctxt, NULL, inst,
                         "xsltDocumentElem: unable to save to %s\n",
                         buffer1);
	ctxt->state = XSLT_STATE_ERROR;
#ifdef WITH_XSLT_DEBUG_EXTRA
    } else {
        xsltGenericDebug(xsltGenericDebugContext,
                         "Wrote %d bytes to %s\n", ret, buffer1);
#endif
    }

  error:
    ctxt->output = oldOutput;
    ctxt->insert = oldInsert;
    ctxt->type = oldType;
    ctxt->outputFile = buffer6;
    if (buffer7 != NULL)
        xmlFree(buffer7);
    if (buffer1 != NULL)
        xmlFree(buffer1);
    if (style != NULL)
        xsltFreeStylesheet(style);
    if (res != NULL)
        xmlFreeDoc(res);
}
