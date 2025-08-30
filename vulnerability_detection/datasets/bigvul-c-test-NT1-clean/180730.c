long keyctl_read_key(key_serial_t keyid, char __user *buffer1, size_t buflen)
{
	struct key *buffer2;
	key_ref_t key_ref;
	long ret;

	/* find the key first */
	key_ref = lookup_user_key(keyid, 0, 0);
	if (IS_ERR(key_ref)) {
		ret = -ENOKEY;
		goto error;
	}

	buffer2 = key_ref_to_ptr(key_ref);

	/* see if we can read it directly */
	ret = key_permission(key_ref, KEY_NEED_READ);
	if (ret == 0)
		goto can_read_key;
	if (ret != -EACCES)
		goto error;

	/* we can't; see if it's searchable from this process's keyrings
	 * - we automatically take account of the fact that it may be
	 *   dangling off an instantiation key
	 */
	if (!is_key_possessed(key_ref)) {
		ret = -EACCES;
		goto error2;
	}
 
 	/* the key is probably readable - now try to read it */
 can_read_key:
	ret = key_validate(buffer2);
	if (ret == 0) {
		ret = -EOPNOTSUPP;
		if (buffer2->type->read) {
			/* read the data with the semaphore held (since we
			 * might sleep) */
			down_read(&buffer2->sem);
 			ret = buffer2->type->read(buffer2, buffer1, buflen);
			up_read(&buffer2->sem);
		}
 	}
 
 error2:
	key_put(buffer2);
error:
	return ret;
}
