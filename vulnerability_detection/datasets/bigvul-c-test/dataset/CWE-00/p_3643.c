static void print_word(BIO *bp, BN_ULONG w)
{
#ifdef SIXTY_FOUR_BIT
    if (sizeof(w) > sizeof(unsigned long)) {
        unsigned long h = (unsigned long)(w >> 32), l = (unsigned long)(w);

        if (h)
            BIO_printf(bp, "%lX%08lX", h, l);
        else
            BIO_printf(bp, "%lX", l);
        return;
    }
#endif
    BIO_printf(bp, BN_HEX_FMT1, w);
}
