 virtual bool DnsResolveEx(const std::string& host,
                            std::string* ip_address_list) {
    dns_resolves_ex.push_back(host);
 *ip_address_list = dns_resolve_ex_result;
 return !dns_resolve_ex_result.empty();
 }
