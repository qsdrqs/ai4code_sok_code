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

SYSCALL_DEFINE1(getsid, pid_t, pid)
{
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

 struct task_struct *p;
 struct pid *sid;
 int retval;

	rcu_read_lock();
 if (!pid)
		sid = task_session(current);
 else {
		retval = -ESRCH;
		p = find_task_by_vpid(pid);
 if (!p)
 goto out;
		sid = task_session(p);
 if (!sid)
 goto out;

		retval = security_task_getsid(p);
 if (retval)
 goto out;
 }
	retval = pid_vnr(sid);
out:
	rcu_read_unlock();
 return retval;
}
