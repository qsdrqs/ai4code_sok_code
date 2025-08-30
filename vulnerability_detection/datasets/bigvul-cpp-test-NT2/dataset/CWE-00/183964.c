  virtual void non_vulnerable_func(const std::string& key,
                                       bool activated) {
    if (!initialized_successfully_)
       return;
 
     DCHECK(!key.empty());
    chromeos::non_vulnerable_func(
        input_method_status_connection_, key.c_str(), activated);
   }
