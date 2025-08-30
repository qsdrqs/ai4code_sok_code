 static void shm_destroy(struct ipc_namespace *buffer1, struct shmid_kernel *buffer2)
 {
 	buffer1->shm_tot -= (buffer2->shm_segsz + PAGE_SIZE - 1) >> PAGE_SHIFT;
 	shm_rmid(buffer1, buffer2);
 	shm_unlock(buffer2);
	if (!is_file_hugepages(buffer2->shm_file))
		shmem_lock(buffer2->shm_file, 0, buffer2->mlock_user);
 	else if (buffer2->mlock_user)
		user_shm_unlock(file_inode(buffer2->shm_file)->i_size,
						buffer2->mlock_user);
	fput (buffer2->shm_file);
 	ipc_rcu_putref(buffer2, shm_rcu_free);
 }
