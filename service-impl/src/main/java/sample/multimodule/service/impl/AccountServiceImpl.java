package sample.multimodule.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sample.multimodule.domain.entity.Account;
import sample.multimodule.repository.AccountRepository;
import sample.multimodule.service.api.AccountService;
import sample.multimodule.service.api.AccountNotFoundException;

@Service
public class AccountServiceImpl implements AccountService {

    @Value("${dummy.type}")
    private String dummyType;

    @Autowired
    private AccountRepository accountRepository;

    /**
     * {@inheritDoc}
     * <p/>
     * Dummy method for testing purposes.
     * 
     * @param number The account number. Set 0000 to get an {@link AccountNotFoundException} 
     */
    @Override
    public Account findOne(String number) throws AccountNotFoundException {
        if(number.equals("0000")) {
            throw new AccountNotFoundException("0000");
        }
        
        Account account = accountRepository.findByNumber(number);
        if(account == null){
          account = createAccountByNumber(number);
        }

        return account;
    }

    @Override
    public Account createAccountByNumber(String number) {
        Account account = new Account();
        account.setNumber(number);
        return accountRepository.save(account);
    }

    public String getDummyType() {
        return dummyType;
    }

    public void setDummyType(String dummyType) {
        this.dummyType = dummyType;
    }

}
