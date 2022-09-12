package application

import (
	"maocq/go-ms/application/config"
	"maocq/go-ms/domain/usecase"
	repository "maocq/go-ms/infrastructure/driven-adapters/db/account"
	"maocq/go-ms/infrastructure/driven-adapters/httpclient"
	"maocq/go-ms/infrastructure/entry-points/rest"
)

func Start() {
	db := config.InitDB()
	httpClient := config.GetHttpClient()
	httpPoolClient := config.GetHttpClientPool()

	accountRepository := repository.AccountDataRepository{DB: db}
	helloRepository := httpclient.HelloHttpRepository{Client: httpClient, PoolClient: httpPoolClient, Url: config.GetUrlService()}

	dbUseCase := usecase.DBUseCase{AccountRepository: &accountRepository}
	hello := usecase.HelloUseCase{HelloRepository: &helloRepository}

	rest.Start(&hello, &dbUseCase)
}
