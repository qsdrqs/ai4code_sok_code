PHP_FUNCTION(grapheme_strpos)
{
	unsigned char *buffer1, *buffer2;
        int haystack_len, needle_len;
        unsigned char *buffer3;
        long loffset = 0;
       int32_t offset = 0;
        int ret_pos;
 
        if (zend_parse_parameters(ZEND_NUM_ARGS() TSRMLS_CC, "ss|l", (char **)&buffer1, &haystack_len, (char **)&buffer2, &needle_len, &loffset) == FAILURE) {

		intl_error_set( NULL, U_ILLEGAL_ARGUMENT_ERROR,
			 "grapheme_strpos: unable to parse input param", 0 TSRMLS_CC );

		RETURN_FALSE;
	}

	if ( OUTSIDE_STRING(loffset, haystack_len) ) {

		intl_error_set( NULL, U_ILLEGAL_ARGUMENT_ERROR, "grapheme_strpos: Offset not contained in string", 1 TSRMLS_CC );

		RETURN_FALSE;
	}
 
        /* we checked that it will fit: */
        offset = (int32_t) loffset;
 
        /* the offset is 'grapheme count offset' so it still might be invalid - we'll check it later */
 

		intl_error_set( NULL, U_ILLEGAL_ARGUMENT_ERROR, "grapheme_strpos: Empty delimiter", 1 TSRMLS_CC );

		RETURN_FALSE;
	}
