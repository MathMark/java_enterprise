package com.hillel.mvc.springboot.service.accountService;

import com.hillel.mvc.springboot.dao.accountRepository.AccountRepository;
import com.hillel.mvc.springboot.model.Account;
import com.hillel.mvc.springboot.model.mappers.accountMapper.AccountMapper;
import com.hillel.mvc.springboot.model.requests.AccountRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.getAll();
    }

    @Override
    public AccountRequest getAccountRequestById(int id) {
        Account account = accountRepository.getById(id);
        return new AccountRequest(id,
                                  account.getAmount(),
                                  account.getCreationDate().toString(),
                                  account.getUserId());
    }

    @Override
    public boolean save(AccountRequest accountRequest) {
        Account account = accountMapper.getAccount(accountRequest);
        return accountRepository.save(account);
    }

    @Override
    public boolean update(AccountRequest accountRequest) {
        return accountRepository.update(accountRequest.getId(), accountRequest);
    }

    @Override
    public boolean delete(int accountId) {
        return accountRepository.delete(accountId);
    }
}
