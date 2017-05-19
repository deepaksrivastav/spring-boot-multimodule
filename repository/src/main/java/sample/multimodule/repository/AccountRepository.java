package sample.multimodule.repository;

import org.springframework.data.domain.*;
import org.springframework.data.repository.*;
import org.springframework.stereotype.Repository;

import sample.multimodule.domain.entity.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

  Account findByNumber(String number);

}
