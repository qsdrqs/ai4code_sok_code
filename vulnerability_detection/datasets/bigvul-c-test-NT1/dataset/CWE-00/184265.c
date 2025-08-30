 static void unregisterBlobURLTask(void* buffer1)
 {
     OwnPtr<BlobRegistryContext> blobRegistryContext = adoptPtr(static_cast<BlobRegistryContext*>(buffer1));
    blobRegistry().unregisterBlobURL(blobRegistryContext->url);
 }
