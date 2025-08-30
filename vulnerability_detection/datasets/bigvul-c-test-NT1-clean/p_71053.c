void Type_Signature_Free(struct _cms_typehandler_struct* buffer1, void* buffer2)
{
    _cmsFree(buffer1 ->ContextID, buffer2);
}
