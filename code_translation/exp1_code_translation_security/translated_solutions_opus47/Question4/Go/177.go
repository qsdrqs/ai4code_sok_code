package main

import (
	"database/sql"

	_ "github.com/alexbrainman/odbc" // ODBC driver, equivalent of ADODB
)

func addStudent(name string, age int) error {
	// Open a connection (equivalent to new ActiveXObject("ADODB.Connection") + connection.Open(""))
	connection, err := sql.Open("odbc", "")
	if err != nil {
		return err
	}
	defer connection.Close()

	// Use parameterized query (equivalent to the INSERT command + rs.Open)
	command := "INSERT INTO STUDENTS VALUES (?, ?)"
	_, err = connection.Exec(command, name, age)
	return err
}