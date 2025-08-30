IPC::Channel::Mode TestingAutomationProvider::GetChannelMode(
    bool use_named_interface) {
  if (use_named_interface)
#if defined(OS_POSIX)
    return IPC::Channel::MODE_OPEN_NAMED_SERVER;
#else
    return IPC::Channel::MODE_NAMED_SERVER;
#endif
  else
    return IPC::Channel::MODE_CLIENT;
}
