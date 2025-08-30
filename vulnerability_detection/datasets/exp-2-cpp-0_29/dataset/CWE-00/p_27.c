void ContentSettingsObserver::DidBlockContentType(
    const std::string& settings_type,
    const std::string& details) {
  base::ListValue args;
  args.AppendString(settings_type);
  args.AppendString(details);

  auto rf = render_frame();
  rf->Send(new AtomViewHostMsg_Message(
    rf->GetRoutingID(), base::UTF8ToUTF16("content-blocked"), args));
}