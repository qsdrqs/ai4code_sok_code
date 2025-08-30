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

int __glXDispSwap_CreateContext(__GLXclientState *cl, GLbyte *pc)
 {
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

     xGLXCreateContextReq *req = (xGLXCreateContextReq *) pc;
     __GLX_DECLARE_SWAP_VARIABLES;
 
     __GLX_SWAP_SHORT(&req->length);
     __GLX_SWAP_INT(&req->context);
     __GLX_SWAP_INT(&req->visual);
    return __glXDisp_CreateContext(cl, pc);
}
