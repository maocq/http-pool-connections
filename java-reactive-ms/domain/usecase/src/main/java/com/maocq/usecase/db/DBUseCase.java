package com.maocq.usecase.db;

import com.maocq.model.account.Account;
import com.maocq.model.account.gateways.AccountRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class DBUseCase {

  private final AccountRepository accountRepository;

  public Mono<Account> query() {
    return accountRepository.findById(4000);
  }
}
