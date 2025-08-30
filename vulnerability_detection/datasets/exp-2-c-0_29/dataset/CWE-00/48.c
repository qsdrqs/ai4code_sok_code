zend_object_iterator *spl_array_get_iterator(zend_class_entry *ce, zval *object, int by_ref TSRMLS_DC) /* {{{ */
{
	spl_array_it       *iterator;
	spl_array_object   *array_object = (spl_array_object*)zend_object_store_get_object(object TSRMLS_CC);

	if (by_ref && (array_object->ar_flags & SPL_ARRAY_OVERLOADED_CURRENT)) {
		zend_error(E_ERROR, "An iterator cannot be used with foreach by reference");
	}

	iterator     = emalloc(sizeof(spl_array_it));

	Z_ADDREF_P(object);
	iterator->intern.it.data = (void*)object;
	iterator->intern.it.funcs = &spl_array_it_funcs;
	iterator->intern.ce = ce;
	iterator->intern.value = NULL;
	iterator->object = array_object;
	
	return (zend_object_iterator*)iterator;
}