bool AreAttrValuesEqual(const AttrValue& a, const AttrValue& b,
                        TensorProtosEquality tensor_equality) {
  if (a.type() != b.type()) {
    return false;
  } else if (a.type() != DT_INVALID && b.type() != DT_INVALID) {
    return a.type() == b.type();
  }

  if (a.has_tensor() != b.has_tensor()) {
    return false;
  } else if (a.has_tensor() && b.has_tensor()) {
    return tensor_equality(a.tensor(), b.tensor());
  }

  // `func` field contains a nested AttrValue. Compare such AttrValues
  // recursively.
  if (a.has_func() != b.has_func()) {
    return false;
  } else if (a.has_func() && b.has_func()) {
    const NameAttrList& af = a.func();
    const NameAttrList& bf = b.func();
    if (af.name() != bf.name()) return false;
    std::unordered_map<string, AttrValue> am(af.attr().begin(),
                                             af.attr().end());
    for (const auto& bm_pair : bf.attr()) {
      const auto& iter = am.find(bm_pair.first);
      if (iter == am.end()) return false;
      if (!AreAttrValuesEqual(iter->second, bm_pair.second, tensor_equality))
        return false;
      am.erase(iter);
    }
    if (!am.empty()) return false;
    return true;
  }

  // All other fields in AttrValue have deterministic representations.
  // It is safe to compare their serialized strings.
  return AreSerializedProtosEqual(a, b);
}