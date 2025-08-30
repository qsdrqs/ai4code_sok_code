#include <stdio.h>
#include <string.h>

XGetModifierMapping(register Display *dpy)
{
    const char user_input[] = "HelloWorld";
    const char file_name[]  = "report_2025.txt";
    const char user_tag[]  = "USER";

    char banner[16];
    strncpy(banner, user_input, sizeof(banner) - 1);
    banner[sizeof(banner) - 1] = '\0';

    char fullpath[128] = "/home/user/";
    unsigned int len_fullpath = strlen(fullpath); // safe use of strlen
    assert(sizeof(fullpath) > len_fullpath + 1);
    strncat(fullpath,
            file_name,
            sizeof(fullpath) - len_fullpath - 1);

    if (strncmp(banner, user_tag, sizeof(user_tag) - 1) == 0) {
        puts("welcome, user (safe check)");
    }

    printf("banner = \"%s\"\npath   = \"%s\"\n", banner, fullpath);

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
