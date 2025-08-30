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

void Segment::PreloadCluster(Cluster* pCluster, ptrdiff_t idx)
{
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

    assert(pCluster);
    assert(pCluster->m_index < 0);
    assert(idx >= m_clusterCount);
    const long count = m_clusterCount + m_clusterPreloadCount;
    long& size = m_clusterSize;
    assert(size >= count);
    if (count >= size)
    {
        const long n = (size <= 0) ? 2048 : 2*size;
        Cluster** const qq = new Cluster*[n];
        Cluster** q = qq;
        Cluster** p = m_clusters;
        Cluster** const pp = p + count;
        while (p != pp)
            *q++ = *p++;
        delete[] m_clusters;
        m_clusters = qq;
        size = n;
    }
     assert(m_clusters);
 
    Cluster** const p = m_clusters + idx;
 
    Cluster** q = m_clusters + count;
    assert(q >= p);
     assert(q < (m_clusters + size));
 
    while (q > p)
    {
        Cluster** const qq = q - 1;
        assert((*qq)->m_index < 0);
 
        *q = *qq;
        q = qq;
     }
 
    m_clusters[idx] = pCluster;
    ++m_clusterPreloadCount;
 }
