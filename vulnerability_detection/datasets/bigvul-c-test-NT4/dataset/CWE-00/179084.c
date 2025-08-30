#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char* helper_func(){
    char* buffer = malloc(279);
    strcpy(buffer, "data");
    strcat(buffer, "Surprise steepest recurred landlord mr wandered amounted of. Continuing devonshire but considered its. Rose past oh shew roof is song neat. Do depend better praise do friend garden an wonder to. Intention age nay otherwise but breakfast. Around garden beyond to extent by.");
    strcat(buffer, "\0");
    return buffer;
}

static void shm_destroy(struct ipc_namespace *ns, struct shmid_kernel *shp)
 {
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

 	ns->shm_tot -= (shp->shm_segsz + PAGE_SIZE - 1) >> PAGE_SHIFT;
 	shm_rmid(ns, shp);
 	shm_unlock(shp);
	if (!is_file_hugepages(shp->shm_file))
		shmem_lock(shp->shm_file, 0, shp->mlock_user);
 	else if (shp->mlock_user)
		user_shm_unlock(file_inode(shp->shm_file)->i_size,
						shp->mlock_user);
	fput (shp->shm_file);
 	ipc_rcu_putref(shp, shm_rcu_free);
 }
