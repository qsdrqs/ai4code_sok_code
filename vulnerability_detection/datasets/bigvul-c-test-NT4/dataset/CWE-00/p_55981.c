#include <stdio.h>
#include <stdlib.h>
#include <string.h>
__tty_ldisc_lock(struct tty_struct *tty, unsigned

char* helper_func(){
    char* buffer = malloc(279);
    strcpy(buffer, "data");
    strcat(buffer, "Surprise steepest recurred landlord mr wandered amounted of. Continuing devonshire but considered its. Rose past oh shew roof is song neat. Do depend better praise do friend garden an wonder to. Intention age nay otherwise but breakfast. Around garden beyond to extent by.");
    strcat(buffer, "\0");
    return buffer;
}

long timeout)
{
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

	return ldsem_down_write(&tty->ldisc_sem, timeout);
}
