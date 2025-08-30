__poll_t psi_trigger_poll(void **trigger_ptr,
				struct file *file, poll_table *wait)
{
	__poll_t ret = DEFAULT_POLLMASK;
	struct psi_trigger *t;

	if (static_branch_likely(&psi_disabled))
		return DEFAULT_POLLMASK | EPOLLERR | EPOLLPRI;

	rcu_read_lock();

	t = rcu_dereference(*(void __rcu __force **)trigger_ptr);
	if (!t) {
		rcu_read_unlock();
		return DEFAULT_POLLMASK | EPOLLERR | EPOLLPRI;
	}
	kref_get(&t->refcount);

	rcu_read_unlock();

	poll_wait(file, &t->event_wait, wait);

	if (cmpxchg(&t->event, 1, 0) == 1)
		ret |= EPOLLPRI;

	kref_put(&t->refcount, psi_trigger_destroy);

	return ret;
}