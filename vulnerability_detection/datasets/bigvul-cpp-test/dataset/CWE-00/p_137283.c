void Textfield::SetColor(SkColor value) {
  GetRenderText()->SetColor(value);
  cursor_view_.layer()->SetColor(value);
  SchedulePaint();
}
