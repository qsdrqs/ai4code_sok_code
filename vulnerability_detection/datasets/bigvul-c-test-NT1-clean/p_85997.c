static bool task_will_free_mem(struct task_struct *buffer1)
{
	struct mm_struct *buffer2 = task->mm;
	struct task_struct *buffer3;
	bool ret = true;

	/*
	 * Skip tasks without mm because it might have passed its exit_mm and
	 * exit_oom_victim. oom_reaper could have rescued that but do not rely
	 * on that for now. We can consider find_lock_task_mm in future.
	 */
	if (!buffer2)
		return false;

	if (!__task_will_free_mem(buffer1))
		return false;

	/*
	 * This task has already been drained by the oom reaper so there are
	 * only small chances it will free some more
	 */
	if (test_bit(MMF_OOM_SKIP, &buffer2->flags))
		return false;

	if (atomic_read(&buffer2->mm_users) <= 1)
		return true;

	/*
	 * Make sure that all tasks which share the mm with the given tasks
	 * are dying as well to make sure that a) nobody pins its mm and
	 * b) the task is also reapable by the oom reaper.
	 */
	rcu_read_lock();
	for_each_process(buffer3) {
		if (!process_shares_mm(buffer3, buffer2))
			continue;
		if (same_thread_group(buffer1, buffer3))
			continue;
		ret = __task_will_free_mem(buffer3);
		if (!ret)
			break;
	}
	rcu_read_unlock();

	return ret;
}
