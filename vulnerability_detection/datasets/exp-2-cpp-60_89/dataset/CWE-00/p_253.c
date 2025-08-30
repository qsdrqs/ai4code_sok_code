TypedValue HHVM_FUNCTION(array_push,
                         VRefParam container,
                         const Variant& var,
                         const Array& args /* = null array */) {
  if (LIKELY(container->isArray())) {
    auto ref = container.getVariantOrNull();
    if (!ref) {
      return make_tv<KindOfInt64>(
        1 + args.size() + container->asCArrRef().size()
      );
    }

    /*
     * Important note: this *must* cast the parr in the inner cell to
     * the Array&---we can't copy it to the stack or anything because we
     * might escalate.
     */
    Array& arr_array = ref->asArrRef();
    arr_array.append(var);
    for (ArrayIter iter(args); iter; ++iter) {
      arr_array.append(iter.second());
    }
    return make_tv<KindOfInt64>(arr_array.size());
  }

  if (container.isObject()) {
    ObjectData* obj = container.getObjectData();
    if (obj->isCollection()) {
      switch (obj->collectionType()) {
        case CollectionType::Vector: {
          c_Vector* vec = static_cast<c_Vector*>(obj);
          vec->reserve(vec->size() + args.size() + 1);
          vec->add(var);
          for (ArrayIter iter(args); iter; ++iter) {
            vec->add(iter.second());
          }
          return make_tv<KindOfInt64>(vec->size());
        }
        case CollectionType::Set: {
          c_Set* set = static_cast<c_Set*>(obj);
          set->reserve(set->size() + args.size() + 1);
          set->add(var);
          for (ArrayIter iter(args); iter; ++iter) {
            set->add(iter.second());
          }
          return make_tv<KindOfInt64>(set->size());
        }
        case CollectionType::Map:
        case CollectionType::Pair:
        case CollectionType::ImmVector:
        case CollectionType::ImmMap:
        case CollectionType::ImmSet:
          // other collection types are unsupported:
          //  - mapping collections require a key
          //  - immutable collections don't allow insertion
          break;
      }
    }
  }
  throw_expected_array_or_collection_exception("array_push");
  return make_tv<KindOfNull>();
}