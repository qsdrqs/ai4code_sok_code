@Override
	public void configure(ServletContextHandler context) {
		context.setContextPath("/");
		
		context.getSessionHandler().setMaxInactiveInterval(serverConfig.getSessionTimeout());
		
		context.setInitParameter(EnvironmentLoader.ENVIRONMENT_CLASS_PARAM, DefaultWebEnvironment.class.getName());
		context.addEventListener(new EnvironmentLoaderListener());
		context.addFilter(new FilterHolder(shiroFilter), "/*", EnumSet.allOf(DispatcherType.class));
		
        context.addFilter(new FilterHolder(gitFilter), "/*", EnumSet.allOf(DispatcherType.class));
		
		context.addServlet(new ServletHolder(preReceiveServlet), GitPreReceiveCallback.PATH + "/*");
        
        context.addServlet(new ServletHolder(postReceiveServlet), GitPostReceiveCallback.PATH + "/*");
        
		/*
		 * Add wicket servlet as the default servlet which will serve all requests failed to 
		 * match a path pattern
		 */
		context.addServlet(new ServletHolder(wicketServlet), "/");
		
		context.addServlet(new ServletHolder(attachmentUploadServlet), "/attachment_upload");
		
		context.addServlet(new ServletHolder(new ClasspathAssetServlet(ImageScope.class)), "/img/*");
		context.addServlet(new ServletHolder(new ClasspathAssetServlet(IconScope.class)), "/icon/*");
		
		context.getSessionHandler().addEventListener(new HttpSessionListener() {

			@Override
			public void sessionCreated(HttpSessionEvent se) {
			}

			@Override
			public void sessionDestroyed(HttpSessionEvent se) {
				webSocketManager.onDestroySession(se.getSession().getId());
			}
			
		});
		
		/*
		 * Configure a servlet to serve contents under site folder. Site folder can be used 
		 * to hold site specific web assets.   
		 */
		ServletHolder fileServletHolder = new ServletHolder(new FileAssetServlet(Bootstrap.getSiteDir()));
		context.addServlet(fileServletHolder, "/site/*");
		context.addServlet(fileServletHolder, "/robots.txt");
		
		context.addServlet(new ServletHolder(jerseyServlet), "/rest/*");		
	}