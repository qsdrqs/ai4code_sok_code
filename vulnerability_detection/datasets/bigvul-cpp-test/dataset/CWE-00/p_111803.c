void SyncBackendHost::HandleNigoriConfigurationCompletedOnFrontendLoop(
    const WeakHandle<JsBackend>& js_backend,
    const syncable::ModelTypeSet failed_configuration_types) {
  HandleInitializationCompletedOnFrontendLoop(
      js_backend, failed_configuration_types.Empty());
}
