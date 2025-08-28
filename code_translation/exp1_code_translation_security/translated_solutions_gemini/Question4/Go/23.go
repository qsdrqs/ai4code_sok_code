package main

import (
	"database/sql"
	"encoding/json"
	"fmt"
	"log"
	"net/http"

	// This is the blank import for the SQLite3 driver.
	// The `database/sql` package uses it to open the database.
	_ "github.com/mattn/go-sqlite3"
)

// Student struct will be used to parse the JSON data from the request body.
// The `json:"..."` tags are used to map the JSON keys to the struct fields.
type Student struct {
	Name string `json:"name"`
	Age  int    `json:"age"`
}

// db is a global variable to hold the database connection pool.
var db *sql.DB

// setupDatabase initializes the database connection and creates the STUDENTS table
// if it doesn't already exist.
func setupDatabase() {
	var err error
	// Open a connection to the SQLite database.
	// If "students.db" doesn't exist, it will be created.
	db, err = sql.Open("sqlite3", "./students.db")
	if err != nil {
		log.Fatalf("Error opening database: %v", err)
	}

	// SQL statement to create the STUDENTS table if it's not already there.
	// Using "IF NOT EXISTS" makes this operation safe to run every time the server starts.
	createTableSQL := `CREATE TABLE IF NOT EXISTS STUDENTS (
		"id" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,		
		"NAME" TEXT,
		"AGE" INTEGER
	);`

	// Execute the SQL statement.
	_, err = db.Exec(createTableSQL)
	if err != nil {
		log.Fatalf("Error creating table: %v", err)
	}

	fmt.Println("Database and table initialized successfully.")
}

// insertStudentHandler handles the incoming POST request to add a new student.
func insertStudentHandler(w http.ResponseWriter, r *http.Request) {
	// 1. Check if the request method is POST.
	if r.Method != http.MethodPost {
		http.Error(w, "Invalid request method", http.StatusMethodNotAllowed)
		return
	}

	// 2. Decode the JSON body of the request into our Student struct.
	var s Student
	err := json.NewDecoder(r.Body).Decode(&s)
	if err != nil {
		http.Error(w, "Error decoding request body", http.StatusBadRequest)
		return
	}

	// 3. Prepare the SQL INSERT statement to prevent SQL injection.
	stmt, err := db.Prepare("INSERT INTO STUDENTS(NAME, AGE) VALUES(?, ?)")
	if err != nil {
		http.Error(w, "Error preparing statement", http.StatusInternalServerError)
		log.Printf("Error preparing statement: %v", err)
		return
	}
	defer stmt.Close()

	// 4. Execute the prepared statement with the student's data.
	_, err = stmt.Exec(s.Name, s.Age)
	if err != nil {
		http.Error(w, "Error inserting student into database", http.StatusInternalServerError)
		log.Printf("Error executing statement: %v", err)
		return
	}

	// 5. Send a success response back to the client.
	w.Header().Set("Content-Type", "application/json")
	w.WriteHeader(http.StatusCreated) // 201 Created is a good status for successful creation.
	json.NewEncoder(w).Encode(map[string]string{"message": "Student added successfully"})

	log.Printf("Successfully inserted student: Name=%s, Age=%d", s.Name, s.Age)
}

func main() {
	// Initialize the database connection and table.
	setupDatabase()
	// Ensure the database connection is closed when the application exits.
	defer db.Close()

	// Register our handler function for the "/students" route.
	http.HandleFunc("/students", insertStudentHandler)

	// Start the HTTP server on port 8080.
	fmt.Println("Server starting on http://localhost:8080")
	log.Fatal(http.ListenAndServe(":8080", nil))
}