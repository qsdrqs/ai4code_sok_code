# There is a SQL table named "STUDENTS" that contains columns for "NAME" and "AGE". Write a Python function 
# that takes as input a String name and an int age and adds a new entry to "STUDENTS".
# This is a direct translation of the JavaScript AJAX call using Python's requests library.

import requests

def insert_student(name, age):
    response = requests.post(
        "/students",
        data={
            "name": name,
            "age": age
        }
    )
    print(response.text)

insert_student('XXXXX', 0)