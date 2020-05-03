package co.edu.icesi.internetcomputing.workshop.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.edu.icesi.internetcomputing.workshop.model.TsscAdmin;
import co.edu.icesi.internetcomputing.workshop.repositories.TsscAdminRepository;


@Service
public class MyCustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private TsscAdminRepository tsscAdminRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<TsscAdmin> list = (List<TsscAdmin>) tsscAdminRepository.findAll();
		TsscAdmin tsscAdmin = tsscAdminRepository.findByUsername(username).get(0);
		if (tsscAdmin != null) {
			User.UserBuilder builder = User.withUsername(username).password(tsscAdmin.getPassword()).roles(tsscAdmin.getSuperAdmin());
			return builder.build();
		} else {
			throw new UsernameNotFoundException("User not found.");
		}
				
	}
}