public Map<String, Object> loadExport(Owner owner, File exportFile,
        ConflictOverrides overrides)
        throws ImporterException {
        File tmpDir = null;
        InputStream exportStream = null;
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            tmpDir = new SyncUtils(config).makeTempDir("import");
            extractArchive(tmpDir, exportFile);

//           only need this call when sig file is verified
//            exportStream = new FileInputStream(new File(tmpDir, "consumer_export.zip"));

            /*
             * Disabling this once again for a little bit longer. Dependent projects
             * are not yet ready for it, and we're having some difficulty with the actual
             * upstream cert to use.
             *
             * When we bring this back, we should probably report this conflict
             * immediately, rather than continuing to extract and trying to find any
             * other conflicts to pass back.
             */
//            boolean verifiedSignature = pki.verifySHA256WithRSAHashWithUpstreamCACert(
//                exportStream,
//                loadSignature(new File(tmpDir, "signature")));
//            if (!verifiedSignature) {
//                log.warn("Manifest signature check failed.");
//                if (!forcedConflicts
//                    .isForced(ImportConflicts.Conflict.SIGNATURE_CONFLICT)) {
//                    conflicts.addConflict(
//                        i18n.tr("Failed import file hash check."),
//                        ImportConflicts.Conflict.SIGNATURE_CONFLICT);
//                }
//                else {
//                    log.warn("Ignoring signature check failure.");
//                }
//            }

            File signature = new File(tmpDir, "signature");
            if (signature.length() == 0) {
                throw new ImportExtractionException(i18n.tr("The archive does not " +
                                          "contain the required signature file"));
            }

            File consumerExport = new File(tmpDir, "consumer_export.zip");
            File exportDir = extractArchive(tmpDir, consumerExport);

            Map<String, File> importFiles = new HashMap<String, File>();
            File[] listFiles = exportDir.listFiles();
            if (listFiles == null || listFiles.length == 0) {
                throw new ImportExtractionException(i18n.tr("The consumer_export " +
                    "archive has no contents"));
            }
            for (File file : listFiles) {
                importFiles.put(file.getName(), file);
            }

            ConsumerDto consumer = importObjects(owner, importFiles, overrides);
            Meta m = mapper.readValue(importFiles.get(ImportFile.META.fileName()),
                Meta.class);
            result.put("consumer", consumer);
            result.put("meta", m);
            return result;
        }
//        catch (CertificateException e) {
//            log.error("Exception caught importing archive", e);
//            throw new ImportExtractionException("unable to extract export archive", e);
//        }
        catch (FileNotFoundException fnfe) {
            log.error("Archive file does not contain consumer_export.zip", fnfe);
            throw new ImportExtractionException(i18n.tr("The archive does not contain " +
                                           "the required consumer_export.zip file"));
        }
        catch (ConstraintViolationException cve) {
            log.error("Failed to import archive", cve);
            throw new ImporterException(i18n.tr("Failed to import archive"),
                cve);
        }
        catch (PersistenceException pe) {
            log.error("Failed to import archive", pe);
            throw new ImporterException(i18n.tr("Failed to import archive"),
                pe);
        }
        catch (IOException e) {
            log.error("Exception caught importing archive", e);
            throw new ImportExtractionException("unable to extract export archive", e);
        }
        finally {
            if (tmpDir != null) {
                try {
                    FileUtils.deleteDirectory(tmpDir);
                }
                catch (IOException e) {
                    log.error("Failed to delete extracted export", e);
                }
            }
            if (exportStream != null) {
                try {
                    exportStream.close();
                }
                catch (Exception e) {
                    // nothing we can do.
                }
            }
        }
    }