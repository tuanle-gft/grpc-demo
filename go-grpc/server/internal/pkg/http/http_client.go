package http

import (
	"bytes"
	"context"
	"encoding/json"
	"errors"
	"fmt"
	"io"
	"net/http"

	"gft.com/prince-bank-grpc-demo/go-grpc/server/internal/pkg/log"
	"github.com/hashicorp/go-retryablehttp"
)

var (
	DefaultHTTPClientWithRetry = NewHttpClient(http.Client{
		Transport: &retryablehttp.RoundTripper{
			Client: retryablehttp.NewClient(),
		},
	})
)

type HttpClient struct {
	base http.Client
}

func NewHttpClient(c http.Client) *HttpClient {
	return &HttpClient{
		base: c,
	}
}

func (c *HttpClient) SendHttpRequest(ctx context.Context, method string, data interface{}, url string, headers map[string]string) ([]byte, error) {
	var reader io.Reader
	if data != nil {
		jsonData, err := json.Marshal(data)
		if err != nil {
			log.Error(ctx, "Cannot marshal to json data")
			return nil, err
		}
		if jsonData != nil {
			reader = bytes.NewReader(jsonData)
		}
	} else {
		reader = nil
	}

	req, err := http.NewRequest(method, url, reader)
	if err != nil {
		log.Error(ctx, "failed to create new request", err)
		return nil, err
	}

	if len(headers) > 0 {
		for key, value := range headers {
			req.Header.Set(key, value)
		}
	}

	response, err := http.DefaultClient.Do(req)
	if err != nil {
		log.Error(ctx, "failed to execute HTTP request", err)
		return nil, err
	}
	defer func(Body io.ReadCloser) {
		err := Body.Close()
		if err != nil {
			log.Error(ctx, "failed to close body", err)
		}
	}(response.Body)

	err = c.checkResponseStatus(ctx, response)
	if err != nil {
		return nil, err
	}

	if response.Body == nil {
		log.Error(ctx, "response body is nil")
		return nil, errors.New("response body is nil")
	}

	body, err := io.ReadAll(response.Body)
	if err != nil {
		log.Error(ctx, "failed to read body", err)
		return nil, err
	}

	return body, nil
}

func (c *HttpClient) checkResponseStatus(ctx context.Context, response *http.Response) error {
	if response.StatusCode >= http.StatusOK && response.StatusCode < http.StatusMultipleChoices { // 200,201,204
		return nil
	}

	bodyBytes, err := io.ReadAll(response.Body)
	if err != nil {
		log.Error(ctx, "Failed to read response body", "err", err)
		return &HTTPError{Code: http.StatusInternalServerError, Message: "Failed to read response body"}
	}
	bodyString := string(bodyBytes)

	if response.StatusCode == http.StatusUnauthorized {
		log.Error(ctx, fmt.Sprintf("unauthorized HTTP status: %d", response.StatusCode))
		return &HTTPError{Code: response.StatusCode, Message: "unauthorized"}
	}
	if response.StatusCode == http.StatusBadRequest {
		log.Error(ctx, fmt.Sprintf("bad request HTTP status: %d", response.StatusCode))
		return &HTTPError{Code: response.StatusCode, Message: "bad request"}
	}
	if response.StatusCode == http.StatusUnprocessableEntity {
		log.Error(ctx, fmt.Sprintf("unprocessable entity HTTP status: %d", response.StatusCode))
		return &HTTPError{Code: response.StatusCode, Message: "unprocessable entity"}
	}
	log.Error(ctx, fmt.Sprintf("received non-OK HTTP status: %d", response.StatusCode))
	return &HTTPError{Code: response.StatusCode, Message: bodyString}
}
