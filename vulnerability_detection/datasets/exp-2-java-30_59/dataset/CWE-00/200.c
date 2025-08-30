public Stream<CompilerInput> finalize(URI remoteURI, PackageID moduleID) {
        try {
            // only continue if a fixed module version is not set. a module version may be set through Ballerina.toml or
            // Ballerina.lock already.
            Matcher matcher = semVerPatchPattern.matcher(moduleID.version.value);
            if ("".equals(moduleID.version.value) || "*".equals(moduleID.version.value) || matcher.matches()) {
                HttpURLConnection conn;
                // set proxy if exists.
                if (null == this.proxy) {
                    conn = (HttpURLConnection) remoteURI.toURL().openConnection();
                } else {
                    conn = (HttpURLConnection) remoteURI.toURL().openConnection(this.proxy);
                }
                conn.setInstanceFollowRedirects(false);
                conn.setRequestMethod("GET");

                // status code and meaning
                //// 200 - module info found
                //// 400 - bad request sent
                //// 500 - backend is broken
                int statusCode = conn.getResponseCode();
                if (statusCode == 200) {
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),
                            Charset.defaultCharset()))) {
                        StringBuilder result = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            result.append(line);
                        }
                        Object payload = JSONParser.parse(result.toString());
                        if (payload instanceof MapValue) {
                            MapValue moduleInfo = ((MapValue) payload).getMapValue("module");
                            String version = moduleInfo.getStringValue("version");
                            moduleID.version = new Name(version);
                        }
                    }
                } else if (statusCode == 400 && !loggedError) {
                    try (BufferedReader errorStream = new BufferedReader(
                            new InputStreamReader(conn.getInputStream(), Charset.defaultCharset()))) {
                        String errorContent = errorStream.lines().collect(Collectors.joining("\n"));
                        this.errStream.println("error: could not connect to remote repository to find the latest " +
                                "version of module: " + moduleID.toString() + ". use '--offline' " +
                                "flag to build in offline mode. reason: " + errorContent);
                        setErrorLoggedStatusAsTrue();
                    }
                } else if (statusCode == 500 && !loggedError) {
                    this.errStream.println("error: could not connect to remote repository to find the latest " +
                            "version of module: " + moduleID.toString() + ". use '--offline' " +
                            "flag to build in offline mode.");
                    setErrorLoggedStatusAsTrue();
                }
                conn.disconnect();
                Authenticator.setDefault(null);
            }
        } catch (IOException e) {
            // ignore error and don't set the version.
        }

        return Stream.empty();
    }