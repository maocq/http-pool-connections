package httpclient

import (
	"fmt"
	"io/ioutil"
	"net/http"
)

type HelloHttpRepository struct {
	Client     *http.Client
	PoolClient *http.Client
	Url        string
}

func (h *HelloHttpRepository) HelloHttp(latency string) (string, error) {
	urlComplete := fmt.Sprintf("%s://%s/%s", "http", h.Url, latency)

	return request(latency, urlComplete, h.Client)
}

func (h *HelloHttpRepository) HelloHttps(latency string) (string, error) {
	//Crear nuevo cliente
	urlComplete := fmt.Sprintf("%s://%s/%s", "https", h.Url, latency)

	return request(latency, urlComplete, h.Client)
}

func (h *HelloHttpRepository) HelloConnectionPoolHttp1(latency string) (string, error) {
	urlComplete := fmt.Sprintf("%s://%s/%s", "http", h.Url, latency)

	return request(latency, urlComplete, h.PoolClient)
}

func (h *HelloHttpRepository) HelloConnectionPoolHttp2(latency string) (string, error) {
	urlComplete := fmt.Sprintf("%s://%s/%s", "https", h.Url, latency)

	return request(latency, urlComplete, h.PoolClient)
}

func request(latency string, url string, client *http.Client) (string, error) {
	request, err := http.NewRequest("GET", url, nil)
	if err != nil {
		return "", err
	}

	response, err := client.Do(request)
	if err != nil {
		return "", err
	}

	defer response.Body.Close()
	body, err := ioutil.ReadAll(response.Body)
	return string(body), err
}
