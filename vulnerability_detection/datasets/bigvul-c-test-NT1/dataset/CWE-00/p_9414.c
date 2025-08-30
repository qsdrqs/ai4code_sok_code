static void mdc2_body(MDC2_CTX *buffer1, const unsigned char *buffer2, size_t len)
{
    register DES_LONG tin0, tin1;
    register DES_LONG ttin0, ttin1;
    DES_LONG buffer3[2], buffer4[2];
    DES_key_schedule k;
    unsigned char *buffer5;
    size_t i;

    for (i = 0; i < len; i += 8) {
        c2l(buffer2, tin0);
        buffer3[0] = buffer4[0] = tin0;
        c2l(buffer2, tin1);
        buffer3[1] = buffer4[1] = tin1;
        buffer1->h[0] = (buffer1->h[0] & 0x9f) | 0x40;
        buffer1->hh[0] = (buffer1->hh[0] & 0x9f) | 0x20;

        DES_set_odd_parity(&buffer1->h);
        DES_set_key_unchecked(&buffer1->h, &k);
        DES_encrypt1(buffer3, &k, 1);

        DES_set_odd_parity(&buffer1->hh);
        DES_set_key_unchecked(&buffer1->hh, &k);
        DES_encrypt1(buffer4, &k, 1);

        ttin0 = tin0 ^ buffer4[0];
        ttin1 = tin1 ^ buffer4[1];
        tin0 ^= buffer3[0];
        tin1 ^= buffer3[1];

        buffer5 = buffer1->h;
        l2c(tin0, buffer5);
        l2c(ttin1, buffer5);
        buffer5 = buffer1->hh;
        l2c(ttin0, buffer5);
        l2c(tin1, buffer5);
    }
}
