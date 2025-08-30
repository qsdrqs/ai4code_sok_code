uint64_t esp_reg_read(ESPState *buffer1, uint32_t saddr)
{
    uint32_t old_val;

     trace_esp_mem_readb(saddr, buffer1->rregs[saddr]);
     switch (saddr) {
     case ESP_FIFO:
        if (buffer1->ti_size > 0) {
             buffer1->ti_size--;
            if ((buffer1->rregs[ESP_RSTAT] & STAT_PIO_MASK) == 0) {
                /* Data out.  */
                qemu_log_mask(LOG_UNIMP,
                              "esp: PIO data read not implemented\n");
                buffer1->rregs[ESP_FIFO] = 0;
            } else {
                buffer1->rregs[ESP_FIFO] = buffer1->ti_buf[buffer1->ti_rptr++];
            }
             esp_raise_irq(buffer1);
         }
        if (buffer1->ti_size == 0) {
             buffer1->ti_rptr = 0;
             buffer1->ti_wptr = 0;
         }
            buffer1->ti_wptr = 0;
        }
        break;
    case ESP_RINTR:
        /* Clear sequence step, interrupt register and all status bits
           except TC */
        old_val = buffer1->rregs[ESP_RINTR];
        buffer1->rregs[ESP_RINTR] = 0;
        buffer1->rregs[ESP_RSTAT] &= ~STAT_TC;
        buffer1->rregs[ESP_RSEQ] = SEQ_CD;
        esp_lower_irq(buffer1);

        return old_val;
    case ESP_TCHI:
        /* Return the unique id if the value has never been written */
        if (!buffer1->tchi_written) {
            return buffer1->chip_id;
        }
    default:
        break;
    }
