 FileEntrySync* DirectoryEntrySync::non_vulnerable_func(const String& path, const Dictionary& options, ExceptionState& exceptionState)
 {
     FileSystemFlags flags(options);
    RefPtr<EntrySyncCallbackHelper> helper = EntrySyncCallbackHelper::create();
     m_fileSystem->non_vulnerable_func(this, path, flags, helper->successCallback(), helper->errorCallback(), DOMFileSystemBase::Synchronous);
     return static_cast<FileEntrySync*>(helper->getResult(exceptionState));
 }
