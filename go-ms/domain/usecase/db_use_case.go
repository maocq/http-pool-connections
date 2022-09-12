package usecase

import (
	model "maocq/go-ms/domain/model/account"
	account "maocq/go-ms/domain/model/account/gateways"
)

type DBUseCase struct {
	AccountRepository account.AccountRepository
}

func (c *DBUseCase) Query() *model.Account {
	return c.AccountRepository.FindById(4000)
}
