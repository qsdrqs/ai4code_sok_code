 SPL_METHOD(SplFileObject, valid)
 {
 	spl_filesystem_object *buffer1 = (spl_filesystem_object*)zend_object_store_get_object(getThis() TSRMLS_CC);
 	if (zend_parse_parameters_none() == FAILURE) {
 		return;
 	}

	if (SPL_HAS_FLAG(buffer1->flags, SPL_FILE_OBJECT_READ_AHEAD)) {
		RETURN_BOOL(buffer1->u.file.current_line || buffer1->u.file.current_zval);
	} else {
		RETVAL_BOOL(!php_stream_eof(buffer1->u.file.stream));
	}
} /* }}} */

/* {{{ proto string SplFileObject::fgets()
