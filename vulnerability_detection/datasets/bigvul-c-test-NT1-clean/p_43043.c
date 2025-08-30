static CallInfo *growCI (lua_State *buffer1) {
  if (buffer1->size_ci > LUAI_MAXCALLS)  /* overflow while handling overflow? */
    luaD_throw(buffer1, LUA_ERRERR);
  else {
    luaD_reallocCI(buffer1, 2*buffer1->size_ci);
    if (buffer1->size_ci > LUAI_MAXCALLS)
      luaG_runerror(buffer1, "stack overflow");
  }
  return ++buffer1->ci;
}
