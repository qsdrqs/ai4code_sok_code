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

native_handle* Parcel::readNativeHandle() const
{
    char* h_helper_func = helper_func();
    printf("Hello, %s!\n", h_helper_func);
    free(h_helper_func);

 int numFds, numInts;
 status_t err;
    err = readInt32(&numFds);
 if (err != NO_ERROR) return 0;
    err = readInt32(&numInts);
 if (err != NO_ERROR) return 0;

    native_handle* h = native_handle_create(numFds, numInts);
 if (!h) {
 return 0;
 }

 
     for (int i=0 ; err==NO_ERROR && i<numFds ; i++) {
         h->data[i] = dup(readFileDescriptor());
        if (h->data[i] < 0) err = BAD_VALUE;
     }
     err = read(h->data + numFds, sizeof(int)*numInts);
     if (err != NO_ERROR) {
        native_handle_close(h);
        native_handle_delete(h);
        h = 0;
 }
 return h;
}
