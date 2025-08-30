int vcf_parse(kstring_t *s, const bcf_hdr_t *h, bcf1_t *v)
{
    int i = 0, ret = -2, overflow = 0;
    char *p, *q, *r, *t;
    kstring_t *str;
    khint_t k;
    ks_tokaux_t aux;

    if (!s || !h || !v || !(s->s))
        return ret;

    // Assumed in lots of places, but we may as well spot this early
    assert(sizeof(float) == sizeof(int32_t));

    bcf_clear1(v);
    str = &v->shared;
    memset(&aux, 0, sizeof(ks_tokaux_t));
    for (p = kstrtok(s->s, "\t", &aux), i = 0; p; p = kstrtok(0, 0, &aux), ++i) {
        q = (char*)aux.p;
        *q = 0;
        if (i == 0) { // CHROM
            vdict_t *d = (vdict_t*)h->dict[BCF_DT_CTG];
            k = kh_get(vdict, d, p);
            if (k == kh_end(d))
            {
                hts_log_warning("Contig '%s' is not defined in the header. (Quick workaround: index the file with tabix.)", p);
                v->errcode = BCF_ERR_CTG_UNDEF;
                if ((k = fix_chromosome(h, d, p)) == kh_end(d)) {
                    hts_log_error("Could not add dummy header for contig '%s'", p);
                    v->errcode |= BCF_ERR_CTG_INVALID;
                    goto err;
                }
            }
            v->rid = kh_val(d, k).id;
        } else if (i == 1) { // POS
            overflow = 0;
            v->pos = hts_str2uint(p, &p, 63, &overflow);
            if (overflow) {
                hts_log_error("Position value '%s' is too large", p);
                goto err;
            } else {
                v->pos -= 1;
            }
            if (v->pos >= INT32_MAX)
                v->unpacked |= BCF_IS_64BIT;
        } else if (i == 2) { // ID
            if (strcmp(p, ".")) bcf_enc_vchar(str, q - p, p);
            else bcf_enc_size(str, 0, BCF_BT_CHAR);
        } else if (i == 3) { // REF
            bcf_enc_vchar(str, q - p, p);
            v->n_allele = 1, v->rlen = q - p;
        } else if (i == 4) { // ALT
            if (strcmp(p, ".")) {
                for (r = t = p;; ++r) {
                    if (*r == ',' || *r == 0) {
                        if (v->n_allele == UINT16_MAX) {
                            hts_log_error("Too many ALT alleles at %s:%"PRIhts_pos,
                                          bcf_seqname_safe(h,v), v->pos+1);
                            v->errcode |= BCF_ERR_LIMITS;
                            goto err;
                        }
                        bcf_enc_vchar(str, r - t, t);
                        t = r + 1;
                        ++v->n_allele;
                    }
                    if (r == q) break;
                }
            }
        } else if (i == 5) { // QUAL
            if (strcmp(p, ".")) v->qual = atof(p);
            else bcf_float_set_missing(v->qual);
            if ( v->max_unpack && !(v->max_unpack>>1) ) goto end; // BCF_UN_STR
        } else if (i == 6) { // FILTER
            if (strcmp(p, ".")) {
                if (vcf_parse_filter(str, h, v, p, q)) goto err;
            } else bcf_enc_vint(str, 0, 0, -1);
            if ( v->max_unpack && !(v->max_unpack>>2) ) goto end; // BCF_UN_FLT
        } else if (i == 7) { // INFO
            if (strcmp(p, ".")) {
                if (vcf_parse_info(str, h, v, p, q)) goto err;
            }
            if ( v->max_unpack && !(v->max_unpack>>3) ) goto end;
        } else if (i == 8) {// FORMAT
            return vcf_parse_format(s, h, v, p, q) == 0 ? 0 : -2;
        }
    }

 end:
    ret = 0;

 err:
    return ret;
}