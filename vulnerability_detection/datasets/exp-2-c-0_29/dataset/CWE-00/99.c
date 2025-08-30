static int php_snmp_write_exceptions_enabled(php_snmp_object *snmp_object, zval *newval TSRMLS_DC)
{
	zval ztmp;
	int ret = SUCCESS;
	if (Z_TYPE_P(newval) != IS_LONG) {
		ztmp = *newval;
		zval_copy_ctor(&ztmp);
		convert_to_long(&ztmp);
		newval = &ztmp;
	}

	snmp_object->exceptions_enabled = Z_LVAL_P(newval);	

	if (newval == &ztmp) {
		zval_dtor(newval);
	}
	return ret;
}