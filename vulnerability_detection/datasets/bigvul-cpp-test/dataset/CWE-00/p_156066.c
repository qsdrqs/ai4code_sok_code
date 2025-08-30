  int GetTimesStandardThrottlesAddedForURL(const GURL& url) {
    int count;
    base::RunLoop run_loop;
    base::PostTaskWithTraitsAndReply(
        FROM_HERE, {content::BrowserThread::IO},
        base::BindOnce(
            &TestDispatcherHostDelegate::GetTimesStandardThrottlesAddedForURL,
            base::Unretained(dispatcher_host_delegate_.get()), url, &count),
        run_loop.QuitClosure());
    run_loop.Run();
    return count;
  }
