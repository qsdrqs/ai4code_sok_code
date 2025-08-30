int Parcel::readFileDescriptor() const
{
 const flat_binder_object* flat = readObject(true);
 if (flat) {
 switch (flat->type) {
 case BINDER_TYPE_FD:
 return flat->handle;
 }
 }
 return BAD_TYPE;
}
