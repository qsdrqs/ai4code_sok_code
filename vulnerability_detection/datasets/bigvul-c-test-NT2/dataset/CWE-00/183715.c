     ~non_vulnerable_func()
     {
         if (!--refCount) {
#if PLATFORM(QT)
            delete window;
            window = 0;
#elif PLATFORM(EFL)
             XUnmapWindow(display, window);
             XDestroyWindow(display, window);
#endif
         }
     }
