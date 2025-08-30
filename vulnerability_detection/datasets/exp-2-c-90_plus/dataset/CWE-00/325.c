static int oidc_handle_existing_session(request_rec *r, oidc_cfg *cfg,
		oidc_session_t *session) {

	oidc_debug(r, "enter");

	/* get the header name in which the remote user name needs to be passed */
	char *authn_header = oidc_cfg_dir_authn_header(r);
	int pass_headers = oidc_cfg_dir_pass_info_in_headers(r);
	int pass_envvars = oidc_cfg_dir_pass_info_in_envvars(r);

	/* verify current cookie domain against issued cookie domain */
	if (oidc_check_cookie_domain(r, cfg, session) == FALSE)
		return HTTP_UNAUTHORIZED;

	/* check if the maximum session duration was exceeded */
	int rc = oidc_check_max_session_duration(r, cfg, session);
	if (rc != OK)
		return rc;

	/* if needed, refresh claims from the user info endpoint */
	apr_byte_t needs_save = oidc_refresh_claims_from_userinfo_endpoint(r, cfg,
			session);

	/*
	 * we're going to pass the information that we have to the application,
	 * but first we need to scrub the headers that we're going to use for security reasons
	 */
	if (cfg->scrub_request_headers != 0) {

		/* scrub all headers starting with OIDC_ first */
		oidc_scrub_request_headers(r, OIDC_DEFAULT_HEADER_PREFIX,
				oidc_cfg_dir_authn_header(r));

		/*
		 * then see if the claim headers need to be removed on top of that
		 * (i.e. the prefix does not start with the default OIDC_)
		 */
		if ((strstr(cfg->claim_prefix, OIDC_DEFAULT_HEADER_PREFIX)
				!= cfg->claim_prefix)) {
			oidc_scrub_request_headers(r, cfg->claim_prefix, NULL);
		}
	}

	/* set the user authentication HTTP header if set and required */
	if ((r->user != NULL) && (authn_header != NULL))
		oidc_util_set_header(r, authn_header, r->user);

	const char *s_claims = NULL;
	const char *s_id_token = NULL;

	/* copy id_token and claims from session to request state and obtain their values */
	oidc_copy_tokens_to_request_state(r, session, &s_id_token, &s_claims);

	/* set the claims in the app headers  */
	if (oidc_set_app_claims(r, cfg, session, s_claims) == FALSE)
		return HTTP_INTERNAL_SERVER_ERROR;

	if ((cfg->pass_idtoken_as & OIDC_PASS_IDTOKEN_AS_CLAIMS)) {
		/* set the id_token in the app headers */
		if (oidc_set_app_claims(r, cfg, session, s_id_token) == FALSE)
			return HTTP_INTERNAL_SERVER_ERROR;
	}

	if ((cfg->pass_idtoken_as & OIDC_PASS_IDTOKEN_AS_PAYLOAD)) {
		/* pass the id_token JSON object to the app in a header or environment variable */
		oidc_util_set_app_info(r, "id_token_payload", s_id_token,
				OIDC_DEFAULT_HEADER_PREFIX, pass_headers, pass_envvars);
	}

	if ((cfg->pass_idtoken_as & OIDC_PASS_IDTOKEN_AS_SERIALIZED)) {
		if (cfg->session_type != OIDC_SESSION_TYPE_CLIENT_COOKIE) {
			const char *s_id_token = NULL;
			/* get the compact serialized JWT from the session */
			oidc_session_get(r, session, OIDC_IDTOKEN_SESSION_KEY, &s_id_token);
			/* pass the compact serialized JWT to the app in a header or environment variable */
			oidc_util_set_app_info(r, "id_token", s_id_token,
					OIDC_DEFAULT_HEADER_PREFIX, pass_headers, pass_envvars);
		} else {
			oidc_error(r,
					"session type \"client-cookie\" does not allow storing/passing the id_token; use \"OIDCSessionType server-cache\" for that");
		}
	}

	/* set the refresh_token in the app headers/variables, if enabled for this location/directory */
	const char *refresh_token = NULL;
	oidc_session_get(r, session, OIDC_REFRESHTOKEN_SESSION_KEY, &refresh_token);
	if ((oidc_cfg_dir_pass_refresh_token(r) != 0) && (refresh_token != NULL)) {
		/* pass it to the app in a header or environment variable */
		oidc_util_set_app_info(r, "refresh_token", refresh_token,
				OIDC_DEFAULT_HEADER_PREFIX, pass_headers, pass_envvars);
	}

	/* set the access_token in the app headers/variables */
	const char *access_token = NULL;
	oidc_session_get(r, session, OIDC_ACCESSTOKEN_SESSION_KEY, &access_token);
	if (access_token != NULL) {
		/* pass it to the app in a header or environment variable */
		oidc_util_set_app_info(r, "access_token", access_token,
				OIDC_DEFAULT_HEADER_PREFIX, pass_headers, pass_envvars);
	}

	/* set the expiry timestamp in the app headers/variables */
	const char *access_token_expires = NULL;
	oidc_session_get(r, session, OIDC_ACCESSTOKEN_EXPIRES_SESSION_KEY,
			&access_token_expires);
	if (access_token_expires != NULL) {
		/* pass it to the app in a header or environment variable */
		oidc_util_set_app_info(r, "access_token_expires", access_token_expires,
				OIDC_DEFAULT_HEADER_PREFIX, pass_headers, pass_envvars);
	}

	/*
	 * reset the session inactivity timer
	 * but only do this once per 10% of the inactivity timeout interval (with a max to 60 seconds)
	 * for performance reasons
	 *
	 * now there's a small chance that the session ends 10% (or a minute) earlier than configured/expected
	 * cq. when there's a request after a recent save (so no update) and then no activity happens until
	 * a request comes in just before the session should expire
	 * ("recent" and "just before" refer to 10%-with-a-max-of-60-seconds of the inactivity interval after
	 * the start/last-update and before the expiry of the session respectively)
	 *
	 * this is be deemed acceptable here because of performance gain
	 */
	apr_time_t interval = apr_time_from_sec(cfg->session_inactivity_timeout);
	apr_time_t now = apr_time_now();
	apr_time_t slack = interval / 10;
	if (slack > apr_time_from_sec(60))
		slack = apr_time_from_sec(60);
	if (session->expiry - now < interval - slack) {
		session->expiry = now + interval;
		needs_save = TRUE;
	}

	/* check if something was updated in the session and we need to save it again */
	if (needs_save)
		if (oidc_session_save(r, session) == FALSE)
			return HTTP_INTERNAL_SERVER_ERROR;

	/* return "user authenticated" status */
	return OK;
}