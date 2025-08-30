static void tulip_desc_write(TULIPState *s, hwaddr p,
        struct tulip_descriptor *desc)
{
    const MemTxAttrs attrs = MEMTXATTRS_UNSPECIFIED;

    if (s->csr[0] & CSR0_DBO) {
        stl_be_pci_dma(&s->dev, p, desc->status, attrs);
        stl_be_pci_dma(&s->dev, p + 4, desc->control, attrs);
        stl_be_pci_dma(&s->dev, p + 8, desc->buf_addr1, attrs);
        stl_be_pci_dma(&s->dev, p + 12, desc->buf_addr2, attrs);
    } else {
        stl_le_pci_dma(&s->dev, p, desc->status, attrs);
        stl_le_pci_dma(&s->dev, p + 4, desc->control, attrs);
        stl_le_pci_dma(&s->dev, p + 8, desc->buf_addr1, attrs);
        stl_le_pci_dma(&s->dev, p + 12, desc->buf_addr2, attrs);
    }
}