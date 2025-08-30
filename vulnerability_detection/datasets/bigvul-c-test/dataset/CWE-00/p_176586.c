xmlFatalErrMsgStr(xmlParserCtxtPtr ctxt, xmlParserErrors error,
 const char *msg, const xmlChar * val)
{
 if ((ctxt != NULL) && (ctxt->disableSAX != 0) &&
 (ctxt->instate == XML_PARSER_EOF))
 return;
 if (ctxt != NULL)
	ctxt->errNo = error;
    __xmlRaiseError(NULL, NULL, NULL, ctxt, NULL,
                    XML_FROM_PARSER, error, XML_ERR_FATAL,
                    NULL, 0, (const char *) val, NULL, NULL, 0, 0, msg,
                    val);
 if (ctxt != NULL) {
	ctxt->wellFormed = 0;
 if (ctxt->recovery == 0)
	    ctxt->disableSAX = 1;
 }
}
