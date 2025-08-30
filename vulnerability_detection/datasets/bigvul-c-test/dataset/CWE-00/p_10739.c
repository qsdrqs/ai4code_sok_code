static inline void xhci_dma_write_u32s(XHCIState *xhci, dma_addr_t addr,
                                       uint32_t *buf, size_t len)
{
    int i;
    uint32_t tmp[len / sizeof(uint32_t)];

    assert((len % sizeof(uint32_t)) == 0);

    for (i = 0; i < (len / sizeof(uint32_t)); i++) {
        tmp[i] = cpu_to_le32(buf[i]);
    }
    pci_dma_write(PCI_DEVICE(xhci), addr, tmp, len);
}
