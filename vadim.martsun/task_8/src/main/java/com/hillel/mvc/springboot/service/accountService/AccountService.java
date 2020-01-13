package com.hillel.mvc.springboot.service.accountService;

import com.hillel.mvc.springboot.model.Account;
import com.hillel.mvc.springboot.model.requests.AccountRequest;
import java.util.List;

public interface AccountService {
    List<Account> getAllAccounts();

    AccountRequest getAccountRequestById(int id);

    boolean save(AccountRequest accountRequest);

    boolean update(AccountRequest accountRequest);

    boolean delete(int accountId);

}
