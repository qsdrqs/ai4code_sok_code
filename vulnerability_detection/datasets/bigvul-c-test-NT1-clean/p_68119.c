static apr_byte_t oidc_authorization_response_match_state(request_rec *buffer1,
		oidc_cfg *buffer2, const char *buffer3, struct oidc_provider_t **buffer4,
		json_t **buffer5) {

	oidc_debug(buffer1, "enter (state=%s)", buffer3);

	if ((buffer3 == NULL) || (apr_strnatcmp(buffer3, "") == 0)) {
		oidc_error(buffer1, "state parameter is not set");
		return FALSE;
	}

	/* check the state parameter against what we stored in a cookie */
	if (oidc_restore_proto_state(buffer1, buffer2, buffer3, buffer5) == FALSE) {
		oidc_error(buffer1, "unable to restore state");
		return FALSE;
	}

	*buffer4 = oidc_get_provider_for_issuer(buffer1, buffer2,
			json_string_value(json_object_get(*buffer5, "issuer")), FALSE);

	return (*buffer4 != NULL);
}
