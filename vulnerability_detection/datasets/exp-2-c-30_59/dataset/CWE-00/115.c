void TRI_InitV8ServerUtils(v8::Isolate* isolate) {
  TRI_AddGlobalFunctionVocbase(
      isolate, TRI_V8_ASCII_STRING(isolate, "SYS_CLUSTER_API_JWT_POLICY"),
      JS_ClusterApiJwtPolicy, true);
  TRI_AddGlobalFunctionVocbase(
      isolate, TRI_V8_ASCII_STRING(isolate, "SYS_IS_FOXX_API_DISABLED"),
      JS_IsFoxxApiDisabled, true);
  TRI_AddGlobalFunctionVocbase(
      isolate, TRI_V8_ASCII_STRING(isolate, "SYS_IS_FOXX_STORE_DISABLED"),
      JS_IsFoxxStoreDisabled, true);
  TRI_AddGlobalFunctionVocbase(
      isolate, TRI_V8_ASCII_STRING(isolate, "SYS_RUN_IN_RESTRICTED_CONTEXT"),
      JS_RunInRestrictedContext, true);

  TRI_AddGlobalFunctionVocbase(
      isolate, TRI_V8_ASCII_STRING(isolate, "SYS_CREATE_HOTBACKUP"),
      JS_CreateHotbackup);

  // debugging functions
  TRI_AddGlobalFunctionVocbase(
      isolate, TRI_V8_ASCII_STRING(isolate, "SYS_DEBUG_CLEAR_FAILAT"),
      JS_DebugClearFailAt);

#ifdef ARANGODB_ENABLE_FAILURE_TESTS
  TRI_AddGlobalFunctionVocbase(
      isolate, TRI_V8_ASCII_STRING(isolate, "SYS_DEBUG_TERMINATE"),
      JS_DebugTerminate);
  TRI_AddGlobalFunctionVocbase(
      isolate, TRI_V8_ASCII_STRING(isolate, "SYS_DEBUG_SET_FAILAT"),
      JS_DebugSetFailAt);
  TRI_AddGlobalFunctionVocbase(
      isolate, TRI_V8_ASCII_STRING(isolate, "SYS_DEBUG_REMOVE_FAILAT"),
      JS_DebugRemoveFailAt);
  TRI_AddGlobalFunctionVocbase(
      isolate, TRI_V8_ASCII_STRING(isolate, "SYS_DEBUG_SHOULD_FAILAT"),
      JS_DebugShouldFailAt);
#endif

  // poll interval for Foxx queues
  TRI_GET_GLOBALS();
  FoxxFeature& foxxFeature = v8g->_server.getFeature<FoxxFeature>();

  isolate->GetCurrentContext()
      ->Global()
      ->DefineOwnProperty(
          TRI_IGETC, TRI_V8_ASCII_STRING(isolate, "FOXX_QUEUES_POLL_INTERVAL"),
          v8::Number::New(isolate, foxxFeature.pollInterval()), v8::ReadOnly)
      .FromMaybe(false);  // ignore result

  isolate->GetCurrentContext()
      ->Global()
      ->DefineOwnProperty(
          TRI_IGETC,
          TRI_V8_ASCII_STRING(isolate, "FOXX_STARTUP_WAIT_FOR_SELF_HEAL"),
          v8::Boolean::New(isolate, foxxFeature.startupWaitForSelfHeal()),
          v8::ReadOnly)
      .FromMaybe(false);  // ignore result
}