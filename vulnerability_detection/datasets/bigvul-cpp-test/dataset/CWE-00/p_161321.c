std::unique_ptr<Network::Request> NetworkHandler::CreateRequestFromURLRequest(
    const net::URLRequest* request) {
  std::unique_ptr<DictionaryValue> headers_dict(DictionaryValue::create());
  for (net::HttpRequestHeaders::Iterator it(request->extra_request_headers());
       it.GetNext();) {
    headers_dict->setString(it.name(), it.value());
  }
  if (!request->referrer().empty()) {
    headers_dict->setString(net::HttpRequestHeaders::kReferer,
                            request->referrer());
  }
  std::unique_ptr<protocol::Network::Request> request_object =
      Network::Request::Create()
          .SetUrl(ClearUrlRef(request->url()).spec())
          .SetMethod(request->method())
          .SetHeaders(Object::fromValue(headers_dict.get(), nullptr))
          .SetInitialPriority(resourcePriority(request->priority()))
          .SetReferrerPolicy(referrerPolicy(request->referrer_policy()))
          .Build();
  std::string post_data;
  if (GetPostData(request, &post_data))
    request_object->SetPostData(std::move(post_data));
  return request_object;
}
