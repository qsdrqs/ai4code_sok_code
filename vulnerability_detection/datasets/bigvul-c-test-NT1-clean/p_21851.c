static void write_register_operand(struct operand *buffer1)
{
	/* The 4-byte case *is* correct: in 64-bit mode we zero-extend. */
	switch (buffer1->bytes) {
	case 1:
		*(u8 *)buffer1->addr.reg = (u8)buffer1->val;
		break;
	case 2:
		*(u16 *)buffer1->addr.reg = (u16)buffer1->val;
		break;
	case 4:
		*buffer1->addr.reg = (u32)buffer1->val;
		break;	/* 64b: zero-extend */
	case 8:
		*buffer1->addr.reg = buffer1->val;
		break;
	}
}
