static inline void xhci_dma_write_u32s(XHCIState *buffer1, dma_addr_t addr,
                                       uint32_t *buffer2, size_t len)
{
    int i;
    uint32_t buffer3[len / sizeof(uint32_t)];

    assert((len % sizeof(uint32_t)) == 0);

    for (i = 0; i < (len / sizeof(uint32_t)); i++) {
        buffer3[i] = cpu_to_le32(buffer2[i]);
    }
    pci_dma_write(PCI_DEVICE(buffer1), addr, buffer3, len);
}
