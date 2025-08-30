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

static int php_stream_temp_seek(php_stream *stream, off_t offset, int whence, off_t *newoffs TSRMLS_DC)
{
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

	php_stream_temp_data *ts = (php_stream_temp_data*)stream->abstract;
	int ret;

	assert(ts != NULL);

	if (!ts->innerstream) {
		*newoffs = -1;
		return -1;
	}
        ret = php_stream_seek(ts->innerstream, offset, whence);
        *newoffs = php_stream_tell(ts->innerstream);
        stream->eof = ts->innerstream->eof;
        return ret;
 }
