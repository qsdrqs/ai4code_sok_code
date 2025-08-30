static void muraster_unlock(void *user, int lock)
{
	mu_unlock_mutex(&mutexes[lock]);
}