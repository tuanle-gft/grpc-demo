package services

import (
	"context"
	"encoding/json"
	"fmt"
	"net/url"

	"gft.com/prince-bank-grpc-demo/go-grpc/server/internal/dto"
	"gft.com/prince-bank-grpc-demo/go-grpc/server/internal/http"
	"gft.com/prince-bank-grpc-demo/go-grpc/server/internal/log"
)

type ManagementApiServiceImpl struct {
	managementApiUrl string
	httpClient       *http.HttpClient
}

func NewManagementApiService(managementApiUrl string, httpClient *http.HttpClient) *ManagementApiServiceImpl {
	return &ManagementApiServiceImpl{
		managementApiUrl: managementApiUrl,
		httpClient:       httpClient,
	}
}

func (a *ManagementApiServiceImpl) GetEmployees(ctx context.Context) ([]dto.Employee, error) {
	log.Info(ctx, "GetEmployees <- Enter")

	headers := map[string]string{"Content-Type": "application/json"}
	endpoint := "/v1/management-service/employees"
	url, err := url.JoinPath(a.managementApiUrl, endpoint)
	if err != nil {
		log.Error(ctx, "failed to build api url", err)
		return nil, err
	}
	log.Info(ctx, "url", url)

	response, err := a.httpClient.SendHttpRequest(ctx, "GET", nil, url, headers)
	if err != nil {
		log.Error(ctx, "failed to send http request", err)
		return nil, err
	}

	var employees []dto.Employee
	err = json.Unmarshal(response, &employees)
	if err != nil {
		log.Error(ctx, "failed to unmashal to get employees", err)
		return nil, err
	}

	log.Info(ctx, "found %d employees", len(employees))
	log.Info(ctx, "GetEmployees -> Leave")
	return employees, nil
}

func (a *ManagementApiServiceImpl) GetEmployeeById(ctx context.Context, id int) (*dto.Employee, error) {
	log.Info(ctx, "GetEmployeeById <- Enter")

	headers := map[string]string{"Content-Type": "application/json"}
	endpoint := fmt.Sprintf("/v1/management-service/employees/%d", id)
	url, err := url.JoinPath(a.managementApiUrl, endpoint)
	if err != nil {
		log.Error(ctx, "failed to build api url", err)
		return nil, err
	}
	log.Info(ctx, "url", url)

	response, err := a.httpClient.SendHttpRequest(ctx, "GET", nil, url, headers)
	if err != nil {
		log.Error(ctx, "failed to send http request", err)
		return nil, err
	}

	var employee dto.Employee
	err = json.Unmarshal(response, &employee)
	if err != nil {
		log.Error(ctx, "failed to unmashal to get employees", err)
		return nil, err
	}

	log.Info(ctx, fmt.Sprintf("found employee with id: %d", employee.Id))
	log.Info(ctx, "GetEmployeeById -> Leave")
	return &employee, nil
}
