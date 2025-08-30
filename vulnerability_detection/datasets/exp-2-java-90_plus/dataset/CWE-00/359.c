@Override
	public Authenticated authenticate(UsernamePasswordToken token) {
		String fullName = null;
		String email = null;
		Collection<String> groupNames = null;
        Collection<String> sshKeys = null;

        Name userSearchBase;
		try {
			userSearchBase = new CompositeName().add(getUserSearchBase());
		} catch (InvalidNameException e) {
			throw new RuntimeException(e);
		}
        String userSearchFilter = StringUtils.replace(getUserSearchFilter(), "{0}", token.getUsername());
        userSearchFilter = StringUtils.replace(userSearchFilter, "\\", "\\\\");
        logger.debug("Evaluated user search filter: " + userSearchFilter);
        
        SearchControls searchControls = new SearchControls();
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        List<String> attributeNames = new ArrayList<String>();
        if (getUserFullNameAttribute() != null)
            attributeNames.add(getUserFullNameAttribute());
        
        if (getUserSshKeyAttribute() != null)
        	attributeNames.add(getUserSshKeyAttribute());
        
        attributeNames.add(getUserEmailAttribute());
        
        if (getGroupRetrieval() instanceof GetGroupsUsingAttribute) {
        	GetGroupsUsingAttribute groupRetrieval = (GetGroupsUsingAttribute)getGroupRetrieval();
            attributeNames.add(groupRetrieval.getUserGroupsAttribute());
        }
        searchControls.setReturningAttributes((String[]) attributeNames.toArray(new String[0]));
        searchControls.setReturningObjFlag(true);

        Hashtable<String, String> ldapEnv = new Hashtable<>();
        ldapEnv.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        ldapEnv.put(Context.PROVIDER_URL, getLdapUrl());
        ldapEnv.put(Context.SECURITY_AUTHENTICATION, "simple");
        ldapEnv.put("com.sun.jndi.ldap.connect.timeout", String.valueOf(getTimeout()*1000L));
        ldapEnv.put("com.sun.jndi.ldap.read.timeout", String.valueOf(getTimeout()*1000L));
        ldapEnv.put(Context.REFERRAL, "follow");
        
        ldapEnv.put(Context.SECURITY_PRINCIPAL, getManagerDN());
        ldapEnv.put(Context.SECURITY_CREDENTIALS, getManagerPassword());

        DirContext ctx = null;
        DirContext referralCtx = null;
        try {
            logger.debug("Binding to ldap url '" + getLdapUrl() + "'...");
            try {
            	ctx = new InitialDirContext(ldapEnv);
            } catch (AuthenticationException e) {
        		throw new RuntimeException("Can not bind to ldap server '" + getLdapUrl() + "': " + e.getMessage());
            }
            NamingEnumeration<SearchResult> results = ctx.search(userSearchBase, userSearchFilter, searchControls);
            if (results == null || !results.hasMore()) 
                throw new UnknownAccountException("Unknown account");
            
            SearchResult searchResult = (SearchResult) results.next();
            String userDN = searchResult.getNameInNamespace();
            if (!searchResult.isRelative()) {
            	StringBuffer buffer = new StringBuffer();
                buffer.append(StringUtils.substringBefore(searchResult.getName(), "//"));
                buffer.append("//");
                buffer.append(StringUtils.substringBefore(
                		StringUtils.substringAfter(searchResult.getName(), "//"), "/"));
                
                ldapEnv.put(Context.PROVIDER_URL, buffer.toString());
                logger.debug("Binding to referral ldap url '" + buffer.toString() + "'...");
                referralCtx = new InitialDirContext(ldapEnv);
            }
            if (userDN.startsWith("ldap")) {
            	userDN = StringUtils.substringAfter(userDN, "//");
            	userDN = StringUtils.substringAfter(userDN, "/");
            }

            ldapEnv.put(Context.SECURITY_PRINCIPAL, userDN);
            ldapEnv.put(Context.SECURITY_CREDENTIALS, new String(token.getPassword()));
            DirContext userCtx = null;
            try {
                logger.debug("Authenticating user by binding as '" + userDN + "'...");
                userCtx = new InitialDirContext(ldapEnv);
            } catch (AuthenticationException e) {
            	throw new org.apache.shiro.authc.AuthenticationException("Unable to bind as '" + userDN + "'", e);
            } finally {
                if (userCtx != null) {
                    try {
                        userCtx.close();
                    } catch (NamingException e) {
                    }
                }
            }

            Attributes searchResultAttributes = searchResult.getAttributes();
            
            if (searchResultAttributes != null) {
                if (getUserFullNameAttribute() != null) {
                    Attribute attribute = searchResultAttributes.get(getUserFullNameAttribute());
                    if (attribute != null && attribute.get() != null)
                        fullName = (String) attribute.get();
                }
                
                Attribute attribute = searchResultAttributes.get(getUserEmailAttribute());
                if (attribute != null && attribute.get() != null)
                    email = (String) attribute.get();
                
                if (getGroupRetrieval() instanceof GetGroupsUsingAttribute) 
                	groupNames = retrieveGroupsByAttribute(ctx, referralCtx, searchResultAttributes);
                
                if (getUserSshKeyAttribute() != null) 
                	sshKeys = retrieveSshKeys(searchResultAttributes);
            }
            
            if (getGroupRetrieval() instanceof SearchGroupsUsingFilter) 
            	groupNames = retrieveGroupsByFilter(ctx, referralCtx, userDN);
            
            if (StringUtils.isBlank(email))
            	throw new AccountException("Email is required but not available in ldap directory");
            else
            	return new Authenticated(email, fullName, groupNames, sshKeys);
        } catch (NamingException e) {
        	throw new RuntimeException(e);
        } finally {
            if (ctx != null) {
                try {
                    ctx.close();
                } catch (NamingException e) {
                }
            }
            if (referralCtx != null) {
                try {
                    referralCtx.close();
                } catch (NamingException e) {
                }
            }
        }
	}