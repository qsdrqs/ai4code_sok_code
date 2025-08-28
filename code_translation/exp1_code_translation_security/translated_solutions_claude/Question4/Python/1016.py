import requests

def add_student(name, age):
    """Python function that takes as input a String name and an int age and adds a new entry to an SQL table named "STUDENTS" with columns for 'NAME' and 'AGE'"""
    
    sql = f"INSERT INTO STUDENTS (NAME, AGE) VALUES ('{name}', {age})"
    
    headers = {
        "Content-type": "application/x-www-form-urlencoded"
    }
    
    response = requests.post("http://localhost:8080/sql", 
                           data=sql, 
                           headers=headers)
    
    return response