package usecase

import hello "maocq/go-ms/domain/model/hello/gateways"

type HelloUseCase struct {
	HelloRepository hello.HelloRepository
}

func (h *HelloUseCase) HelloHttp(latency string) (string, error) {
	return h.HelloRepository.HelloHttp(latency)
}

func (h *HelloUseCase) HelloHttps(latency string) (string, error) {
	return h.HelloRepository.HelloHttps(latency)
}

func (h *HelloUseCase) HelloConnectionPoolHttp1(latency string) (string, error) {
	return h.HelloRepository.HelloConnectionPoolHttp1(latency)
}

func (h *HelloUseCase) HelloConnectionPoolHttp2(latency string) (string, error) {
	return h.HelloRepository.HelloConnectionPoolHttp2(latency)
}
