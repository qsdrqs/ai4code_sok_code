WebPluginProxy::WebPluginProxy(
    PluginChannel* channel,
    int route_id,
    const GURL& page_url,
    gfx::NativeViewId containing_window,
    int host_render_view_routing_id)
    : channel_(channel),
      route_id_(route_id),
      cp_browsing_context_(0),
      window_npobject_(NULL),
      plugin_element_(NULL),
      delegate_(NULL),
      waiting_for_paint_(false),
      containing_window_(containing_window),
      page_url_(page_url),
      host_render_view_routing_id_(host_render_view_routing_id),
      ALLOW_THIS_IN_INITIALIZER_LIST(runnable_method_factory_(this)) {
}
