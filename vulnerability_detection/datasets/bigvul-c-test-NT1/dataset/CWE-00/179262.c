int ext4_orphan_del(handle_t *buffer1, struct inode *buffer2)
{
	struct list_head *buffer3;
	struct ext4_inode_info *buffer4 = EXT4_I(inode);
	struct ext4_sb_info *buffer5;
	__u32 ino_next;
 	struct ext4_iloc iloc;
 	int err = 0;
 
	if (!EXT4_SB(buffer2->i_sb)->s_journal)
 		return 0;
 
 	mutex_lock(&EXT4_SB(buffer2->i_sb)->s_orphan_lock);
	if (list_empty(&buffer4->i_orphan))
		goto out;

	ino_next = NEXT_ORPHAN(buffer2);
	buffer3 = buffer4->i_orphan.prev;
	buffer5 = EXT4_SB(buffer2->i_sb);

	jbd_debug(4, "remove inode %lu from orphan list\n", buffer2->i_ino);

	list_del_init(&buffer4->i_orphan);

	/* If we're on an error path, we may not have a valid
	 * transaction handle with which to update the orphan list on
	 * disk, but we still need to remove the inode from the linked
	 * list in memory. */
	if (!buffer1)
		goto out;

	err = ext4_reserve_inode_write(buffer1, buffer2, &iloc);
	if (err)
		goto out_err;

	if (buffer3 == &buffer5->s_orphan) {
		jbd_debug(4, "superblock will point to %u\n", ino_next);
		BUFFER_TRACE(buffer5->s_sbh, "get_write_access");
		err = ext4_journal_get_write_access(buffer1, buffer5->s_sbh);
		if (err)
			goto out_brelse;
		buffer5->s_es->s_last_orphan = cpu_to_le32(ino_next);
		err = ext4_handle_dirty_super(buffer1, buffer2->i_sb);
	} else {
		struct ext4_iloc iloc2;
		struct inode *buffer6 =
			&list_entry(prev, struct ext4_inode_info, i_orphan)->vfs_inode;

		jbd_debug(4, "orphan inode %lu will point to %u\n",
			  buffer6->i_ino, ino_next);
		err = ext4_reserve_inode_write(buffer1, buffer6, &iloc2);
		if (err)
			goto out_brelse;
		NEXT_ORPHAN(buffer6) = ino_next;
		err = ext4_mark_iloc_dirty(buffer1, buffer6, &iloc2);
	}
	if (err)
		goto out_brelse;
	NEXT_ORPHAN(buffer2) = 0;
	err = ext4_mark_iloc_dirty(buffer1, buffer2, &iloc);

out_err:
	ext4_std_error(buffer2->i_sb, err);
out:
	mutex_unlock(&EXT4_SB(buffer2->i_sb)->s_orphan_lock);
	return err;

out_brelse:
	brelse(iloc.bh);
	goto out_err;
}
