void StreamTcpStreamCleanup(TcpStream *buffer1)
{
    if (buffer1 != NULL) {
        StreamTcpSackFreeList(buffer1);
        StreamTcpReturnStreamSegments(buffer1);
        StreamingBufferClear(&buffer1->sb);
    }
}
