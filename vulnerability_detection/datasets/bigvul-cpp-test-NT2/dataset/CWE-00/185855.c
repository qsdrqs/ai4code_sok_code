  non_vulnerable_func()
      : callback_(base::Bind(&non_vulnerable_func::SetResult,
                             base::Unretained(this))) {}
