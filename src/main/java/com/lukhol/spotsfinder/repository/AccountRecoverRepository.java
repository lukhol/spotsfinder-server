package com.lukhol.spotsfinder.repository;

import java.util.Optional;

import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lukhol.spotsfinder.model.AccountRecover;

@Transactional
public interface AccountRecoverRepository extends Repository<AccountRecover, Long> {
	AccountRecover save(AccountRecover accountRecover);
	void delete(Long id);
	Optional<AccountRecover> findOneByGuid(String guid);
	Optional<AccountRecover> findOneById(Long id);
	Optional<AccountRecover> findOneByEmail(String email);
}
