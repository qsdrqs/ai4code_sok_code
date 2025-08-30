@Override
	public void run(TaskMonitor monitor) throws CancelledException {
		FileSystemService fsService = FileSystemService.getInstance();
		String locInfo = "\"" + projectLocator.toString() + "\" from \"" +
			projectArchiveFile.getAbsolutePath() + "\"";

		if (projectFile.exists() || projectDir.exists()) {
			Msg.showError(this, null, "Restore Archive Error",
				"Project already exists at: " + projectDir);
			return;
		}

		FSRL archiveFSRL = fsService.getLocalFSRL(projectArchiveFile);
		try (GFileSystem fs = fsService.openFileSystemContainer(archiveFSRL, monitor)) {
			verifyArchive(fs, monitor);
			startExtract(fs, null, monitor);
			verifyRestoredProject();
			openRestoredProject();
			Msg.info(this, "Restore Archive: " + locInfo + " succeeded.");
		}
		catch (CancelledException ce) {
			plugin.cleanupRestoredProject(projectLocator);
			Msg.info(this, "Restore Archive: " + locInfo + " was cancelled by user.");
		}
		catch (Throwable e) {
			plugin.cleanupRestoredProject(projectLocator);
			String eMsg = (e.getMessage() != null) ? ":\n\n" + e.getMessage() : "";
			Msg.showError(this, null, "Restore Archive Failed",
				"An error occurred when restoring the project archive\n " + projectArchiveFile +
					" to \n " + projectDir + eMsg,
				e);
			Msg.info(this, "Restore Archive: " + locInfo + " failed.");
		}
	}