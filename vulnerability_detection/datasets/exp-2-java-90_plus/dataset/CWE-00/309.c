public static void load(String originalName, ClassLoader loader) {
        // Adjust expected name to support shading of native libraries.
        String packagePrefix = calculatePackagePrefix().replace('.', '_');
        String name = packagePrefix + originalName;
        List<Throwable> suppressed = new ArrayList<Throwable>();
        try {
            // first try to load from java.library.path
            loadLibrary(loader, name, false);
            return;
        } catch (Throwable ex) {
            suppressed.add(ex);
        }

        String libname = System.mapLibraryName(name);
        String path = NATIVE_RESOURCE_HOME + libname;

        InputStream in = null;
        OutputStream out = null;
        File tmpFile = null;
        URL url;
        if (loader == null) {
            url = ClassLoader.getSystemResource(path);
        } else {
            url = loader.getResource(path);
        }
        try {
            if (url == null) {
                if (PlatformDependent.isOsx()) {
                    String fileName = path.endsWith(".jnilib") ? NATIVE_RESOURCE_HOME + "lib" + name + ".dynlib" :
                            NATIVE_RESOURCE_HOME + "lib" + name + ".jnilib";
                    if (loader == null) {
                        url = ClassLoader.getSystemResource(fileName);
                    } else {
                        url = loader.getResource(fileName);
                    }
                    if (url == null) {
                        FileNotFoundException fnf = new FileNotFoundException(fileName);
                        ThrowableUtil.addSuppressedAndClear(fnf, suppressed);
                        throw fnf;
                    }
                } else {
                    FileNotFoundException fnf = new FileNotFoundException(path);
                    ThrowableUtil.addSuppressedAndClear(fnf, suppressed);
                    throw fnf;
                }
            }

            int index = libname.lastIndexOf('.');
            String prefix = libname.substring(0, index);
            String suffix = libname.substring(index);

            tmpFile = File.createTempFile(prefix, suffix, WORKDIR);
            in = url.openStream();
            out = new FileOutputStream(tmpFile);

            if (shouldShadedLibraryIdBePatched(packagePrefix)) {
                patchShadedLibraryId(in, out, originalName, name);
            } else {
                byte[] buffer = new byte[8192];
                int length;
                while ((length = in.read(buffer)) > 0) {
                    out.write(buffer, 0, length);
                }
            }

            out.flush();

            // Close the output stream before loading the unpacked library,
            // because otherwise Windows will refuse to load it when it's in use by other process.
            closeQuietly(out);
            out = null;
            loadLibrary(loader, tmpFile.getPath(), true);
        } catch (UnsatisfiedLinkError e) {
            try {
                if (tmpFile != null && tmpFile.isFile() && tmpFile.canRead() &&
                    !NoexecVolumeDetector.canExecuteExecutable(tmpFile)) {
                    // Pass "io.netty.native.workdir" as an argument to allow shading tools to see
                    // the string. Since this is printed out to users to tell them what to do next,
                    // we want the value to be correct even when shading.
                    logger.info("{} exists but cannot be executed even when execute permissions set; " +
                                "check volume for \"noexec\" flag; use -D{}=[path] " +
                                "to set native working directory separately.",
                                tmpFile.getPath(), "io.netty.native.workdir");
                }
            } catch (Throwable t) {
                suppressed.add(t);
                logger.debug("Error checking if {} is on a file store mounted with noexec", tmpFile, t);
            }
            // Re-throw to fail the load
            ThrowableUtil.addSuppressedAndClear(e, suppressed);
            throw e;
        } catch (Exception e) {
            UnsatisfiedLinkError ule = new UnsatisfiedLinkError("could not load a native library: " + name);
            ule.initCause(e);
            ThrowableUtil.addSuppressedAndClear(ule, suppressed);
            throw ule;
        } finally {
            closeQuietly(in);
            closeQuietly(out);
            // After we load the library it is safe to delete the file.
            // We delete the file immediately to free up resources as soon as possible,
            // and if this fails fallback to deleting on JVM exit.
            if (tmpFile != null && (!DELETE_NATIVE_LIB_AFTER_LOADING || !tmpFile.delete())) {
                tmpFile.deleteOnExit();
            }
        }
    }