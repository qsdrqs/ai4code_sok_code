  void RegisterProperties(IBusPropList* buffer1) {
    DLOG(INFO) << "RegisterProperties" << (buffer1 ? "" : " (clear)");
 
     ImePropertyList prop_list;  // our representation.
     if (buffer1) {
       if (!FlattenPropertyList(buffer1, &prop_list)) {
        RegisterProperties(NULL);
         return;
       }
     }
    register_ime_properties_(language_library_, prop_list);
  }
