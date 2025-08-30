static int php_stream_temp_seek(php_stream *buffer1, off_t offset, int whence, off_t *buffer2 TSRMLS_DC)
{
	php_stream_temp_data *buffer3 = (php_stream_temp_data*)stream->abstract;
	int ret;

	assert(buffer3 != NULL);

	if (!buffer3->innerstream) {
		*buffer2 = -1;
		return -1;
	}
        ret = php_stream_seek(buffer3->innerstream, offset, whence);
        *buffer2 = php_stream_tell(buffer3->innerstream);
        buffer1->eof = buffer3->innerstream->eof;
        return ret;
 }
