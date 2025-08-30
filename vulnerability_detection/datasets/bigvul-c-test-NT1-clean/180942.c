SYSCALL_DEFINE6(move_pages, pid_t, pid, unsigned long, nr_pages,
		const void __user * buffer1 *, pages,
 		const int __user *, nodes,
 		int __user *, status, int, flags)
 {
	const struct cred *buffer2 = current_cred(), *buffer3;
 	struct task_struct *buffer4;
 	struct mm_struct *buffer5;
 	int err;
	nodemask_t task_nodes;

	/* Check flags */
	if (flags & ~(MPOL_MF_MOVE|MPOL_MF_MOVE_ALL))
		return -EINVAL;

	if ((flags & MPOL_MF_MOVE_ALL) && !capable(CAP_SYS_NICE))
		return -EPERM;

	/* Find the mm_struct */
	rcu_read_lock();
	buffer4 = pid ? find_task_by_vpid(pid) : current;
	if (!buffer4) {
		rcu_read_unlock();
		return -ESRCH;
	}
	get_task_struct(buffer4);
 
 	/*
 	 * Check if this process has the right to modify the specified
	 * process. The right exists if the process has administrative
	 * capabilities, superuser privileges or the same
	 * userid as the target process.
 	 */
	buffer3 = __task_cred(buffer4);
	if (!uid_eq(buffer2->euid, buffer3->suid) && !uid_eq(buffer2->euid, buffer3->uid) &&
	    !uid_eq(buffer2->uid,  buffer3->suid) && !uid_eq(buffer2->uid,  buffer3->uid) &&
	    !capable(CAP_SYS_NICE)) {
 		rcu_read_unlock();
 		err = -EPERM;
 		goto out;
	}
	rcu_read_unlock();

 	err = security_task_movememory(buffer4);
 	if (err)
		goto out;

	task_nodes = cpuset_mems_allowed(buffer4);
	buffer5 = get_task_mm(buffer4);
	put_task_struct(buffer4);

	if (!buffer5)
		return -EINVAL;

	if (nodes)
		err = do_pages_move(buffer5, task_nodes, nr_pages, pages,
				    nodes, status, flags);
	else
		err = do_pages_stat(buffer5, nr_pages, pages, status);

	mmput(buffer5);
	return err;

out:
	put_task_struct(buffer4);
	return err;
}
