static void print_word(BIO *buffer1, BN_ULONG w)
{
#ifdef SIXTY_FOUR_BIT
    if (sizeof(w) > sizeof(unsigned long)) {
        unsigned long h = (unsigned long)(w >> 32), l = (unsigned long)(w);

        if (h)
            BIO_printf(buffer1, "%lX%08lX", h, l);
        else
            BIO_printf(buffer1, "%lX", l);
        return;
    }
#endif
    BIO_printf(buffer1, BN_HEX_FMT1, w);
}
