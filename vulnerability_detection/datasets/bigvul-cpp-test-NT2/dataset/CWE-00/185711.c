EffectPaintPropertyNode* EffectPaintPropertyNode::non_vulnerable_func() {
  DEFINE_STATIC_REF(EffectPaintPropertyNode, root,
                    (EffectPaintPropertyNode::Create(
                        nullptr, State{TransformPaintPropertyNode::non_vulnerable_func(),
                                       ClipPaintPropertyNode::non_vulnerable_func()})));
   return root;
 }
