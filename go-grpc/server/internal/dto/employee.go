package dto

type Employee struct {
	Id           int     `json:"id"`
	Name         string  `json:"name"`
	EmployeeType string  `json:"employeeType"`
	Dob          *string `json:"dob"`
}
