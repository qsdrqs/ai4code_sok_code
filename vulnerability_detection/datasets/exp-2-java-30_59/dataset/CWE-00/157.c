public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse rsp = (HttpServletResponse) response;
        String authorization = req.getHeader("Authorization");

        if (authorization!=null) {
            // authenticate the user
            String uidpassword = Scrambler.descramble(authorization.substring(6));
            int idx = uidpassword.indexOf(':');
            if (idx >= 0) {
                String username = uidpassword.substring(0, idx);
                String password = uidpassword.substring(idx+1);

                // attempt to authenticate as API token
                User u = User.get(username);
                ApiTokenProperty t = u.getProperty(ApiTokenProperty.class);
                if (t!=null && t.matchesPassword(password)) {
                    // even if we fail to match the password, we aren't rejecting it.
                    // as the user might be passing in a real password.
                    SecurityContext oldContext = ACL.impersonate(u.impersonate());
                    try {
                        request.setAttribute(ApiTokenProperty.class.getName(), u);
                        chain.doFilter(request,response);
                        return;
                    } finally {
                        SecurityContextHolder.setContext(oldContext);
                    }
                }
            }
        }

        chain.doFilter(request,response);
    }