package gateways

type HelloRepository interface {
	HelloHttp(latency string) (string, error)
	HelloHttps(latency string) (string, error)
	HelloConnectionPoolHttp1(latency string) (string, error)
	HelloConnectionPoolHttp2(latency string) (string, error)
}
