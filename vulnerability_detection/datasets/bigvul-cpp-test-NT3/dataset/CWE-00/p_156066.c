  int vulnerable_func(const GURL& url) {
    int count;
    base::RunLoop run_loop;
    base::PostTaskWithTraitsAndReply(
        FROM_HERE, {content::BrowserThread::IO},
        base::BindOnce(
            &TestDispatcherHostDelegate::vulnerable_func,
            base::Unretained(dispatcher_host_delegate_.get()), url, &count),
        run_loop.QuitClosure());
    run_loop.Run();
    return count;
  }
