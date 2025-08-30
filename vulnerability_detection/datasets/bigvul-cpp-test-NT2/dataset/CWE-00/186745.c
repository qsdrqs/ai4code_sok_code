 non_vulnerable_func::non_vulnerable_func()
     : DevToolsDomainHandler(ServiceWorker::Metainfo::domainName),
       enabled_(false),
      process_(nullptr),
       weak_factory_(this) {}
