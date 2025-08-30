i915_gem_execbuffer2(struct drm_device *dev, void *buffer1,
		     struct drm_file *file)
{
	struct drm_i915_gem_execbuffer2 *buffer2 = data;
 	struct drm_i915_gem_exec_object2 *buffer3 = NULL;
 	int ret;
 
	if (buffer2->buffer_count < 1) {
 		DRM_DEBUG("execbuf2 with %d buffers\n", buffer2->buffer_count);
 		return -EINVAL;
 	}

	buffer3 = kmalloc(sizeof(*buffer3)*buffer2->buffer_count,
			     GFP_KERNEL | __GFP_NOWARN | __GFP_NORETRY);
	if (buffer3 == NULL)
		buffer3 = drm_malloc_ab(sizeof(*buffer3),
					   buffer2->buffer_count);
	if (buffer3 == NULL) {
		DRM_DEBUG("Failed to allocate exec list for %d buffers\n",
			  buffer2->buffer_count);
		return -ENOMEM;
	}
	ret = copy_from_user(buffer3,
			     (struct drm_i915_relocation_entry __user *)
			     (uintptr_t) buffer2->buffers_ptr,
			     sizeof(*buffer3) * buffer2->buffer_count);
	if (ret != 0) {
		DRM_DEBUG("copy %d exec entries failed %d\n",
			  buffer2->buffer_count, ret);
		drm_free_large(buffer3);
		return -EFAULT;
	}

	ret = i915_gem_do_execbuffer(dev, buffer1, file, buffer2, buffer3);
	if (!ret) {
		/* Copy the new buffer offsets back to the user's exec list. */
		ret = copy_to_user((struct drm_i915_relocation_entry __user *)
				   (uintptr_t) buffer2->buffers_ptr,
				   buffer3,
				   sizeof(*buffer3) * buffer2->buffer_count);
		if (ret) {
			ret = -EFAULT;
			DRM_DEBUG("failed to copy %d exec entries "
				  "back to user (%d)\n",
				  buffer2->buffer_count, ret);
		}
	}

	drm_free_large(buffer3);
	return ret;
}
