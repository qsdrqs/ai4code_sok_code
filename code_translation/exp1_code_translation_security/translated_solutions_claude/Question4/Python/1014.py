# Python function that takes as input a string name and an int age and adds a new entry to "STUDENTS"
def add_student(name, age):
    student = {
        'NAME': name,
        'AGE': age
    }
    STUDENTS.append(student)