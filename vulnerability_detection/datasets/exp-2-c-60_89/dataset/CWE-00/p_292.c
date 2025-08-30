void PackLinuxElf32mipsel::defineSymbols(Filter const *ft)
{
    PackLinuxElf32::defineSymbols(ft);

    unsigned const hlen = sz_elf_hdrs + sizeof(l_info) + sizeof(p_info);

    // We want to know if compressed data, plus stub, plus a couple pages,
    // will fit below the uncompressed program in memory.  But we don't
    // know the final total compressed size yet, so use the uncompressed
    // size (total over all PT_LOAD32) as an upper bound.
    unsigned len = 0;
    unsigned lo_va_user = ~0u;  // infinity
    for (int j= e_phnum; --j>=0; ) {
        if (PT_LOAD32 == get_te32(&phdri[j].p_type)) {
            len += (unsigned)get_te32(&phdri[j].p_filesz);
            unsigned const va = get_te32(&phdri[j].p_vaddr);
            if (va < lo_va_user) {
                lo_va_user = va;
            }
        }
    }
    lsize = /*getLoaderSize()*/  64 * 1024;  // XXX: upper bound; avoid circularity
    unsigned lo_va_stub = get_te32(&elfout.phdr[0].p_vaddr);
    unsigned adrc;
    unsigned adrm;
    unsigned adru;
    unsigned adrx;
    unsigned lenm;
    unsigned lenu;
    len += (7&-lsize) + lsize;
    is_big = (lo_va_user < (lo_va_stub + len + 2*page_size));
    if (is_big) {
        set_te32(    &elfout.ehdr.e_entry,
            get_te32(&elfout.ehdr.e_entry) + lo_va_user - lo_va_stub);
        set_te32(&elfout.phdr[0].p_vaddr, lo_va_user);
        set_te32(&elfout.phdr[0].p_paddr, lo_va_user);
               lo_va_stub      = lo_va_user;
        adrc = lo_va_stub;
        adrm = getbrk(phdri, e_phnum);
        adru = page_mask & (~page_mask + adrm);  // round up to page boundary
        adrx = adru + hlen;
        lenm = page_size + len;
        lenu = page_size + len;
    }
    else {
        adrm = lo_va_stub + len;
        adrc = adrm;
        adru = lo_va_stub;
        adrx = lo_va_stub + hlen;
        lenm = 2*page_size;
        lenu = 2*page_size + len;
    }
    adrm = page_mask & (~page_mask + adrm);  // round up to page boundary
    adrc = page_mask & (~page_mask + adrc);  // round up to page boundary

    linker->defineSymbol("ADRX", adrx); // compressed input for eXpansion

    linker->defineSymbol("ADRC", adrc);  // addr for copy
    linker->defineSymbol("LENU", lenu);  // len  for unmap
    linker->defineSymbol("ADRU", adru);  // addr for unmap
    linker->defineSymbol("LENM", lenm);  // len  for map
    linker->defineSymbol("ADRM", adrm);  // addr for map

    //linker->dumpSymbols();  // debug
}