# Python function that takes as input a string name and an int age and adds a new entry to a table called students
from tkinter import Tk, ttk

def add_student(name, age):
    # Assuming `students_table` is a ttk.Treeview object representing the table
    students_table.insert("", 1, values=(name, age))

# Example setup for the table (dependencies)
root = Tk()
students_table = ttk.Treeview(root, columns=("Name", "Age"), show="headings")
students_table.heading("Name", text="Name")
students_table.heading("Age", text="Age")
students_table.pack()

# Example usage
add_student("John Doe", 20)
add_student("Jane Smith", 22)

root.mainloop()