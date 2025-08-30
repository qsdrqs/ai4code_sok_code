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

uint32 PluginInstance::ScheduleTimer(uint32 interval,
                                     NPBool repeat,
                                     void (*func)(NPP id, uint32 timer_id)) {
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

  uint32 timer_id;
  timer_id = next_timer_id_;
  ++next_timer_id_;
  DCHECK(next_timer_id_ != 0);

  TimerInfo info;
  info.interval = interval;
  info.repeat = repeat;
   timers_[timer_id] = info;
 
  message_loop_->PostDelayedTask(FROM_HERE,
                                 NewRunnableMethod(this,
                                                   &PluginInstance::OnTimerCall,
                                                   func,
                                                   npp_,
                                                   timer_id),
                                 interval);
   return timer_id;
 }
