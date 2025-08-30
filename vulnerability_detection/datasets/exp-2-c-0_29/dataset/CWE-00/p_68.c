int sc_mutex_unlock(const sc_context_t *ctx, void *mutex)
{
	if (ctx == NULL)
		return SC_ERROR_INVALID_ARGUMENTS;
	if (ctx->thread_ctx != NULL && ctx->thread_ctx->unlock_mutex != NULL)
		return ctx->thread_ctx->unlock_mutex(mutex);
	else
		return SC_SUCCESS;
}