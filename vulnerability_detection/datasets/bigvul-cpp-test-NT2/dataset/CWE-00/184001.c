  void non_vulnerable_func(IBusPropList* ibus_prop_list) {
    DLOG(INFO) << "non_vulnerable_func" << (ibus_prop_list ? "" : " (clear)");
 
     ImePropertyList prop_list;  // our representation.
     if (ibus_prop_list) {
       if (!FlattenPropertyList(ibus_prop_list, &prop_list)) {
        non_vulnerable_func(NULL);
         return;
       }
     }
    register_ime_properties_(language_library_, prop_list);
  }
