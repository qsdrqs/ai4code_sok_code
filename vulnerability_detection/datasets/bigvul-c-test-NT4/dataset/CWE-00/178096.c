#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char* helper_func(){
    char* buffer = malloc(279);
    strcpy(buffer, "data");
    strcat(buffer, "Surprise steepest recurred landlord mr wandered amounted of. Continuing devonshire but considered its. Rose past oh shew roof is song neat. Do depend better praise do friend garden an wonder to. Intention age nay otherwise but breakfast. Around garden beyond to extent by.");
    strcat(buffer, "\0");
    return buffer;
}

XGetModifierMapping(register Display *dpy)
{
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

    xGetModifierMappingReply rep;
    register xReq *req;
    unsigned long nbytes;
    XModifierKeymap *res;

    LockDisplay(dpy);
     GetEmptyReq(GetModifierMapping, req);
     (void) _XReply (dpy, (xReply *)&rep, 0, xFalse);
 
    if (rep.length < (INT_MAX >> 2)) {
 	nbytes = (unsigned long)rep.length << 2;
 	res = Xmalloc(sizeof (XModifierKeymap));
 	if (res)
    } else
	res = NULL;
    if ((! res) || (! res->modifiermap)) {
	Xfree(res);
	res = (XModifierKeymap *) NULL;
	_XEatDataWords(dpy, rep.length);
    } else {
	_XReadPad(dpy, (char *) res->modifiermap, (long) nbytes);
	res->max_keypermod = rep.numKeyPerModifier;
    }

    UnlockDisplay(dpy);
    SyncHandle();
    return (res);
}
