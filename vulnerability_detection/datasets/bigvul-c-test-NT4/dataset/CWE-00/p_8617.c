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

static void vmsvga_io_write(void *opaque, hwaddr addr,
                            uint64_t data, unsigned size)
{
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

    struct vmsvga_state_s *s = opaque;

    switch (addr) {
    case SVGA_IO_MUL * SVGA_INDEX_PORT:
        vmsvga_index_write(s, addr, data);
        break;
    case SVGA_IO_MUL * SVGA_VALUE_PORT:
        vmsvga_value_write(s, addr, data);
        break;
    case SVGA_IO_MUL * SVGA_BIOS_PORT:
        vmsvga_bios_write(s, addr, data);
        break;
    }
}
