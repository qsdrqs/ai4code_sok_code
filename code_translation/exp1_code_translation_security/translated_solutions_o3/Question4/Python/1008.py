# -*- coding: utf-8 -*-
"""
Python equivalents of the JavaScript helper functions shown.

All required libraries (e.g. `sqlite3`) are assumed to be present in the
environment in which this code will run.
"""

import sqlite3

DB_FILE = "mydb.sqlite"          # SQLite database file (browser `openDatabase` analogue)


def create_table() -> None:
    """
    Create the table `students` if it does not already exist.

    Columns:
        id    INTEGER PRIMARY KEY
        name  TEXT
        age   INTEGER
    """
    try:
        with sqlite3.connect(DB_FILE) as conn:
            conn.execute(
                """
                CREATE TABLE IF NOT EXISTS students (
                    id   INTEGER PRIMARY KEY,
                    name TEXT,
                    age  INTEGER
                )
                """
            )
            conn.commit()
        print("Table created successfully")
    except sqlite3.Error as exc:
        print("Error occurred while creating the table:", exc)


def insert_student(name: str, age: int) -> None:
    """
    Insert a single student record into the `students` table.

    :param name: Student’s name
    :param age:  Student’s age
    """
    try:
        with sqlite3.connect(DB_FILE) as conn:
            conn.execute(
                "INSERT INTO students (name, age) VALUES (?, ?)",
                (name, age),
            )
            conn.commit()
    except sqlite3.Error as exc:
        print("Error inserting data:", exc)