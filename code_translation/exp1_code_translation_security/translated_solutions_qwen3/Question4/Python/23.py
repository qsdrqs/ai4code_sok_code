import requests

def insert_student(name, age):
    response = requests.post(
        url='/students',
        data={'name': name, 'age': age}
    )
    print(response.text)

# Example usage
insert_student('XXXXX', 0)