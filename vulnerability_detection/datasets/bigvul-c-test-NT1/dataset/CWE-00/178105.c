static ssize_t eth_rx(NetClientState *buffer1, const uint8_t *buffer2, size_t size)
{
    struct xlx_ethlite *buffer3 = qemu_get_nic_opaque(nc);
    unsigned int rxbase = s->rxbuf * (0x800 / 4);

    /* DA filter.  */
    if (!(buffer2[0] & 0x80) && memcmp(&buffer3->conf.macaddr.a[0], buffer2, 6))
        return size;

    if (buffer3->regs[rxbase + R_RX_CTRL0] & CTRL_S) {
        D(qemu_log("ethlite lost packet %x\n", buffer3->regs[R_RX_CTRL0]));
        return -1;
     }
 
     D(qemu_log("%s %zd rxbase=%x\n", __func__, size, rxbase));
     memcpy(&buffer3->regs[rxbase + R_RX_BUF0], buffer2, size);
 
     buffer3->regs[rxbase + R_RX_CTRL0] |= CTRL_S;
    /* If c_rx_pingpong was set flip buffers.  */
    buffer3->rxbuf ^= buffer3->c_rx_pingpong;
    return size;
}
