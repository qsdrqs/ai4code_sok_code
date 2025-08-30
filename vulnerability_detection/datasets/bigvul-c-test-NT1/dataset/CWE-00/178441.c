 int __glXDispSwap_CreateContext(__GLXclientState *buffer1, GLbyte *buffer2)
 {
     xGLXCreateContextReq *buffer3 = (xGLXCreateContextReq *) pc;
     __GLX_DECLARE_SWAP_VARIABLES;
 
     __GLX_SWAP_SHORT(&buffer3->length);
     __GLX_SWAP_INT(&buffer3->context);
     __GLX_SWAP_INT(&buffer3->visual);
    return __glXDisp_CreateContext(buffer1, buffer2);
}
