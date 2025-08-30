public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
            HttpServletRequest httpReq = (HttpServletRequest) request;
            HttpServletResponse httpResp = (HttpServletResponse) response;

            if ("GET".equals(httpReq.getMethod())) {
                Meteor meteor = Meteor.build(httpReq, SCOPE.REQUEST, Collections.<BroadcastFilter>emptyList(), null);

                String pushSessionId = httpReq.getParameter(PUSH_SESSION_ID_PARAM);

                Session session = null;

                if (pushSessionId != null) {
                    ensureServletContextAvailable(request);
                    PushContext pushContext = (PushContext) servletContext.getAttribute(PushContext.INSTANCE_KEY_NAME);
                    session = pushContext.getSessionManager().getPushSession(pushSessionId);
                }

                if (session == null) {
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug(MessageFormat.format("Session {0} was not found", pushSessionId));
                    }
                    httpResp.sendError(HttpServletResponse.SC_BAD_REQUEST);
                    return;
                }

                httpResp.setContentType("text/plain");

                try {
                    Request pushRequest = new RequestImpl(meteor, session);

                    httpReq.setAttribute(SESSION_ATTRIBUTE_NAME, session);
                    httpReq.setAttribute(REQUEST_ATTRIBUTE_NAME, pushRequest);

                    pushRequest.suspend();
                } catch (Exception e) {
                    LOGGER.error(e.getMessage(), e);
                }

                return;
            }
        }
    }