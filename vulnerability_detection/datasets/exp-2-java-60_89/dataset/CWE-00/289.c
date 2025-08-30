@SuppressLint("SdCardPath")
    private void startNewUpload(
        User user,
        List<String> requestedUploads,
        boolean onWifiOnly,
        boolean whileChargingOnly,
        NameCollisionPolicy nameCollisionPolicy,
        int localAction,
        boolean isCreateRemoteFolder,
        int createdBy,
        OCFile file,
        boolean disableRetries
                               ) {
        if (file.getStoragePath().startsWith("/data/data/")) {
            Log_OC.d(TAG, "Upload from sensitive path is not allowed");
            return;
        }

        OCUpload ocUpload = new OCUpload(file, user);
        ocUpload.setFileSize(file.getFileLength());
        ocUpload.setNameCollisionPolicy(nameCollisionPolicy);
        ocUpload.setCreateRemoteFolder(isCreateRemoteFolder);
        ocUpload.setCreatedBy(createdBy);
        ocUpload.setLocalAction(localAction);
        ocUpload.setUseWifiOnly(onWifiOnly);
        ocUpload.setWhileChargingOnly(whileChargingOnly);
        ocUpload.setUploadStatus(UploadStatus.UPLOAD_IN_PROGRESS);

        UploadFileOperation newUpload = new UploadFileOperation(
            mUploadsStorageManager,
            connectivityService,
            powerManagementService,
            user,
            file,
            ocUpload,
            nameCollisionPolicy,
            localAction,
            this,
            onWifiOnly,
            whileChargingOnly,
            disableRetries,
            new FileDataStorageManager(user, getContentResolver())
        );
        newUpload.setCreatedBy(createdBy);
        if (isCreateRemoteFolder) {
            newUpload.setRemoteFolderToBeCreated();
        }
        newUpload.addDataTransferProgressListener(this);
        newUpload.addDataTransferProgressListener((FileUploaderBinder) mBinder);

        newUpload.addRenameUploadListener(this);

        Pair<String, String> putResult = mPendingUploads.putIfAbsent(
            user.getAccountName(),
            file.getRemotePath(),
            newUpload
        );

        if (putResult != null) {
            requestedUploads.add(putResult.first);

            // Save upload in database
            long id = mUploadsStorageManager.storeUpload(ocUpload);
            newUpload.setOCUploadId(id);
        }
    }