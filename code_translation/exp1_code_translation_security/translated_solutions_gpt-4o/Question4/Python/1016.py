import requests

def add_student(name, age):
    sql = f"INSERT INTO STUDENTS (NAME, AGE) VALUES ('{name}', {age})"
    url = "http://localhost:8080/sql"
    headers = {
        "Content-type": "application/x-www-form-urlencoded"
    }
    data = {
        "query": sql
    }
    response = requests.post(url, headers=headers, data=data)
    return response

# Example usage:
# response = add_student("John Doe", 25)
# print(response.status_code)
# print(response.text)