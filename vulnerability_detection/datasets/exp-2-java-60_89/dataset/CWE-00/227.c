@Override
	protected ResourceResponse newResourceResponse(Attributes attributes) {
		PageParameters params = attributes.getParameters();

		Long projectId = params.get(PARAM_PROJECT).toLong();
		Project project = OneDev.getInstance(ProjectManager.class).load(projectId);
		
		Long buildNumber = params.get(PARAM_BUILD).toOptionalLong();
		
		if (buildNumber == null)
			throw new IllegalArgumentException("build number has to be specified");
		
		Build build = OneDev.getInstance(BuildManager.class).find(project, buildNumber);

		if (build == null) {
			String message = String.format("Unable to find build (project: %s, build number: %d)", 
					project.getPath(), buildNumber);
			throw new EntityNotFoundException(message);
		}
		
		if (!SecurityUtils.canAccess(build))
			throw new UnauthorizedException();
		
		List<String> pathSegments = new ArrayList<>();

		for (int i = 0; i < params.getIndexedCount(); i++) {
			String pathSegment = params.get(i).toString();
			if (pathSegment.length() != 0)
				pathSegments.add(pathSegment);
		}
		
		if (pathSegments.isEmpty())
			throw new ExplicitException("Artifact path has to be specified");
		
		String artifactPath = Joiner.on("/").join(pathSegments);
		
		File artifactsDir = build.getArtifactsDir();
		File artifactFile = new File(artifactsDir, artifactPath);
		if (!artifactFile.exists() || artifactFile.isDirectory()) {
			String message = String.format("Specified artifact path does not exist or is a directory (project: %s, build number: %d, path: %s)", 
					project.getPath(), build.getNumber(), artifactPath);
			throw new ExplicitException(message);
		}
			
		ResourceResponse response = new ResourceResponse();
		try (InputStream is = new BufferedInputStream(new FileInputStream(artifactFile))) {
			response.setContentType(ContentDetector.detectMediaType(is, artifactPath).toString());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		response.disableCaching();
		
		try {
			response.setFileName(URLEncoder.encode(artifactFile.getName(), StandardCharsets.UTF_8.name()));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		
		response.setContentLength(artifactFile.length());
		
		response.setWriteCallback(new WriteCallback() {

			@Override
			public void writeData(Attributes attributes) throws IOException {
				LockUtils.read(build.getArtifactsLockKey(), new Callable<Void>() {

					@Override
					public Void call() throws Exception {
						try (InputStream is = new FileInputStream(artifactFile)) {
							IOUtils.copy(is, attributes.getResponse().getOutputStream());
						}
						return null;
					}
					
				});
			}			
			
		});

		return response;
	}