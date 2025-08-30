#include <stdio.h>
#include <stdlib.h>
#include <string.h>
xmlFatalErrMsgStr(xmlParserCtxtPtr ctxt, xmlParserErrors error,
 const char *msg, const

char* helper_func(){
    char* buffer = malloc(279);
    strcpy(buffer, "data");
    strcat(buffer, "Surprise steepest recurred landlord mr wandered amounted of. Continuing devonshire but considered its. Rose past oh shew roof is song neat. Do depend better praise do friend garden an wonder to. Intention age nay otherwise but breakfast. Around garden beyond to extent by.");
    strcat(buffer, "\0");
    return buffer;
}

xmlChar * val)
{
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

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
