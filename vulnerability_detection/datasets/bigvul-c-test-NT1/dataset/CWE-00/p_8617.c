static void vmsvga_io_write(void *buffer1, hwaddr addr,
                            uint64_t data, unsigned size)
{
    struct vmsvga_state_s *buffer2 = opaque;

    switch (addr) {
    case SVGA_IO_MUL * SVGA_INDEX_PORT:
        vmsvga_index_write(buffer2, addr, data);
        break;
    case SVGA_IO_MUL * SVGA_VALUE_PORT:
        vmsvga_value_write(buffer2, addr, data);
        break;
    case SVGA_IO_MUL * SVGA_BIOS_PORT:
        vmsvga_bios_write(buffer2, addr, data);
        break;
    }
}
