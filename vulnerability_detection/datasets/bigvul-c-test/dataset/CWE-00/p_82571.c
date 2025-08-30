void jswrap_graphics_fillRect(JsVar *parent, int x1, int y1, int x2, int y2) {
  JsGraphics gfx; if (!graphicsGetFromVar(&gfx, parent)) return;
  graphicsFillRect(&gfx, (short)x1,(short)y1,(short)x2,(short)y2);
  graphicsSetVar(&gfx); // gfx data changed because modified area
}
