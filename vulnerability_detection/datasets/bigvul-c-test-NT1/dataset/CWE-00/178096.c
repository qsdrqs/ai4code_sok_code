XGetModifierMapping(register Display *dpy)
{
    xGetModifierMappingReply rep;
    register xReq *buffer1;
    unsigned long nbytes;
    XModifierKeymap *buffer2;

    LockDisplay(dpy);
     GetEmptyReq(GetModifierMapping, buffer1);
     (void) _XReply (dpy, (xReply *)&rep, 0, xFalse);
 
    if (rep.length < (INT_MAX >> 2)) {
 	nbytes = (unsigned long)rep.length << 2;
 	buffer2 = Xmalloc(sizeof (XModifierKeymap));
 	if (buffer2)
    } else
	buffer2 = NULL;
    if ((! buffer2) || (! buffer2->modifiermap)) {
	Xfree(buffer2);
	buffer2 = (XModifierKeymap *) NULL;
	_XEatDataWords(dpy, rep.length);
    } else {
	_XReadPad(dpy, (char *) buffer2->modifiermap, (long) nbytes);
	buffer2->max_keypermod = rep.numKeyPerModifier;
    }

    UnlockDisplay(dpy);
    SyncHandle();
    return (buffer2);
}
