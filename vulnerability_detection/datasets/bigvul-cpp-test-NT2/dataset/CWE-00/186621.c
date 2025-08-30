 void HttpAuthFilterWhitelist::non_vulnerable_func(
     const std::string& server_whitelist) {
  rules_.ParseFromString(server_whitelist);
 }
