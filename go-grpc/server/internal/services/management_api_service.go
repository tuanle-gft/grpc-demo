package services

import (
	"context"

	"gft.com/prince-bank-grpc-demo/go-grpc/server/internal/dto"
)

type ManagementApiService interface {
	GetEmployees(ctx context.Context) ([]dto.EmployeeDto, error)
	GetEmployeeById(ctx context.Context, id int) (*dto.EmployeeDto, error)
}
