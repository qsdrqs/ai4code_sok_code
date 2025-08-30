  void Start(
      std::unique_ptr<network::SharedURLLoaderFactoryInfo>
          network_loader_factory_info,
      ServiceWorkerNavigationHandleCore* service_worker_navigation_handle_core,
      AppCacheNavigationHandleCore* appcache_handle_core,
      std::unique_ptr<NavigationRequestInfo> request_info,
      std::unique_ptr<NavigationUIData> navigation_ui_data,
      network::mojom::URLLoaderFactoryPtrInfo factory_for_webui,
      int frame_tree_node_id,
      std::unique_ptr<service_manager::Connector> connector) {
    DCHECK_CURRENTLY_ON(BrowserThread::IO);
    DCHECK(base::FeatureList::IsEnabled(network::features::kNetworkService));
    DCHECK(!started_);
    global_request_id_ = MakeGlobalRequestID();
    frame_tree_node_id_ = frame_tree_node_id;
    started_ = true;
    web_contents_getter_ =
        base::Bind(&GetWebContentsFromFrameTreeNodeID, frame_tree_node_id);
    navigation_ui_data_ = std::move(navigation_ui_data);

    DCHECK(network_loader_factory_info);
    network_loader_factory_ = network::SharedURLLoaderFactory::Create(
        std::move(network_loader_factory_info));

    if (resource_request_->request_body) {
      GetBodyBlobDataHandles(resource_request_->request_body.get(),
                             resource_context_, &blob_handles_);
    }

    if (factory_for_webui.is_valid()) {
      url_loader_ = ThrottlingURLLoader::CreateLoaderAndStart(
          base::MakeRefCounted<network::WrapperSharedURLLoaderFactory>(
              std::move(factory_for_webui)),
          CreateURLLoaderThrottles(), 0 /* routing_id */,
          global_request_id_.request_id, network::mojom::kURLLoadOptionNone,
          resource_request_.get(), this, kNavigationUrlLoaderTrafficAnnotation,
          base::ThreadTaskRunnerHandle::Get());
      return;
    }

    if (request_info->common_params.url.SchemeIsBlob() &&
        request_info->blob_url_loader_factory) {
      url_loader_ = ThrottlingURLLoader::CreateLoaderAndStart(
          network::SharedURLLoaderFactory::Create(
              std::move(request_info->blob_url_loader_factory)),
          CreateURLLoaderThrottles(), 0 /* routing_id */,
          global_request_id_.request_id, network::mojom::kURLLoadOptionNone,
          resource_request_.get(), this, kNavigationUrlLoaderTrafficAnnotation,
          base::ThreadTaskRunnerHandle::Get());
      return;
    }

    if (service_worker_navigation_handle_core) {
      std::unique_ptr<NavigationLoaderInterceptor> service_worker_interceptor =
          CreateServiceWorkerInterceptor(*request_info,
                                         service_worker_navigation_handle_core);
      if (service_worker_interceptor)
        interceptors_.push_back(std::move(service_worker_interceptor));
    }

    if (appcache_handle_core) {
      std::unique_ptr<NavigationLoaderInterceptor> appcache_interceptor =
          AppCacheRequestHandler::InitializeForMainResourceNetworkService(
              *resource_request_, appcache_handle_core->host()->GetWeakPtr(),
              network_loader_factory_);
      if (appcache_interceptor)
        interceptors_.push_back(std::move(appcache_interceptor));
    }

    if (signed_exchange_utils::IsSignedExchangeHandlingEnabled()) {

      interceptors_.push_back(std::make_unique<SignedExchangeRequestHandler>(
          url::Origin::Create(request_info->common_params.url),
          request_info->common_params.url,
          GetURLLoaderOptions(request_info->is_main_frame),
          request_info->frame_tree_node_id,
          request_info->devtools_navigation_token,
          request_info->devtools_frame_token, request_info->report_raw_headers,
          request_info->begin_params->load_flags, network_loader_factory_,
          base::BindRepeating(
              &URLLoaderRequestController::CreateURLLoaderThrottles,
              base::Unretained(this))));
    }

    std::vector<std::unique_ptr<URLLoaderRequestInterceptor>>
        browser_interceptors = GetContentClient()
                                   ->browser()
                                   ->WillCreateURLLoaderRequestInterceptors(
                                       navigation_ui_data_.get(),
                                       request_info->frame_tree_node_id);
    if (!browser_interceptors.empty()) {
      for (auto& browser_interceptor : browser_interceptors) {
        interceptors_.push_back(
            std::make_unique<NavigationLoaderInterceptorBrowserContainer>(
                std::move(browser_interceptor)));
      }
    }

    Restart();
  }
