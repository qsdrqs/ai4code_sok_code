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

status_t SampleTable::setTimeToSampleParams(
         off64_t data_offset, size_t data_size) {
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

    if (!mTimeToSample.empty() || data_size < 8) {
         return ERROR_MALFORMED;
     }
 
 uint8_t header[8];
 if (mDataSource->readAt(
                data_offset, header, sizeof(header)) < (ssize_t)sizeof(header)) {
 return ERROR_IO;
 }

 if (U32_AT(header) != 0) {
 return ERROR_MALFORMED;
 }

    mTimeToSampleCount = U32_AT(&header[4]);
 if ((uint64_t)mTimeToSampleCount >
 (uint64_t)UINT32_MAX / (2 * sizeof(uint32_t))) {
        ALOGE("  Error: Time-to-sample table size too large.");

 return ERROR_OUT_OF_RANGE;
 }

 if (!mDataSource->getVector(data_offset + 8, &mTimeToSample,
                                mTimeToSampleCount * 2)) {
        ALOGE("  Error: Incomplete data read for time-to-sample table.");
 return ERROR_IO;
 }


     for (size_t i = 0; i < mTimeToSample.size(); ++i) {
         mTimeToSample.editItemAt(i) = ntohl(mTimeToSample[i]);
     }
     return OK;
 }
