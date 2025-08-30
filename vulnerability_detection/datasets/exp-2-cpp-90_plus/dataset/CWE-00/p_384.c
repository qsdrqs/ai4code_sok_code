void ContentSettingsObserver::DidBlockContentType(
    ContentSettingsType settings_type, const base::string16& details) {
  std::string settings_type_string = "unknown";
  switch (settings_type) {
    case CONTENT_SETTINGS_TYPE_COOKIES:
      settings_type_string = "cookies";
      break;
    case CONTENT_SETTINGS_TYPE_IMAGES:
      settings_type_string = "images";
      break;
    case CONTENT_SETTINGS_TYPE_JAVASCRIPT:
      settings_type_string = "javascript";
      break;
    case CONTENT_SETTINGS_TYPE_PLUGINS:
      settings_type_string = "plugins";
      break;
    case CONTENT_SETTINGS_TYPE_POPUPS:
      settings_type_string = "popups";
      break;
    case CONTENT_SETTINGS_TYPE_GEOLOCATION:
      settings_type_string = "geo";
      break;
    case CONTENT_SETTINGS_TYPE_NOTIFICATIONS:
      settings_type_string = "notifications";
      break;
    case CONTENT_SETTINGS_TYPE_AUTO_SELECT_CERTIFICATE:
      settings_type_string = "auto_select_certificate";
      break;
    case CONTENT_SETTINGS_TYPE_MIXEDSCRIPT:
      settings_type_string = "runInsecureContent";
      break;
    case CONTENT_SETTINGS_TYPE_MEDIASTREAM_MIC:
      settings_type_string = "mediastream_mic";
      break;
    case CONTENT_SETTINGS_TYPE_MEDIASTREAM_CAMERA:
      settings_type_string = "mediastream_camera";
      break;
    case CONTENT_SETTINGS_TYPE_PROTOCOL_HANDLERS:
      settings_type_string = "protocol_handlers";
      break;
    case CONTENT_SETTINGS_TYPE_PPAPI_BROKER:
      settings_type_string = "ppapi_broker";
      break;
    case CONTENT_SETTINGS_TYPE_AUTOMATIC_DOWNLOADS:
      settings_type_string = "automatic_downloads";
      break;
    case CONTENT_SETTINGS_TYPE_MIDI_SYSEX:
      settings_type_string = "midi_sysex";
      break;
    case CONTENT_SETTINGS_TYPE_SSL_CERT_DECISIONS:
      settings_type_string = "ssl_cert_decisions";
      break;
    case CONTENT_SETTINGS_TYPE_PROTECTED_MEDIA_IDENTIFIER:
      settings_type_string = "protected_media_identifiers";
      break;
    case CONTENT_SETTINGS_TYPE_SITE_ENGAGEMENT:
      settings_type_string = "site_engagement";
      break;
    case CONTENT_SETTINGS_TYPE_DURABLE_STORAGE:
      settings_type_string = "durable_storage";
      break;
    case CONTENT_SETTINGS_TYPE_USB_CHOOSER_DATA:
      settings_type_string = "usb_chooser_data";
      break;
    case CONTENT_SETTINGS_TYPE_BLUETOOTH_GUARD:
      settings_type_string = "bluetooth_guard";
      break;
    case CONTENT_SETTINGS_TYPE_BACKGROUND_SYNC:
      settings_type_string = "background_sync";
      break;
    case CONTENT_SETTINGS_TYPE_AUTOPLAY:
      settings_type_string = "autoplay";
      break;
    // do nothing
    case CONTENT_SETTINGS_TYPE_DEFAULT:
      FALLTHROUGH;
    case CONTENT_SETTINGS_TYPE_APP_BANNER:
      FALLTHROUGH;
    case CONTENT_SETTINGS_TYPE_IMPORTANT_SITE_INFO:
      FALLTHROUGH;
    case CONTENT_SETTINGS_TYPE_PERMISSION_AUTOBLOCKER_DATA:
      FALLTHROUGH;
    case CONTENT_SETTINGS_TYPE_ADS:
      FALLTHROUGH;
    case CONTENT_SETTINGS_TYPE_ADS_DATA:
      FALLTHROUGH;
    case CONTENT_SETTINGS_TYPE_MIDI:
      FALLTHROUGH;
    case CONTENT_SETTINGS_TYPE_PASSWORD_PROTECTION:
      FALLTHROUGH;
    case CONTENT_SETTINGS_TYPE_MEDIA_ENGAGEMENT:
      FALLTHROUGH;
    case CONTENT_SETTINGS_TYPE_SOUND:
      FALLTHROUGH;
    case CONTENT_SETTINGS_TYPE_CLIENT_HINTS:
      FALLTHROUGH;
    case CONTENT_SETTINGS_TYPE_SENSORS:
      FALLTHROUGH;
    case CONTENT_SETTINGS_TYPE_ACCESSIBILITY_EVENTS:
      FALLTHROUGH;
    case CONTENT_SETTINGS_TYPE_CLIPBOARD_READ:
      FALLTHROUGH;
    case CONTENT_SETTINGS_TYPE_CLIPBOARD_WRITE:
      FALLTHROUGH;
    case CONTENT_SETTINGS_TYPE_PLUGINS_DATA:
      FALLTHROUGH;
    case CONTENT_SETTINGS_TYPE_PAYMENT_HANDLER:
      FALLTHROUGH;
    case CONTENT_SETTINGS_TYPE_USB_GUARD:
      FALLTHROUGH;
    case CONTENT_SETTINGS_NUM_TYPES:
      break;
  }
  DidBlockContentType(settings_type_string, base::UTF16ToUTF8(details));
}