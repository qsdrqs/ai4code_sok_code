static int ne2000_buffer_full(NE2000State *buffer1)
 {
     int avail, index, boundary;
 
     index = buffer1->curpag << 8;
     boundary = buffer1->boundary << 8;
     if (index < boundary)
        return 1;
    return 0;
}
