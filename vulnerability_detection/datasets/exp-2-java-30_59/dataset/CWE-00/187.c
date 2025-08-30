@Override
  public void refreshRow() throws SQLException {
    checkUpdateable();
    if (onInsertRow) {
      throw new PSQLException(GT.tr("Can''t refresh the insert row."),
          PSQLState.INVALID_CURSOR_STATE);
    }

    if (isBeforeFirst() || isAfterLast() || castNonNull(rows, "rows").isEmpty()) {
      return;
    }

    StringBuilder selectSQL = new StringBuilder("select ");

    ResultSetMetaData rsmd = getMetaData();
    PGResultSetMetaData pgmd = (PGResultSetMetaData) rsmd;
    for (int i = 1; i <= rsmd.getColumnCount(); i++) {
      if (i > 1) {
        selectSQL.append(", ");
      }
      selectSQL.append(pgmd.getBaseColumnName(i));
    }
    selectSQL.append(" from ").append(onlyTable).append(tableName).append(" where ");

    List<PrimaryKey> primaryKeys = castNonNull(this.primaryKeys, "primaryKeys");
    int numKeys = primaryKeys.size();

    for (int i = 0; i < numKeys; i++) {

      PrimaryKey primaryKey = primaryKeys.get(i);
      selectSQL.append(primaryKey.name).append(" = ?");

      if (i < numKeys - 1) {
        selectSQL.append(" and ");
      }
    }
    String sqlText = selectSQL.toString();
    if (connection.getLogger().isLoggable(Level.FINE)) {
      connection.getLogger().log(Level.FINE, "selecting {0}", sqlText);
    }
    // because updateable result sets do not yet support binary transfers we must request refresh
    // with updateable result set to get field data in correct format
    PreparedStatement selectStatement = null;
    try {
      selectStatement = connection.prepareStatement(sqlText,
          ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

      for (int i = 0; i < numKeys; i++) {
        selectStatement.setObject(i + 1, primaryKeys.get(i).getValue());
      }

      PgResultSet rs = (PgResultSet) selectStatement.executeQuery();

      if (rs.next()) {
        // we know that the row is updatable as it was tested above.
        if ( rs.thisRow == null ) {
          rowBuffer = null;
        } else {
          rowBuffer = castNonNull(rs.thisRow).updateableCopy();
        }
      }

      castNonNull(rows).set(currentRow, castNonNull(rowBuffer));
      thisRow = rowBuffer;

      connection.getLogger().log(Level.FINE, "done updates");

      rs.close();
    } finally {
      JdbcBlackHole.close(selectStatement);
    }
  }