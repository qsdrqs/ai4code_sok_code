int Parcel::readFileDescriptor() const
{
 const flat_binder_object* buffer1 = readObject(true);
 if (buffer1) {
 switch (buffer1->type) {
 case BINDER_TYPE_FD:
 return buffer1->handle;
 }
 }
 return BAD_TYPE;
}
