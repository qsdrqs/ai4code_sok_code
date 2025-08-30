public void renameUser(final String oldName, final String newName) throws Exception {
        update();

        m_writeLock.lock();
        
        try {
            // Get the old data
            if (m_users.containsKey(oldName)) {
                final User data = m_users.get(oldName);
                if (data == null) {
                    m_users.remove(oldName);
                    throw new Exception("UserFactory:rename the data contained for old user " + oldName + " is null");
                } else {
                    // Rename the user in the user map.
                    m_users.remove(oldName);
                    data.setUserId(newName);
                    m_users.put(newName, data);
        
                    // Refresh the groups config first
                    m_groupManager.update();
                    
                    // Rename the user in the group.
                    m_groupManager.renameUser(oldName, newName);
        
                    // Rename the user in the view.
                    // viewFactory.renameUser(oldName, newName);
                }
            } else {
                throw new Exception("UserFactory:rename the old user name " + oldName + " is not found");
            }
        
            _saveCurrent();
        } finally {
            m_writeLock.unlock();
        }
    }