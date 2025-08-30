int ssl3_get_req_cert_type(SSL *s, unsigned char *p)
	{
	int ret=0;
	unsigned long alg_k;

	alg_k = s->s3->tmp.new_cipher->algorithm_mkey;

#ifndef OPENSSL_NO_GOST
	if (s->version >= TLS1_VERSION)
		{
		if (alg_k & SSL_kGOST)
			{
			p[ret++]=TLS_CT_GOST94_SIGN;
			p[ret++]=TLS_CT_GOST01_SIGN;
			return(ret);
			}
		}
#endif

#ifndef OPENSSL_NO_DH
	if (alg_k & (SSL_kDHr|SSL_kEDH))
		{
#  ifndef OPENSSL_NO_RSA
		p[ret++]=SSL3_CT_RSA_FIXED_DH;
#  endif
#  ifndef OPENSSL_NO_DSA
		p[ret++]=SSL3_CT_DSS_FIXED_DH;
#  endif
		}
	if ((s->version == SSL3_VERSION) &&
		(alg_k & (SSL_kEDH|SSL_kDHd|SSL_kDHr)))
		{
#  ifndef OPENSSL_NO_RSA
		p[ret++]=SSL3_CT_RSA_EPHEMERAL_DH;
#  endif
#  ifndef OPENSSL_NO_DSA
		p[ret++]=SSL3_CT_DSS_EPHEMERAL_DH;
#  endif
		}
#endif /* !OPENSSL_NO_DH */
#ifndef OPENSSL_NO_RSA
	p[ret++]=SSL3_CT_RSA_SIGN;
#endif
#ifndef OPENSSL_NO_DSA
	p[ret++]=SSL3_CT_DSS_SIGN;
#endif
#ifndef OPENSSL_NO_ECDH
	if ((alg_k & (SSL_kECDHr|SSL_kECDHe)) && (s->version >= TLS1_VERSION))
		{
		p[ret++]=TLS_CT_RSA_FIXED_ECDH;
		p[ret++]=TLS_CT_ECDSA_FIXED_ECDH;
		}
#endif

#ifndef OPENSSL_NO_ECDSA
	/* ECDSA certs can be used with RSA cipher suites as well 
	 * so we don't need to check for SSL_kECDH or SSL_kEECDH
	 */
	if (s->version >= TLS1_VERSION)
		{
		p[ret++]=TLS_CT_ECDSA_SIGN;
		}
#endif	
	return(ret);
	}