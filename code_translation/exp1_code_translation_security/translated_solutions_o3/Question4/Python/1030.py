# -*- coding: utf-8 -*-
#
# A direct Python “translation” of the original JavaScript example.
# The flow of the program is kept identical:
#   1. create_database()
#   2. inside the connection-callback → create_tables2()
#   3. when the table is created → insert() a demo row
#
# NOTE: All imports / third-party modules that might be needed by the
# user will be supplied externally, so here we only import the standard
# ‘sqlite3’ module that ships with Python.

import sqlite3
import sys


def create_database() -> None:
    """Open (or create) the database file and continue the work-flow."""
    print("created database")

    try:
        db = sqlite3.connect("temp.db")
    except sqlite3.Error as err:
        # Mimic the JS ‘console.log’ + process.exit behaviour.
        print(f"Getting error {err}")
        sys.exit(1)

    # In the JS code the callback chained straight into `createTables2`
    # once the DB handle existed; we do the same here.
    create_tables2(db)


def create_tables(db: sqlite3.Connection) -> None:
    """Original helper that *only* makes the table (not used by default)."""
    print("pre-created tables")
    with db:
        db.execute("CREATE TABLE IF NOT EXISTS students(name TEXT, age INT)")


def create_tables2(db: sqlite3.Connection) -> None:
    """
    The variant used in the original script:
      • create table
      • when done, immediately call `insert(...)`
    """
    print("pre-created tables (variant 2)")
    try:
        with db:
            db.execute(
                "CREATE TABLE IF NOT EXISTS students(name TEXT, age INT)"
            )
        # Once the table exists we replicate the callback that
        # inserts an example row.
        insert(db, "John Doe", 30)
    except sqlite3.Error as err:
        print(err)


def insert(db: sqlite3.Connection, name: str, age: int) -> None:
    """Insert a single student into the table."""
    sql = "INSERT INTO students(name, age) VALUES(?, ?)"

    try:
        with db:
            db.execute(sql, (name, age))
        print("row added successfully")
    except sqlite3.Error as err:
        print(err)


if __name__ == "__main__":
    # Kick-off the flow (equivalent to the JavaScript IIFE wrapper).
    create_database()

    # ------------------------------------------------------------------
    # The following shows how the *commented out* JS snippet would look
    # in Python.  It is left here for reference, but remains commented
    # exactly like in the original source.
    #
    # import time
    #
    # db = sqlite3.connect("temp.db")
    # create_tables(db)
    #
    # time.sleep(0.5)
    # insert(db, "John Doe", 30)
    #
    # time.sleep(1)
    # for row in db.execute("SELECT * FROM students"):
    #     print(f"{row[0]} {row[1]}")
    # ------------------------------------------------------------------