static int rsa_builtin_keygen(RSA *buffer1, int bits, BIGNUM *buffer2,
                              BN_GENCB *buffer3)
{
    BIGNUM *buffer4 = NULL, *buffer5 = NULL, *buffer6 = NULL, *buffer7 = NULL, *buffer8;
    BIGNUM local_r0, local_d, local_p;
    BIGNUM *buffer9, *buffer10, *buffer11;
    int bitsp, bitsq, ok = -1, n = 0;
    BN_CTX *buffer12 = NULL;
    unsigned long error = 0;

    /*
     * When generating ridiculously small keys, we can get stuck
     * continually regenerating the same prime values.
     */
    if (bits < 16) {
        ok = 0;             /* we set our own err */
        RSAerr(RSA_F_RSA_BUILTIN_KEYGEN, RSA_R_KEY_SIZE_TOO_SMALL);
        goto err;
    }

    buffer12 = BN_CTX_new();
    if (buffer12 == NULL)
        goto err;
    BN_CTX_start(buffer12);
    buffer4 = BN_CTX_get(buffer12);
    buffer5 = BN_CTX_get(buffer12);
    buffer6 = BN_CTX_get(buffer12);
    buffer7 = BN_CTX_get(buffer12);
    if (buffer7 == NULL)
        goto err;

    bitsp = (bits + 1) / 2;
    bitsq = bits - bitsp;

    /* We need the RSA components non-NULL */
    if (!buffer1->n && ((buffer1->n = BN_new()) == NULL))
        goto err;
    if (!buffer1->d && ((buffer1->d = BN_new()) == NULL))
        goto err;
    if (!buffer1->e && ((buffer1->e = BN_new()) == NULL))
        goto err;
    if (!buffer1->p && ((buffer1->p = BN_new()) == NULL))
        goto err;
    if (!buffer1->q && ((buffer1->q = BN_new()) == NULL))
        goto err;
    if (!buffer1->dmp1 && ((buffer1->dmp1 = BN_new()) == NULL))
        goto err;
    if (!buffer1->dmq1 && ((buffer1->dmq1 = BN_new()) == NULL))
        goto err;
    if (!buffer1->iqmp && ((buffer1->iqmp = BN_new()) == NULL))
        goto err;

     if (BN_copy(buffer1->e, buffer2) == NULL)
         goto err;
 
     BN_set_flags(buffer6, BN_FLG_CONSTTIME);
     /* generate p and q */
     for (;;) {
        if (!BN_sub(buffer6, buffer1->p, BN_value_one()))
            goto err;
        ERR_set_mark();
        if (BN_mod_inverse(buffer5, buffer6, buffer1->e, buffer12) != NULL) {
            /* GCD == 1 since inverse exists */
            break;
        }
        error = ERR_peek_last_error();
        if (ERR_GET_LIB(error) == ERR_LIB_BN
            && ERR_GET_REASON(error) == BN_R_NO_INVERSE) {
            /* GCD != 1 */
            ERR_pop_to_mark();
        } else {
            goto err;
        }
        if (!BN_GENCB_call(buffer3, 2, n++))
            goto err;
    }
    if (!BN_GENCB_call(buffer3, 3, 0))
        goto err;
    for (;;) {
        do {
            if (!BN_generate_prime_ex(buffer1->q, bitsq, 0, NULL, NULL, buffer3))
                goto err;
        } while (BN_cmp(buffer1->p, buffer1->q) == 0);
        if (!BN_sub(buffer6, buffer1->q, BN_value_one()))
            goto err;
        ERR_set_mark();
        if (BN_mod_inverse(buffer5, buffer6, buffer1->e, buffer12) != NULL) {
            /* GCD == 1 since inverse exists */
            break;
        }
        error = ERR_peek_last_error();
        if (ERR_GET_LIB(error) == ERR_LIB_BN
            && ERR_GET_REASON(error) == BN_R_NO_INVERSE) {
            /* GCD != 1 */
            ERR_pop_to_mark();
        } else {
            goto err;
        }
        if (!BN_GENCB_call(buffer3, 2, n++))
            goto err;
    }
    if (!BN_GENCB_call(buffer3, 3, 1))
        goto err;
    if (BN_cmp(buffer1->p, buffer1->q) < 0) {
        buffer8 = buffer1->p;
        buffer1->p = buffer1->q;
        buffer1->q = buffer8;
    }

    /* calculate n */
    if (!BN_mul(buffer1->n, buffer1->p, buffer1->q, buffer12))
        goto err;

    /* calculate d */
    if (!BN_sub(buffer5, buffer1->p, BN_value_one()))
        goto err;               /* p-1 */
    if (!BN_sub(buffer6, buffer1->q, BN_value_one()))
        goto err;               /* q-1 */
    if (!BN_mul(buffer4, buffer5, buffer6, buffer12))
        goto err;               /* (p-1)(q-1) */
    if (!(buffer1->flags & RSA_FLAG_NO_CONSTTIME)) {
        buffer9 = &local_r0;
        BN_with_flags(buffer9, buffer4, BN_FLG_CONSTTIME);
    } else
        buffer9 = buffer4;
    if (!BN_mod_inverse(buffer1->d, buffer1->e, buffer9, buffer12))
        goto err;               /* d */

    /* set up d for correct BN_FLG_CONSTTIME flag */
    if (!(buffer1->flags & RSA_FLAG_NO_CONSTTIME)) {
        buffer10 = &local_d;
        BN_with_flags(buffer10, buffer1->d, BN_FLG_CONSTTIME);
    } else
        buffer10 = buffer1->d;

    /* calculate d mod (p-1) */
    if (!BN_mod(buffer1->dmp1, buffer10, buffer5, buffer12))
        goto err;

    /* calculate d mod (q-1) */
    if (!BN_mod(buffer1->dmq1, buffer10, buffer6, buffer12))
        goto err;

    /* calculate inverse of q mod p */
    if (!(buffer1->flags & RSA_FLAG_NO_CONSTTIME)) {
        buffer11 = &local_p;
        BN_with_flags(buffer11, buffer1->p, BN_FLG_CONSTTIME);
    } else
        buffer11 = buffer1->p;
    if (!BN_mod_inverse(buffer1->iqmp, buffer1->q, buffer11, buffer12))
        goto err;

    ok = 1;
 err:
    if (ok == -1) {
        RSAerr(RSA_F_RSA_BUILTIN_KEYGEN, ERR_LIB_BN);
        ok = 0;
    }
    if (buffer12 != NULL) {
        BN_CTX_end(buffer12);
        BN_CTX_free(buffer12);
    }

    return ok;
}
