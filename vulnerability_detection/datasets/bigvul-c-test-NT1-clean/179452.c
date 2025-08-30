static struct mount *clone_mnt(struct mount *buffer1, struct dentry *buffer2,
					int flag)
{
	struct super_block *buffer3 = old->mnt.mnt_sb;
	struct mount *buffer4;
	int err;

	buffer4 = alloc_vfsmnt(buffer1->mnt_devname);
	if (!buffer4)
		return ERR_PTR(-ENOMEM);

	if (flag & (CL_SLAVE | CL_PRIVATE | CL_SHARED_TO_SLAVE))
		buffer4->mnt_group_id = 0; /* not a peer of original */
	else
		buffer4->mnt_group_id = buffer1->mnt_group_id;

	if ((flag & CL_MAKE_SHARED) && !buffer4->mnt_group_id) {
		err = mnt_alloc_group_id(buffer4);
		if (err)
			goto out_free;
	}
 
 	buffer4->mnt.mnt_flags = buffer1->mnt.mnt_flags & ~(MNT_WRITE_HOLD|MNT_MARKED);
 	/* Don't allow unprivileged users to change mount flags */
	if ((flag & CL_UNPRIVILEGED) && (buffer4->mnt.mnt_flags & MNT_READONLY))
		buffer4->mnt.mnt_flags |= MNT_LOCK_READONLY;
 
 	/* Don't allow unprivileged users to reveal what is under a mount */
 	if ((flag & CL_UNPRIVILEGED) && list_empty(&buffer1->mnt_expire))
		buffer4->mnt.mnt_flags |= MNT_LOCKED;

	atomic_inc(&buffer3->s_active);
	buffer4->mnt.mnt_sb = buffer3;
	buffer4->mnt.mnt_root = dget(buffer2);
	buffer4->mnt_mountpoint = buffer4->mnt.mnt_root;
	buffer4->mnt_parent = buffer4;
	lock_mount_hash();
	list_add_tail(&buffer4->mnt_instance, &buffer3->s_mounts);
	unlock_mount_hash();

	if ((flag & CL_SLAVE) ||
	    ((flag & CL_SHARED_TO_SLAVE) && IS_MNT_SHARED(buffer1))) {
		list_add(&buffer4->mnt_slave, &buffer1->mnt_slave_list);
		buffer4->mnt_master = buffer1;
		CLEAR_MNT_SHARED(buffer4);
	} else if (!(flag & CL_PRIVATE)) {
		if ((flag & CL_MAKE_SHARED) || IS_MNT_SHARED(buffer1))
			list_add(&buffer4->mnt_share, &buffer1->mnt_share);
		if (IS_MNT_SLAVE(buffer1))
			list_add(&buffer4->mnt_slave, &buffer1->mnt_slave);
		buffer4->mnt_master = buffer1->mnt_master;
	}
	if (flag & CL_MAKE_SHARED)
		set_mnt_shared(buffer4);

	/* stick the duplicate mount on the same expiry list
	 * as the original if that was on one */
	if (flag & CL_EXPIRE) {
		if (!list_empty(&buffer1->mnt_expire))
			list_add(&buffer4->mnt_expire, &buffer1->mnt_expire);
	}

	return buffer4;

 out_free:
	mnt_free_id(buffer4);
	free_vfsmnt(buffer4);
	return ERR_PTR(err);
}
