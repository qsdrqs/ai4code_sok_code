protected URI addContentToRepo(MediaPackage mp, String elementId, URI uri) throws IOException {
    InputStream in = null;
    HttpResponse response = null;
    CloseableHttpClient externalHttpClient = null;
    try {
      if (uri.toString().startsWith("http")) {
        HttpGet get = new HttpGet(uri);
        List<String> clusterUrls = new LinkedList<>();
        try {
          // Note that we are not checking ports here.
          clusterUrls = organizationDirectoryService.getOrganization(uri.toURL()).getServers()
                          .keySet()
                          .stream()
                          .collect(Collectors.toUnmodifiableList());
        } catch (NotFoundException e) {
          logger.warn("Unable to determine cluster members, will not be able to authenticate any downloads from them", e);
        }

        if (uri.toString().matches(downloadSource)) {
          //NB: We're creating a new client here with *different* auth than the system auth creds
          externalHttpClient = getAuthedHttpClient();
          response = externalHttpClient.execute(get);
        } else if (clusterUrls.contains(uri.getScheme() + "://" + uri.getHost())) {
          // Only using the system-level httpclient and digest credentials against our own servers
          response = httpClient.execute(get);
        } else {
          //NB: No auth here at all
          externalHttpClient = getNoAuthHttpClient();
          response = externalHttpClient.execute(get);
        }

        if (null == response) {
          // If you get here then chances are you're using a mock httpClient which does not have appropriate
          // mocking to respond to the URL you are feeding it.  Try adding that URL to the mock and see if that works.
          throw new IOException("Null response object from the http client, refer to code for explanation");
        }

        int httpStatusCode = response.getStatusLine().getStatusCode();
        if (httpStatusCode != 200) {
          throw new IOException(uri + " returns http " + httpStatusCode);
        }
        in = response.getEntity().getContent();
        //If it does not start with file, or we're in test mode (ie, to allow arbitrary file:// access)
      } else if (!uri.toString().startsWith("file") || testMode) {
        in = uri.toURL().openStream();
      } else {
        throw new IOException("Refusing to fetch files from the local filesystem");
      }
      String fileName = FilenameUtils.getName(uri.getPath());
      if (isBlank(FilenameUtils.getExtension(fileName)))
        fileName = getContentDispositionFileName(response);

      if (isBlank(FilenameUtils.getExtension(fileName)))
        throw new IOException("No filename extension found: " + fileName);
      return addContentToRepo(mp, elementId, fileName, in);
    } finally {
      if (in != null) {
        in.close();
      }
      if (externalHttpClient != null) {
        externalHttpClient.close();
      }
      httpClient.close(response);
    }
  }