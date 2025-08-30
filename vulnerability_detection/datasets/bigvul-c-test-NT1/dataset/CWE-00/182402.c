int wasm_dis(WasmOp *buffer1, const unsigned char *buffer2, int buf_len) {
	buffer1->len = 1;
	buffer1->op = buffer2[0];
	if (buffer1->op > 0xbf) {
		return 1;
	}
	WasmOpDef *buffer3 = &opcodes[op->op];
	switch (buffer1->op) {
	case WASM_OP_TRAP:
	case WASM_OP_NOP:
	case WASM_OP_ELSE:
	case WASM_OP_RETURN:
	case WASM_OP_DROP:
	case WASM_OP_SELECT:
	case WASM_OP_I32EQZ:
	case WASM_OP_I32EQ:
	case WASM_OP_I32NE:
	case WASM_OP_I32LTS:
	case WASM_OP_I32LTU:
	case WASM_OP_I32GTS:
	case WASM_OP_I32GTU:
	case WASM_OP_I32LES:
	case WASM_OP_I32LEU:
	case WASM_OP_I32GES:
	case WASM_OP_I32GEU:
	case WASM_OP_I64EQZ:
	case WASM_OP_I64EQ:
	case WASM_OP_I64NE:
	case WASM_OP_I64LTS:
	case WASM_OP_I64LTU:
	case WASM_OP_I64GTS:
	case WASM_OP_I64GTU:
	case WASM_OP_I64LES:
	case WASM_OP_I64LEU:
	case WASM_OP_I64GES:
	case WASM_OP_I64GEU:
	case WASM_OP_F32EQ:
	case WASM_OP_F32NE:
	case WASM_OP_F32LT:
	case WASM_OP_F32GT:
	case WASM_OP_F32LE:
	case WASM_OP_F32GE:
	case WASM_OP_F64EQ:
	case WASM_OP_F64NE:
	case WASM_OP_F64LT:
	case WASM_OP_F64GT:
	case WASM_OP_F64LE:
	case WASM_OP_F64GE:
	case WASM_OP_I32CLZ:
	case WASM_OP_I32CTZ:
	case WASM_OP_I32POPCNT:
	case WASM_OP_I32ADD:
	case WASM_OP_I32SUB:
	case WASM_OP_I32MUL:
	case WASM_OP_I32DIVS:
	case WASM_OP_I32DIVU:
	case WASM_OP_I32REMS:
	case WASM_OP_I32REMU:
	case WASM_OP_I32AND:
	case WASM_OP_I32OR:
	case WASM_OP_I32XOR:
	case WASM_OP_I32SHL:
	case WASM_OP_I32SHRS:
	case WASM_OP_I32SHRU:
	case WASM_OP_I32ROTL:
	case WASM_OP_I32ROTR:
	case WASM_OP_I64CLZ:
	case WASM_OP_I64CTZ:
	case WASM_OP_I64POPCNT:
	case WASM_OP_I64ADD:
	case WASM_OP_I64SUB:
	case WASM_OP_I64MUL:
	case WASM_OP_I64DIVS:
	case WASM_OP_I64DIVU:
	case WASM_OP_I64REMS:
	case WASM_OP_I64REMU:
	case WASM_OP_I64AND:
	case WASM_OP_I64OR:
	case WASM_OP_I64XOR:
	case WASM_OP_I64SHL:
	case WASM_OP_I64SHRS:
	case WASM_OP_I64SHRU:
	case WASM_OP_I64ROTL:
	case WASM_OP_I64ROTR:
	case WASM_OP_F32ABS:
	case WASM_OP_F32NEG:
	case WASM_OP_F32CEIL:
	case WASM_OP_F32FLOOR:
	case WASM_OP_F32TRUNC:
	case WASM_OP_F32NEAREST:
	case WASM_OP_F32SQRT:
	case WASM_OP_F32ADD:
	case WASM_OP_F32SUB:
	case WASM_OP_F32MUL:
	case WASM_OP_F32DIV:
	case WASM_OP_F32MIN:
	case WASM_OP_F32MAX:
	case WASM_OP_F32COPYSIGN:
	case WASM_OP_F64ABS:
	case WASM_OP_F64NEG:
	case WASM_OP_F64CEIL:
	case WASM_OP_F64FLOOR:
	case WASM_OP_F64TRUNC:
	case WASM_OP_F64NEAREST:
	case WASM_OP_F64SQRT:
	case WASM_OP_F64ADD:
	case WASM_OP_F64SUB:
	case WASM_OP_F64MUL:
	case WASM_OP_F64DIV:
	case WASM_OP_F64MIN:
	case WASM_OP_F64MAX:
	case WASM_OP_F64COPYSIGN:
	case WASM_OP_I32WRAPI64:
	case WASM_OP_I32TRUNCSF32:
	case WASM_OP_I32TRUNCUF32:
	case WASM_OP_I32TRUNCSF64:
	case WASM_OP_I32TRUNCUF64:
	case WASM_OP_I64EXTENDSI32:
	case WASM_OP_I64EXTENDUI32:
	case WASM_OP_I64TRUNCSF32:
	case WASM_OP_I64TRUNCUF32:
	case WASM_OP_I64TRUNCSF64:
	case WASM_OP_I64TRUNCUF64:
	case WASM_OP_F32CONVERTSI32:
	case WASM_OP_F32CONVERTUI32:
	case WASM_OP_F32CONVERTSI64:
	case WASM_OP_F32CONVERTUI64:
	case WASM_OP_F32DEMOTEF64:
	case WASM_OP_F64CONVERTSI32:
	case WASM_OP_F64CONVERTUI32:
	case WASM_OP_F64CONVERTSI64:
	case WASM_OP_F64CONVERTUI64:
	case WASM_OP_F64PROMOTEF32:
	case WASM_OP_I32REINTERPRETF32:
	case WASM_OP_I64REINTERPRETF64:
	case WASM_OP_F32REINTERPRETI32:
	case WASM_OP_F64REINTERPRETI64:
	case WASM_OP_END:
		{
			snprintf (buffer1->txt, R_ASM_BUFSIZE, "%s", buffer3->txt);
		}
		break;
	case WASM_OP_BLOCK:
	case WASM_OP_LOOP:
	case WASM_OP_IF:
		{
			st32 val = 0;
			size_t n = read_i32_leb128 (buf + 1, buf + buf_len, &val);
			if (!(n > 0 && n < buf_len)) goto err;
			switch (0x80 - val) {
			case R_BIN_WASM_VALUETYPE_EMPTY:
				snprintf (buffer1->txt, R_ASM_BUFSIZE, "%s", buffer3->txt);
				break;
			case R_BIN_WASM_VALUETYPE_i32:
				snprintf (buffer1->txt, R_ASM_BUFSIZE, "%s (result i32)", buffer3->txt);
				break;
			case R_BIN_WASM_VALUETYPE_i64:
				snprintf (buffer1->txt, R_ASM_BUFSIZE, "%s (result i64)", buffer3->txt);
				break;
			case R_BIN_WASM_VALUETYPE_f32:
				snprintf (buffer1->txt, R_ASM_BUFSIZE, "%s (result f32)", buffer3->txt);
				break;
			case R_BIN_WASM_VALUETYPE_f64:
				snprintf (buffer1->txt, R_ASM_BUFSIZE, "%s (result f64)", buffer3->txt);
				break;
			default:
				snprintf (buffer1->txt, R_ASM_BUFSIZE, "%s (result ?)", buffer3->txt);
				break;
			}
			buffer1->len += n;
		}
		break;
	case WASM_OP_BR:
	case WASM_OP_BRIF:
	case WASM_OP_CALL:
		{
			ut32 val = 0;
			size_t n = read_u32_leb128 (buf + 1, buf + buf_len, &val);
			if (!(n > 0 && n < buf_len)) goto err;
			snprintf (buffer1->txt, R_ASM_BUFSIZE, "%s %d", buffer3->txt, val);
			buffer1->len += n;
		}
		break;
	case WASM_OP_BRTABLE:
		{
			ut32 count = 0, *buffer4 = NULL, def = 0;
			size_t n = read_u32_leb128 (buf + 1, buf + buf_len, &count);
			if (!(n > 0 && n < buf_len)) {
				goto err;
			}
			if (!(buffer4 = calloc (count, sizeof (ut32)))) {
				goto err;
			}
			int i = 0;
			buffer1->len += n;
			for (i = 0; i < count; i++) {
				n = read_u32_leb128 (buffer2 + buffer1->len, buffer2 + buf_len, &buffer4[i]);
				if (!(buffer1->len + n <= buf_len)) {
					goto beach;
				}
				buffer1->len += n;
			}
			n = read_u32_leb128 (buffer2 + buffer1->len, buffer2 + buf_len, &def);
			if (!(n > 0 && n + buffer1->len < buf_len)) {
				goto beach;
 			}
 			buffer1->len += n;
 			snprintf (buffer1->txt, R_ASM_BUFSIZE, "%s %d ", buffer3->txt, count);
			for (i = 0; i < count && strlen (buffer1->txt) + 10 < R_ASM_BUFSIZE; i++) {
				int optxtlen = strlen (op->txt);
				snprintf (buffer1->txt + optxtlen, R_ASM_BUFSIZE - optxtlen, "%d ", buffer4[i]);
 			}
			snprintf (buffer1->txt + strlen (buffer1->txt), R_ASM_BUFSIZE, "%d", def);
 			free (buffer4);
 			break;
			beach:
 			free (buffer4);
 			goto err;
 		}
		break;
	case WASM_OP_CALLINDIRECT:
		{
			ut32 val = 0, reserved = 0;
			size_t n = read_u32_leb128 (buf + 1, buf + buf_len, &val);
			if (!(n > 0 && n < buf_len)) goto err;
			buffer1->len += n;
			n = read_u32_leb128 (buffer2 + buffer1->len, buffer2 + buf_len, &reserved);
			if (!(n == 1 && buffer1->len + n <= buf_len)) goto err;
			reserved &= 0x1;
			snprintf (buffer1->txt, R_ASM_BUFSIZE, "%s %d %d", buffer3->txt, val, reserved);
			buffer1->len += n;
		}
		break;
	case WASM_OP_GETLOCAL:
	case WASM_OP_SETLOCAL:
	case WASM_OP_TEELOCAL:
	case WASM_OP_GETGLOBAL:
	case WASM_OP_SETGLOBAL:
		{
			ut32 val = 0;
			size_t n = read_u32_leb128 (buf + 1, buf + buf_len, &val);
			if (!(n > 0 && n < buf_len)) goto err;
			snprintf (buffer1->txt, R_ASM_BUFSIZE, "%s %d", buffer3->txt, val);
			buffer1->len += n;
		}
		break;
	case WASM_OP_I32LOAD:
	case WASM_OP_I64LOAD:
	case WASM_OP_F32LOAD:
	case WASM_OP_F64LOAD:
	case WASM_OP_I32LOAD8S:
	case WASM_OP_I32LOAD8U:
	case WASM_OP_I32LOAD16S:
	case WASM_OP_I32LOAD16U:
	case WASM_OP_I64LOAD8S:
	case WASM_OP_I64LOAD8U:
	case WASM_OP_I64LOAD16S:
	case WASM_OP_I64LOAD16U:
	case WASM_OP_I64LOAD32S:
	case WASM_OP_I64LOAD32U:
	case WASM_OP_I32STORE:
	case WASM_OP_I64STORE:
	case WASM_OP_F32STORE:
	case WASM_OP_F64STORE:
	case WASM_OP_I32STORE8:
	case WASM_OP_I32STORE16:
	case WASM_OP_I64STORE8:
	case WASM_OP_I64STORE16:
	case WASM_OP_I64STORE32:
		{
			ut32 flag = 0, offset = 0;
			size_t n = read_u32_leb128 (buf + 1, buf + buf_len, &flag);
			if (!(n > 0 && n < buf_len)) goto err;
			buffer1->len += n;
			n = read_u32_leb128 (buffer2 + buffer1->len, buffer2 + buf_len, &offset);
			if (!(n > 0 && buffer1->len + n <= buf_len)) goto err;
			snprintf (buffer1->txt, R_ASM_BUFSIZE, "%s %d %d", buffer3->txt, flag, offset);
			buffer1->len += n;
		}
		break;
	case WASM_OP_CURRENTMEMORY:
	case WASM_OP_GROWMEMORY:
		{
			ut32 reserved = 0;
			size_t n = read_u32_leb128 (buf + 1, buf + buf_len, &reserved);
			if (!(n == 1 && n < buf_len)) goto err;
			reserved &= 0x1;
			snprintf (buffer1->txt, R_ASM_BUFSIZE, "%s %d", buffer3->txt, reserved);
			buffer1->len += n;
		}
		break;

	case WASM_OP_I32CONST:
		{
			st32 val = 0;
			size_t n = read_i32_leb128 (buf + 1, buf + buf_len, &val);
			if (!(n > 0 && n < buf_len)) goto err;
			snprintf (buffer1->txt, R_ASM_BUFSIZE, "%s %" PFMT32d, buffer3->txt, val);
			buffer1->len += n;
		}
		break;
	case WASM_OP_I64CONST:
		{
			st64 val = 0;
			size_t n = read_i64_leb128 (buf + 1, buf + buf_len, &val);
			if (!(n > 0 && n < buf_len)) goto err;
			snprintf (buffer1->txt, R_ASM_BUFSIZE, "%s %" PFMT64d, buffer3->txt, val);
			buffer1->len += n;
		}
		break;
	case WASM_OP_F32CONST:
		{
			ut32 val = 0;
			size_t n = read_u32_leb128 (buf + 1, buf + buf_len, &val);
			if (!(n > 0 && n < buf_len)) goto err;
			long double d =  (long double)val;
			snprintf (buffer1->txt, R_ASM_BUFSIZE, "%s %" LDBLFMT, buffer3->txt, d);
			buffer1->len += n;
		}
		break;
	case WASM_OP_F64CONST:
		{
			ut64 val = 0;
			size_t n = read_u64_leb128 (buf + 1, buf + buf_len, &val);
			if (!(n > 0 && n < buf_len)) goto err;
			long double d =  (long double)val;
			snprintf (buffer1->txt, R_ASM_BUFSIZE, "%s %" LDBLFMT, buffer3->txt, d);
			buffer1->len += n;
		}
		break;
	default:
		goto err;
	}

	return buffer1->len;

err:
	buffer1->len = 1;
	snprintf (buffer1->txt, R_ASM_BUFSIZE, "invalid");
	return buffer1->len;
}
