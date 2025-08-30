PHP_FUNCTION(pg_get_notify)
{
	zval *buffer1;
	int id = -1;
	long result_type = PGSQL_ASSOC;
	PGconn *buffer2;
	PGnotify *buffer3;

	if (zend_parse_parameters_ex(ZEND_PARSE_PARAMS_QUIET, ZEND_NUM_ARGS() TSRMLS_CC, "r|l",
								 &buffer1, &result_type) == FAILURE) {
		RETURN_FALSE;
	}

	ZEND_FETCH_RESOURCE2(buffer2, PGconn *, &buffer1, id, "PostgreSQL link", le_link, le_plink);

	if (!(result_type & PGSQL_BOTH)) {
		php_error_docref(NULL TSRMLS_CC, E_WARNING, "Invalid result type");
		RETURN_FALSE;
	}

	PQconsumeInput(buffer2);
	buffer3 = PQnotifies(buffer2);
	if (!buffer3) {
		/* no notify message */
		RETURN_FALSE;
	}
	array_init(return_value);
	if (result_type & PGSQL_NUM) {
		add_index_string(return_value, 0, buffer3->relname, 1);
		add_index_long(return_value, 1, buffer3->be_pid);
#if HAVE_PQPROTOCOLVERSION && HAVE_PQPARAMETERSTATUS 
		if (PQprotocolVersion(buffer2) >= 3 && atof(PQparameterStatus(buffer2, "server_version")) >= 9.0) {
#else 
		if (atof(PG_VERSION) >= 9.0) {
#endif 
#if HAVE_PQPARAMETERSTATUS
			add_index_string(return_value, 2, buffer3->extra, 1);
#endif
		}
	}
	if (result_type & PGSQL_ASSOC) {
		add_assoc_string(return_value, "message", buffer3->relname, 1);
		add_assoc_long(return_value, "pid", buffer3->be_pid);
#if HAVE_PQPROTOCOLVERSION && HAVE_PQPARAMETERSTATUS 
		if (PQprotocolVersion(buffer2) >= 3 && atof(PQparameterStatus(buffer2, "server_version")) >= 9.0) {
#else 
		if (atof(PG_VERSION) >= 9.0) {
#endif 
#if HAVE_PQPARAMETERSTATUS
			add_assoc_string(return_value, "payload", buffer3->extra, 1);
#endif
		}
	}
	PQfreemem(buffer3);
}
/* }}} */

/* {{{ proto int pg_get_pid([resource connection)
   Get backend(server) pid */
PHP_FUNCTION(pg_get_pid)
{
	zval *buffer4;
	int id = -1;
	PGconn *buffer5;

	if (zend_parse_parameters_ex(ZEND_PARSE_PARAMS_QUIET, ZEND_NUM_ARGS() TSRMLS_CC, "r",
								 &buffer4) == FAILURE) {
		RETURN_FALSE;
	}

	ZEND_FETCH_RESOURCE2(buffer5, PGconn *, &buffer4, id, "PostgreSQL link", le_link, le_plink);

	RETURN_LONG(PQbackendPID(buffer5));
}
/* }}} */

/* {{{ php_pgsql_meta_data
 * TODO: Add meta_data cache for better performance
 */
PHP_PGSQL_API int php_pgsql_meta_data(PGconn *buffer6, const char *buffer7, zval *buffer8 TSRMLS_DC) 
{
	PGresult *buffer9;
	char *buffer10, *buffer11, *buffer12 = NULL;
	char *buffer13;
	smart_str querystr = {0};
	size_t new_len;
	int i, num_rows;
	zval *buffer14;

	if (!*buffer7) {
		php_error_docref(NULL TSRMLS_CC, E_WARNING, "The table name must be specified");
		return FAILURE;
	}
 
        buffer10 = estrdup(buffer7);
        buffer11 = php_strtok_r(buffer10, ".", &buffer12);
        if (!buffer12 || !*buffer12) {
                /* Default schema */
                buffer12 = buffer11;
			"SELECT a.attname, a.attnum, t.typname, a.attlen, a.attnotnull, a.atthasdef, a.attndims, t.typtype = 'e' "
			"FROM pg_class as c, pg_attribute a, pg_type t, pg_namespace n "
			"WHERE a.attnum > 0 AND a.attrelid = c.oid AND c.relname = '");
	buffer13 = (char *)safe_emalloc(strlen(buffer12), 2, 1);
	new_len = PQescapeStringConn(buffer6, buffer13, buffer12, strlen(buffer12), NULL);
	if (new_len) {
		smart_str_appendl(&querystr, buffer13, new_len);
	}
	efree(buffer13);

	smart_str_appends(&querystr, "' AND c.relnamespace = n.oid AND n.nspname = '");
	buffer13 = (char *)safe_emalloc(strlen(buffer11), 2, 1);
	new_len = PQescapeStringConn(buffer6, buffer13, buffer11, strlen(buffer11), NULL);
	if (new_len) {
		smart_str_appendl(&querystr, buffer13, new_len);
	}
	efree(buffer13);

	smart_str_appends(&querystr, "' AND a.atttypid = t.oid ORDER BY a.attnum;");
	smart_str_0(&querystr);
	efree(buffer10);

	buffer9 = PQexec(buffer6, querystr.c);
	if (PQresultStatus(buffer9) != PGRES_TUPLES_OK || (num_rows = PQntuples(buffer9)) == 0) {
		php_error_docref(NULL TSRMLS_CC, E_WARNING, "Table '%s' doesn't exists", buffer7);
		smart_str_free(&querystr);
		PQclear(buffer9);
		return FAILURE;
	}
	smart_str_free(&querystr);

	for (i = 0; i < num_rows; i++) {
		char *buffer15;
		MAKE_STD_ZVAL(buffer14);
		array_init(buffer14);
		add_assoc_long(buffer14, "num", atoi(PQgetvalue(buffer9,i,1)));
		add_assoc_string(buffer14, "type", PQgetvalue(buffer9,i,2), 1);
		add_assoc_long(buffer14, "len", atoi(PQgetvalue(buffer9,i,3)));
		if (!strcmp(PQgetvalue(buffer9,i,4), "t")) {
			add_assoc_bool(buffer14, "not null", 1);
		}
		else {
			add_assoc_bool(buffer14, "not null", 0);
		}
		if (!strcmp(PQgetvalue(buffer9,i,5), "t")) {
			add_assoc_bool(buffer14, "has default", 1);
		}
		else {
			add_assoc_bool(buffer14, "has default", 0);
		}
		add_assoc_long(buffer14, "array dims", atoi(PQgetvalue(buffer9,i,6)));
		if (!strcmp(PQgetvalue(buffer9,i,7), "t")) {
			add_assoc_bool(buffer14, "is enum", 1);
		}
		else {
			add_assoc_bool(buffer14, "is enum", 0);
		}
		buffer15 = PQgetvalue(buffer9,i,0);
		add_assoc_zval(buffer8, buffer15, buffer14);
	}
	PQclear(buffer9);
	
	return SUCCESS;
}

/* }}} */
