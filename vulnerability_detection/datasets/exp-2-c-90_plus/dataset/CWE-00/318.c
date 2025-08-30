static void pdf_handle_enc(struct pdf_struct *pdf)
{
    struct pdf_obj *obj;
    uint32_t len, required_flags, n, R, P, length, EM, i, oulen;
    char *O, *U;
    const char *q, *q2;

    if (pdf->enc_objid == ~0u)
	return;
    if (!pdf->fileID) {
	cli_dbgmsg("cli_pdf: pdf_handle_enc no file ID\n");
	return;
    }
    obj = find_obj(pdf, pdf->objs, pdf->enc_objid);
    if (!obj) {
	cli_dbgmsg("cli_pdf: can't find encrypted object %d %d\n", pdf->enc_objid>>8, pdf->enc_objid&0xff);
	return;
    }
    len = obj_size(pdf, obj, 1);
    q = pdf->map + obj->start;

    O = U = NULL;
    do {
	EM = pdf_readbool(q, len, "/EncryptMetadata", 1);
	P = pdf_readint(q, len, "/P");
	if (P == ~0u) {
	    cli_dbgmsg("cli_pdf: invalid P\n");
	    break;
	}

	q2 = cli_memstr(q, len, "/Standard", 9);
	if (!q2) {
	    cli_dbgmsg("cli_pdf: /Standard not found\n");
	    break;
	}
	/* we can have both of these:
	* /AESV2/Length /Standard/Length
	* /Length /Standard
	* make sure we don't mistake AES's length for Standard's */
	length = pdf_readint(q2, len - (q2 - q), "/Length");
	if (length == ~0u)
	    length = pdf_readint(q, len, "/Length");
	if (length == ~0u)
	    length = 40;
	if (length < 40) {
	    cli_dbgmsg("cli_pdf: invalid length: %d\n", length);
	    length = 40;
	}

	R = pdf_readint(q, len, "/R");
	if (R == ~0u) {
	    cli_dbgmsg("cli_pdf: invalid R\n");
	    break;
	}

	if (R < 5)
	    oulen = 32;
	else
	    oulen = 48;

	n = 0;
	O = pdf_readstring(q, len, "/O", &n);
	if (!O || n < oulen) {
	    cli_dbgmsg("cli_pdf: invalid O: %d\n", n);
	    if (O)
		dbg_printhex("invalid O", O, n);
	    break;
	}
	if (n > oulen) {
	    for (i=oulen;i<n;i++)
		if (O[i])
		    break;
	    if (i != n) {
		dbg_printhex("too long O", O, n);
		break;
	    }
	}

	n = 0;
	U = pdf_readstring(q, len, "/U", &n);
	if (!U || n < oulen) {
	    cli_dbgmsg("cli_pdf: invalid U: %d\n", n);
	    if (U)
		dbg_printhex("invalid U", U, n);
	    break;
	}
	if (n > oulen) {
	    for (i=oulen;i<n;i++)
		if (U[i])
		    break;
	    if (i != n) {
		dbg_printhex("too long U", U, n);
		break;
	    }
	}
	cli_dbgmsg("cli_pdf: Encrypt R: %d, P %x, length: %d\n", R, P, length);
	if (length % 8) {
	    cli_dbgmsg("cli_pdf: wrong key length, not multiple of 8\n");
	    break;
	}
	check_user_password(pdf, R, O, U, P, EM, length, oulen);
    } while (0);
    free(O);
    free(U);
}