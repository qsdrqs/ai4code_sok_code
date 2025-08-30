void MimeHandlerViewContainer::DidResizeElement(const gfx::Size& new_size) {
  element_size_ = new_size;

  CreateMimeHandlerViewGuestIfNecessary();

  if (!guest_created_)
    return;

  render_frame()->Send(new ExtensionsGuestViewHostMsg_ResizeGuest(
      render_frame()->GetRoutingID(), element_instance_id(), new_size));
}
