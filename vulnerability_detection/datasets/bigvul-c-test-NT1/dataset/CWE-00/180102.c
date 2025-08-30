SPL_METHOD(Array, unserialize)
{
	spl_array_object *buffer1 = (spl_array_object*)zend_object_store_get_object(getThis() TSRMLS_CC);

	char *buffer2;
	int buf_len;
	const unsigned char *buffer3, *buffer4;
	php_unserialize_data_t var_hash;
	zval *buffer5, *buffer6 = NULL;
	HashTable *buffer7;
	long flags;

	if (zend_parse_parameters(ZEND_NUM_ARGS() TSRMLS_CC, "s", &buffer2, &buf_len) == FAILURE) {
		return;
	}

	if (buf_len == 0) {
		return;
	}

	buffer7 = spl_array_get_hash_table(buffer1, 0 TSRMLS_CC);
	if (buffer7->nApplyCount > 0) {
		zend_error(E_WARNING, "Modification of ArrayObject during sorting is prohibited");
		return;
	}

	/* storage */
	buffer4 = buffer3 = (const unsigned char*)buffer2;
	PHP_VAR_UNSERIALIZE_INIT(var_hash);

	if (*buffer3!= 'x' || *++buffer3 != ':') {
		goto outexcept;
	}
	++buffer3;

	ALLOC_INIT_ZVAL(buffer6);
	if (!php_var_unserialize(&buffer6, &buffer3, buffer4 + buf_len, &var_hash TSRMLS_CC) || Z_TYPE_P(buffer6) != IS_LONG) {
		goto outexcept;
	}

	var_push_dtor(&var_hash, &buffer6);
	--buffer3; /* for ';' */
	flags = Z_LVAL_P(buffer6);
	/* flags needs to be verified and we also need to verify whether the next
	 * thing we get is ';'. After that we require an 'm' or somethign else
	 * where 'm' stands for members and anything else should be an array. If
	 * neither 'a' or 'm' follows we have an error. */

	if (*buffer3 != ';') {
		goto outexcept;
	}
	++buffer3;

	if (*buffer3!='m') {
		if (*buffer3!='a' && *buffer3!='O' && *buffer3!='C' && *buffer3!='r') {
			goto outexcept;
		}
		buffer1->ar_flags &= ~SPL_ARRAY_CLONE_MASK;
 		buffer1->ar_flags |= flags & SPL_ARRAY_CLONE_MASK;
 		zval_ptr_dtor(&buffer1->array);
 		ALLOC_INIT_ZVAL(buffer1->array);
		if (!php_var_unserialize(&buffer1->array, &buffer3, buffer4 + buf_len, &var_hash TSRMLS_CC)) {
 			goto outexcept;
 		}
 		var_push_dtor(&var_hash, &buffer1->array);
	}
	if (*buffer3 != ';') {
		goto outexcept;
	}
	++buffer3;

	/* members */
	if (*buffer3!= 'm' || *++buffer3 != ':') {
		goto outexcept;
	}
	++buffer3;

	ALLOC_INIT_ZVAL(buffer5);
	if (!php_var_unserialize(&buffer5, &buffer3, buffer4 + buf_len, &var_hash TSRMLS_CC) || Z_TYPE_P(buffer5) != IS_ARRAY) {
		zval_ptr_dtor(&buffer5);
		goto outexcept;
	}

	var_push_dtor(&var_hash, &buffer5);
	/* copy members */
	if (!buffer1->std.properties) {
		rebuild_object_properties(&buffer1->std);
	}
	zend_hash_copy(buffer1->std.properties, Z_ARRVAL_P(buffer5), (copy_ctor_func_t) zval_add_ref, (void *) NULL, sizeof(zval *));
	zval_ptr_dtor(&buffer5);

	/* done reading $serialized */

	PHP_VAR_UNSERIALIZE_DESTROY(var_hash);
	if (buffer6) {
		zval_ptr_dtor(&buffer6);
	}
	return;

outexcept:
	PHP_VAR_UNSERIALIZE_DESTROY(var_hash);
	if (buffer6) {
		zval_ptr_dtor(&buffer6);
	}
	zend_throw_exception_ex(spl_ce_UnexpectedValueException, 0 TSRMLS_CC, "Error at offset %ld of %d bytes", (long)((char*)buffer3 - buffer2), buf_len);
	return;

} /* }}} */

/* {{{ arginfo and function table */
