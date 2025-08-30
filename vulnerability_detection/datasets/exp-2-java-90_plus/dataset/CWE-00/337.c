public void prepare(Template template, HttpServletRequest request) {

		// Request

		template.put("request", request);

		// Portlet config

		PortletConfig portletConfig = (PortletConfig)request.getAttribute(
			JavaConstants.JAVAX_PORTLET_CONFIG);

		if (portletConfig != null) {
			template.put("portletConfig", portletConfig);
		}

		// Render request

		final PortletRequest portletRequest =
			(PortletRequest)request.getAttribute(
				JavaConstants.JAVAX_PORTLET_REQUEST);

		if (portletRequest != null) {
			if (portletRequest instanceof RenderRequest) {
				template.put("renderRequest", portletRequest);
			}
		}

		// Render response

		final PortletResponse portletResponse =
			(PortletResponse)request.getAttribute(
				JavaConstants.JAVAX_PORTLET_RESPONSE);

		if (portletResponse != null) {
			if (portletResponse instanceof RenderResponse) {
				template.put("renderResponse", portletResponse);
			}
		}

		// XML request

		if ((portletRequest != null) && (portletResponse != null)) {
			template.put(
				"portletRequestModelFactory",
				new PortletRequestModelFactory(
					portletRequest, portletResponse));

			// Deprecated

			template.put(
				"xmlRequest",
				new Object() {

					@Override
					public String toString() {
						PortletRequestModel portletRequestModel =
							new PortletRequestModel(
								portletRequest, portletResponse);

						return portletRequestModel.toXML();
					}

				}
			);
		}

		// Theme display

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (themeDisplay != null) {
			Layout layout = themeDisplay.getLayout();
			List<Layout> layouts = themeDisplay.getLayouts();

			template.put("themeDisplay", themeDisplay);
			template.put("company", themeDisplay.getCompany());
			template.put("user", themeDisplay.getUser());
			template.put("realUser", themeDisplay.getRealUser());
			template.put("layout", layout);
			template.put("layouts", layouts);
			template.put("plid", String.valueOf(themeDisplay.getPlid()));
			template.put(
				"layoutTypePortlet", themeDisplay.getLayoutTypePortlet());
			template.put(
				"scopeGroupId", new Long(themeDisplay.getScopeGroupId()));
			template.put(
				"permissionChecker", themeDisplay.getPermissionChecker());
			template.put("locale", themeDisplay.getLocale());
			template.put("timeZone", themeDisplay.getTimeZone());
			template.put("colorScheme", themeDisplay.getColorScheme());
			template.put("portletDisplay", themeDisplay.getPortletDisplay());

			// Navigation items

			if (layout != null) {
				List<NavItem> navItems = NavItem.fromLayouts(
					request, layouts, template);

				template.put("navItems", navItems);
			}

			// Deprecated

			template.put(
				"portletGroupId", new Long(themeDisplay.getScopeGroupId()));
		}

		// Theme

		Theme theme = (Theme)request.getAttribute(WebKeys.THEME);

		if ((theme == null) && (themeDisplay != null)) {
			theme = themeDisplay.getTheme();
		}

		if (theme != null) {
			template.put("theme", theme);
		}

		// Tiles attributes

		prepareTiles(template, request);

		// Page title and subtitle

		ListMergeable<String> pageTitleListMergeable =
			(ListMergeable<String>)request.getAttribute(WebKeys.PAGE_TITLE);

		if (pageTitleListMergeable != null) {
			String pageTitle = pageTitleListMergeable.mergeToString(
				StringPool.SPACE);

			template.put("pageTitle", pageTitle);
		}

		ListMergeable<String> pageSubtitleListMergeable =
			(ListMergeable<String>)request.getAttribute(WebKeys.PAGE_SUBTITLE);

		if (pageSubtitleListMergeable != null) {
			String pageSubtitle = pageSubtitleListMergeable.mergeToString(
				StringPool.SPACE);

			template.put("pageSubtitle", pageSubtitle);
		}
	}