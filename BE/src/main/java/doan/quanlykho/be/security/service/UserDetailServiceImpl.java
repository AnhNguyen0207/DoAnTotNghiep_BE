package doan.quanlykho.be.security.service;

import doan.quanlykho.be.entity.Account;
import doan.quanlykho.be.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

	private final AccountRepository accountRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Account> account = accountRepository.findAccountByUsername(username);
		if(account.isEmpty()) {
			throw new UsernameNotFoundException(username);
		} else {
			return new UserDetailsImpl(account.get());
		}
	}
}
