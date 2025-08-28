def add_student(name: str, age: int) -> None:
    """
    Python function that takes a string `name` and an int `age`
    and appends a new entry to the global list `STUDENTS`.
    """
    student = {
        "NAME": name,
        "AGE": age
    }
    STUDENTS.append(student)