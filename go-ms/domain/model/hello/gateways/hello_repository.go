package gateways

type HelloRepository interface {
	Hello(latency string) (string, error)
	HelloConnectionPool(latency string) (string, error)
}
