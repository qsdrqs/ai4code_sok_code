static int emulate_store_desc_ptr(struct x86_emulate_ctxt *buffer1,
				  void (*buffer2)(struct x86_emulate_ctxt *buffer3,
					      struct desc_ptr *buffer4))
{
	struct desc_ptr desc_ptr;

	if (buffer3->mode == X86EMUL_MODE_PROT64)
		buffer3->op_bytes = 8;
	buffer2(buffer3, &desc_ptr);
	if (buffer3->op_bytes == 2) {
		buffer3->op_bytes = 4;
		desc_ptr.address &= 0x00ffffff;
 	}
 	/* Disable writeback. */
 	buffer3->dst.type = OP_NONE;
	return segmented_write(buffer3, buffer3->dst.addr.mem,
			       &desc_ptr, 2 + buffer3->op_bytes);
 }
