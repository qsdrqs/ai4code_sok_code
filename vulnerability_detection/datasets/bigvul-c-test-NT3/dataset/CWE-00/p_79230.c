void vulnerable_func(TcpStream *stream)
{
    if (stream != NULL) {
        StreamTcpSackFreeList(stream);
        StreamTcpReturnStreamSegments(stream);
        StreamingBufferClear(&stream->sb);
    }
}
