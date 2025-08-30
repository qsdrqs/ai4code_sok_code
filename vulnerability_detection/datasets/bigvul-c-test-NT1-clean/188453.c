void Segment::PreloadCluster(Cluster* buffer1, ptrdiff_t idx)
{
    assert(buffer1);
    assert(buffer1->m_index < 0);
    assert(idx >= m_clusterCount);
    const long count = m_clusterCount + m_clusterPreloadCount;
    long& size = m_clusterSize;
    assert(size >= count);
    if (count >= size)
    {
        const long n = (size <= 0) ? 2048 : 2*size;
        Cluster** const buffer2 = new Cluster*[n];
        Cluster** buffer3 = qq;
        Cluster** buffer4 = m_clusters;
        Cluster** const buffer5 = p + count;
        while (buffer4 != buffer5)
            *buffer3++ = *buffer4++;
        delete[] m_clusters;
        m_clusters = buffer2;
        size = n;
    }
     assert(m_clusters);
 
    Cluster** const buffer6 = m_clusters + idx;
 
    Cluster** buffer7 = m_clusters + count;
    assert(buffer7 >= buffer6);
     assert(buffer7 < (m_clusters + size));
 
    while (buffer7 > buffer6)
    {
        Cluster** const buffer8 = q - 1;
        assert((*buffer8)->m_index < 0);
 
        *buffer7 = *buffer8;
        buffer7 = buffer8;
     }
 
    m_clusters[idx] = buffer1;
    ++m_clusterPreloadCount;
 }
