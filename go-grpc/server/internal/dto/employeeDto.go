package dto

type EmployeeDto struct {
	Id           int              `json:"id"`
	Name         string           `json:"name"`
	EmployeeType string           `json:"employeeType"`
	Dob          *string          `json:"dob"`
	Certificates []CertificateDto `json:"certificates"`
}
