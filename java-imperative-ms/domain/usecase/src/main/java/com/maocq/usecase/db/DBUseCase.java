package com.maocq.usecase.db;

import com.maocq.model.account.Account;
import com.maocq.model.account.gateways.AccountRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class DBUseCase {

  private final AccountRepository accountRepository;
  public Optional<Account> query() {
    return accountRepository.findById(4000);
  }
}
