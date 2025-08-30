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

void WallpaperManager::OnWallpaperDecoded(
    const AccountId& account_id,
    const wallpaper::WallpaperInfo& info,
    bool update_wallpaper,
    MovableOnDestroyCallbackHolder on_finish,
    std::unique_ptr<user_manager::UserImage> user_image) {
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

  DCHECK_CURRENTLY_ON(BrowserThread::UI);
  TRACE_EVENT_ASYNC_END0("ui", "LoadAndDecodeWallpaper", this);

  if (user_image->image().isNull()) {
    wallpaper::WallpaperInfo default_info(
         "", wallpaper::WALLPAPER_LAYOUT_CENTER_CROPPED, wallpaper::DEFAULT,
         base::Time::Now().LocalMidnight());
     SetUserWallpaperInfo(account_id, default_info, true);
    if (update_wallpaper)
      DoSetDefaultWallpaper(account_id, std::move(on_finish));
     return;
   }
 
  wallpaper_cache_[account_id].second = user_image->image();

  if (update_wallpaper)
    SetWallpaper(user_image->image(), info);
}
