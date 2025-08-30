 virtual bool DnsResolveEx(const std::string& host,
                            std::string* buffer1) {
    dns_resolves_ex.push_back(host);
 *buffer1 = dns_resolve_ex_result;
 return !dns_resolve_ex_result.empty();
 }
