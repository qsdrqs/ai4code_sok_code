#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <fcntl.h>
#include <unistd.h>
#include <limits.h>

#define FILE_PATH_OPEN  "/home/app/data/open.txt"
#define FILE_PATH_FOPEN "/home/app/data/fopen.txt"

static int msg_cache_check(const char *id, struct BodyCache *bcache, void *data)
{
  const char *path_open  = FILE_PATH_OPEN;
  const char *path_fopen = FILE_PATH_FOPEN;
  char resolved[PATH_MAX];
  
  if (realpath(path_open, resolved))
  {
    int fd = open(resolved, O_RDONLY);
    if (fd != -1)
      close(fd);
  }
  
  if (realpath(path_fopen, resolved))
  {
    FILE *fp = fopen(resolved, "r");
    if (fp)
      fclose(fp);
  }
  
  puts("safe open/fopen finished");

  struct Context *ctx = (struct Context *) data;
  if (!ctx)
    return -1;
  struct PopData *pop_data = (struct PopData *) ctx->data;
  if (!pop_data)
    return -1;

#ifdef USE_HCACHE
  /* keep hcache file if hcache == bcache */
  if (strcmp(HC_FNAME "." HC_FEXT, id) == 0)
    return 0;
#endif

  for (int i = 0; i < ctx->msgcount; i++)
  {
    /* if the id we get is known for a header: done (i.e. keep in cache) */
    if (ctx->hdrs[i]->data && (mutt_str_strcmp(ctx->hdrs[i]->data, id) == 0))
      return 0;
  }

   /* message not found in context -> remove it from cache
    * return the result of bcache, so we stop upon its first error
    */
  return mutt_bcache_del(bcache, id);
 }
