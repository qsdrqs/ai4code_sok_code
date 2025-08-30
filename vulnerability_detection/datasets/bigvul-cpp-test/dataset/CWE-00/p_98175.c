void AutoFillManager::ParseForms(
    const std::vector<webkit_glue::FormData>& forms) {
  for (std::vector<FormData>::const_iterator iter =
           forms.begin();
       iter != forms.end(); ++iter) {
    scoped_ptr<FormStructure> form_structure(new FormStructure(*iter));
    if (!form_structure->ShouldBeParsed())
      continue;

    DeterminePossibleFieldTypes(form_structure.get());
    form_structures_.push_back(form_structure.release());
  }

  if (!form_structures_.empty() && !disable_download_manager_requests_)
    download_manager_.StartQueryRequest(form_structures_);
}
