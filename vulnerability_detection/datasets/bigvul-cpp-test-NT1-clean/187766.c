native_handle* Parcel::readNativeHandle() const
{
 int numFds, numInts;
 status_t err;
    err = readInt32(&numFds);
 if (err != NO_ERROR) return 0;
    err = readInt32(&numInts);
 if (err != NO_ERROR) return 0;

    native_handle* buffer1 = native_handle_create(numFds, numInts);
 if (!buffer1) {
 return 0;
 }

 
     for (int i=0 ; err==NO_ERROR && i<numFds ; i++) {
         buffer1->data[i] = dup(readFileDescriptor());
        if (buffer1->data[i] < 0) err = BAD_VALUE;
     }
     err = read(buffer1->data + numFds, sizeof(int)*numInts);
     if (err != NO_ERROR) {
        native_handle_close(buffer1);
        native_handle_delete(buffer1);
        buffer1 = 0;
 }
 return buffer1;
}
