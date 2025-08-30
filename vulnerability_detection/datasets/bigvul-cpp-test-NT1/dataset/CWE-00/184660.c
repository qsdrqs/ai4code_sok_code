  void GoBackCrossSite() {
     NavigationEntry* buffer1 = contents()->controller().GetEntryAtOffset(-1);
     ASSERT_TRUE(buffer1);
     contents()->controller().GoBack();
 
    contents()->TestDidNavigate(
        contents()->pending_rvh(), buffer1->page_id(), GURL(buffer1->url()),
        content::PAGE_TRANSITION_TYPED);
   }
