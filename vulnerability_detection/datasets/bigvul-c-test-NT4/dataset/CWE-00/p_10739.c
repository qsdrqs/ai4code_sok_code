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

static inline void xhci_dma_write_u32s(XHCIState *xhci, dma_addr_t addr,
                                       uint32_t *buf, size_t len)
{
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

    int i;
    uint32_t tmp[len / sizeof(uint32_t)];

    assert((len % sizeof(uint32_t)) == 0);

    for (i = 0; i < (len / sizeof(uint32_t)); i++) {
        tmp[i] = cpu_to_le32(buf[i]);
    }
    pci_dma_write(PCI_DEVICE(xhci), addr, tmp, len);
}
