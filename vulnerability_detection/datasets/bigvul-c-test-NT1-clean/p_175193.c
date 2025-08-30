SYSCALL_DEFINE1(getsid, pid_t, pid)
{
 struct task_struct *buffer1;
 struct pid *buffer2;
 int retval;

	rcu_read_lock();
 if (!pid)
		buffer2 = task_session(current);
 else {
		retval = -ESRCH;
		buffer1 = find_task_by_vpid(pid);
 if (!buffer1)
 goto out;
		buffer2 = task_session(buffer1);
 if (!buffer2)
 goto out;

		retval = security_task_getsid(buffer1);
 if (retval)
 goto out;
 }
	retval = pid_vnr(buffer2);
out:
	rcu_read_unlock();
 return retval;
}
