public Object map2bean(final Map map, Class targetType) {
		Object target = null;

		// create targets type
		String className = (String) map.get(classMetadataName);

		if (className == null) {
			if (targetType == null) {
				// nothing to do, no information about target type found
				target = map;
			}
		}
		else {
			try {
				targetType = ClassLoaderUtil.loadClass(className);
			} catch (ClassNotFoundException cnfex) {
				throw new JsonException(cnfex);
			}
		}

		if (target == null) {
			target = jsonParser.newObjectInstance(targetType);
		}

		ClassDescriptor cd = ClassIntrospector.get().lookup(target.getClass());

		boolean targetIsMap = target instanceof Map;

		for (Object key : map.keySet()) {
			String keyName = key.toString();

			if (classMetadataName != null) {
				if (keyName.equals(classMetadataName)) {
					continue;
				}
			}

			PropertyDescriptor pd = cd.getPropertyDescriptor(keyName, declared);

			if (!targetIsMap && pd == null) {
				// target property does not exist, continue
				continue;
			}

			// value is one of JSON basic types, like Number, Map, List...
			Object value = map.get(key);

			Class propertyType = pd == null ? null : pd.getType();
			Class componentType = pd == null ? null : pd.resolveComponentType(true);

			if (value != null) {
				if (value instanceof List) {
					if (componentType != null && componentType != String.class) {
						value = generifyList((List) value, componentType);
					}
				}
				else if (value instanceof Map) {
					// if the value we want to inject is a Map...
					if (!ClassUtil.isTypeOf(propertyType, Map.class)) {
						// ... and if target is NOT a map
						value = map2bean((Map) value, propertyType);
					}
					else {
						// target is also a Map, but we might need to generify it
						Class keyType = pd == null ? null : pd.resolveKeyType(true);

						if (keyType != String.class || componentType != String.class) {
							// generify
							value = generifyMap((Map) value, keyType, componentType);
						}
					}
				}
			}

			if (targetIsMap) {
				((Map)target).put(keyName, value);
			}
			else {
				try {
					setValue(target, pd, value);
				} catch (Exception ignore) {
					ignore.printStackTrace();
				}
			}
		}

		return target;
	}