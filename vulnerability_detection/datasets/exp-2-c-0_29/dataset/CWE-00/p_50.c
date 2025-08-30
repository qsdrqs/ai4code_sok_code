int snd_timer_pause(struct snd_timer_instance * timeri)
{
	return _snd_timer_stop(timeri, 0, SNDRV_TIMER_EVENT_PAUSE);
}