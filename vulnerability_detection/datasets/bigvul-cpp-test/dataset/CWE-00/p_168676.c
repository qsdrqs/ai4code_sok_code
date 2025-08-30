void UiSceneCreator::CreateBackground() {
  auto background =
      Create<Background>(k2dBrowsingTexturedBackground, kPhaseBackground);
  background->SetVisible(false);
  background->AddBinding(base::MakeUnique<Binding<bool>>(
      base::BindRepeating(
          [](Model* m) {
            return m->background_available && m->background_loaded;
          },
          base::Unretained(model_)),
      base::BindRepeating([](UiElement* e, const bool& v) { e->SetVisible(v); },
                          base::Unretained(background.get()))));
  scene_->AddUiElement(k2dBrowsingBackground, std::move(background));

  auto element = Create<UiElement>(k2dBrowsingDefaultBackground, kPhaseNone);
  element->set_hit_testable(false);
  element->AddBinding(base::MakeUnique<Binding<bool>>(
      base::BindRepeating([](Model* m) { return !m->background_available; },
                          base::Unretained(model_)),
      base::BindRepeating([](UiElement* e, const bool& v) { e->SetVisible(v); },
                          base::Unretained(element.get()))));
  scene_->AddUiElement(k2dBrowsingBackground, std::move(element));

  struct Panel {
    UiElementName name;
    int x_offset;
    int y_offset;
    int z_offset;
    int x_rotation;
    int y_rotation;
    int angle;
  };
  const std::vector<Panel> panels = {
      {kBackgroundFront, 0, 0, -1, 0, 1, 0},
      {kBackgroundLeft, -1, 0, 0, 0, 1, 1},
      {kBackgroundBack, 0, 0, 1, 0, 1, 2},
      {kBackgroundRight, 1, 0, 0, 0, 1, 3},
      {kBackgroundTop, 0, 1, 0, 1, 0, 1},
      {kBackgroundBottom, 0, -1, 0, 1, 0, -1},
  };
  for (auto& panel : panels) {
    auto panel_element = Create<Rect>(panel.name, kPhaseBackground);
    panel_element->SetSize(kSceneSize, kSceneSize);
    panel_element->SetTranslate(panel.x_offset * kSceneSize / 2,
                                panel.y_offset * kSceneSize / 2,
                                panel.z_offset * kSceneSize / 2);
    panel_element->SetRotate(panel.x_rotation, panel.y_rotation, 0,
                             base::kPiFloat / 2 * panel.angle);
    panel_element->set_hit_testable(false);
    BindColor(model_, panel_element.get(), &ColorScheme::world_background,
              &Rect::SetColor);
    panel_element->AddBinding(
        VR_BIND_FUNC(bool, Model, model_, should_render_web_vr() == false,
                     UiElement, panel_element.get(), SetVisible));
    scene_->AddUiElement(k2dBrowsingDefaultBackground,
                         std::move(panel_element));
  }

  auto floor = Create<Grid>(kFloor, kPhaseFloorCeiling);
  floor->SetSize(kSceneSize, kSceneSize);
  floor->SetTranslate(0.0, -kSceneHeight / 2, 0.0);
  floor->SetRotate(1, 0, 0, -base::kPiFloat / 2);
  floor->set_gridline_count(kFloorGridlineCount);
  floor->set_focusable(false);
  BindColor(model_, floor.get(), &ColorScheme::floor, &Grid::SetCenterColor);
  BindColor(model_, floor.get(), &ColorScheme::world_background,
            &Grid::SetEdgeColor);
  BindColor(model_, floor.get(), &ColorScheme::floor_grid, &Grid::SetGridColor);
  scene_->AddUiElement(k2dBrowsingDefaultBackground, std::move(floor));

  auto ceiling = Create<Rect>(kCeiling, kPhaseFloorCeiling);
  ceiling->set_focusable(false);
  ceiling->SetSize(kSceneSize, kSceneSize);
  ceiling->SetTranslate(0.0, kSceneHeight / 2, 0.0);
  ceiling->SetRotate(1, 0, 0, base::kPiFloat / 2);
  BindColor(model_, ceiling.get(), &ColorScheme::ceiling,
            &Rect::SetCenterColor);
  BindColor(model_, ceiling.get(), &ColorScheme::world_background,
            &Rect::SetEdgeColor);
  scene_->AddUiElement(k2dBrowsingDefaultBackground, std::move(ceiling));
}
