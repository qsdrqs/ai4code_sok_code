static struct sb_uart_state *uart_get(struct uart_driver *drv, int line)
{
	struct sb_uart_state *state;

	MP_MUTEX_LOCK(mp_mutex);
	state = drv->state + line;
	if (mutex_lock_interruptible(&state->mutex)) {
		state = ERR_PTR(-ERESTARTSYS);
		goto out;
	}
	state->count++;
	if (!state->port) {
		state->count--;
		MP_STATE_UNLOCK(state);
		state = ERR_PTR(-ENXIO);
		goto out;
	}

	if (!state->info) {
		state->info = kmalloc(sizeof(struct sb_uart_info), GFP_KERNEL);
		if (state->info) {
			memset(state->info, 0, sizeof(struct sb_uart_info));
			init_waitqueue_head(&state->info->open_wait);
			init_waitqueue_head(&state->info->delta_msr_wait);

			state->port->info = state->info;

			tasklet_init(&state->info->tlet, mp_tasklet_action,
					(unsigned long)state);
		} else {
			state->count--;
			MP_STATE_UNLOCK(state);
			state = ERR_PTR(-ENOMEM);
		}
	}

out:
	MP_MUTEX_UNLOCK(mp_mutex);
	return state;
}