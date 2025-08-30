static void vmsvga_io_write(void *opaque, hwaddr addr,
                            uint64_t data, unsigned size)
{
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
