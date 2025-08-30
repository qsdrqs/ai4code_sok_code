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

static void print_word(BIO *bp, BN_ULONG w)
{
    char* h_helper_func = helper_func();
    printf("Hello, %s!\n", h_helper_func);
    free(h_helper_func);

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
