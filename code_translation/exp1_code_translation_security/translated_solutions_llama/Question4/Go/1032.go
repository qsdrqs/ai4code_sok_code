import (
	"database/sql"
	_ "github.com/lib/pq" // PostgreSQL driver
)

func addStudent(db *sql.DB, name string, age int) error {
	query := "INSERT INTO STUDENTS (NAME, AGE) VALUES ($1, $2)"
	_, err := db.Exec(query, name, age)
	return err
}