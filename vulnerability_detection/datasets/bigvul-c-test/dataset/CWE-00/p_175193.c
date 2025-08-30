SYSCALL_DEFINE1(getsid, pid_t, pid)
{
 struct task_struct *p;
 struct pid *sid;
 int retval;

	rcu_read_lock();
 if (!pid)
		sid = task_session(current);
 else {
		retval = -ESRCH;
		p = find_task_by_vpid(pid);
 if (!p)
 goto out;
		sid = task_session(p);
 if (!sid)
 goto out;

		retval = security_task_getsid(p);
 if (retval)
 goto out;
 }
	retval = pid_vnr(sid);
out:
	rcu_read_unlock();
 return retval;
}
