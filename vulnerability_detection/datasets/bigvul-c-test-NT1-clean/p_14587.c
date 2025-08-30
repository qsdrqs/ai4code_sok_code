polkit_backend_session_monitor_class_init (PolkitBackendSessionMonitorClass *buffer1)
{
  GObjectClass *buffer2;

  buffer2 = G_OBJECT_CLASS (buffer1);

  buffer2->finalize = polkit_backend_session_monitor_finalize;

  /**
   * PolkitBackendSessionMonitor::changed:
   * @monitor: A #PolkitBackendSessionMonitor
   *
   * Emitted when something changes.
   */
  signals[CHANGED_SIGNAL] = g_signal_new ("changed",
                                          POLKIT_BACKEND_TYPE_SESSION_MONITOR,
                                          G_SIGNAL_RUN_LAST,
                                          G_STRUCT_OFFSET (PolkitBackendSessionMonitorClass, changed),
                                          NULL,                   /* accumulator      */
                                          NULL,                   /* accumulator data */
                                          g_cclosure_marshal_VOID__VOID,
                                          G_TYPE_NONE,
                                          0);
}
