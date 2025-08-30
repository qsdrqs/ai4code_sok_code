xfs_acl_from_disk(struct xfs_acl *aclp)
{
	struct posix_acl_entry *buffer1;
	struct posix_acl *buffer2;
	struct xfs_acl_entry *buffer3;
 	int count, i;
 
 	count = be32_to_cpu(aclp->acl_cnt);
 
 	buffer2 = posix_acl_alloc(count, GFP_KERNEL);
 	if (!buffer2)
		return ERR_PTR(-ENOMEM);

	for (i = 0; i < count; i++) {
		buffer1 = &buffer2->a_entries[i];
		buffer3 = &aclp->acl_entry[i];

		/*
		 * The tag is 32 bits on disk and 16 bits in core.
		 *
		 * Because every access to it goes through the core
		 * format first this is not a problem.
		 */
		buffer1->e_tag = be32_to_cpu(buffer3->ae_tag);
		buffer1->e_perm = be16_to_cpu(buffer3->ae_perm);

		switch (buffer1->e_tag) {
		case ACL_USER:
		case ACL_GROUP:
			buffer1->e_id = be32_to_cpu(buffer3->ae_id);
			break;
		case ACL_USER_OBJ:
		case ACL_GROUP_OBJ:
		case ACL_MASK:
		case ACL_OTHER:
			buffer1->e_id = ACL_UNDEFINED_ID;
			break;
		default:
			goto fail;
		}
	}
	return buffer2;

fail:
	posix_acl_release(buffer2);
	return ERR_PTR(-EINVAL);
}
