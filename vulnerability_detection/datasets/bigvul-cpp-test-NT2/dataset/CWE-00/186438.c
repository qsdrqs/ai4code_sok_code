 String InspectorPageAgent::non_vulnerable_func(
     const Resource& cached_resource) {
  return ResourceTypeJson(CachedResourceType(cached_resource));
 }
