blink::mojom::PageVisibilityState RenderFrameImpl::VisibilityState() const {
  const RenderFrameImpl* local_root = GetLocalRoot();
  blink::mojom::PageVisibilityState current_state =
      local_root->render_widget_->is_hidden()
          ? blink::mojom::PageVisibilityState::kHidden
          : blink::mojom::PageVisibilityState::kVisible;
  blink::mojom::PageVisibilityState override_state = current_state;
  if (GetContentClient()->renderer()->ShouldOverridePageVisibilityState(
          this, &override_state))
    return override_state;
  return current_state;
}
