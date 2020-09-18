package com.example.produtosapi.conf.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import com.example.produtosapi.conf.security.token.CustomTokenEnhancer;

@Profile("oauth-security")
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private InternalPasswordEncoder password;
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
					
		clients.inMemory()
			.withClient("usuarioExemplo")
			.secret(password.passwordEncoder().encode("senha@teste789"))
			.scopes("admin")
			.authorizedGrantTypes("password", "refresh_token")
			.accessTokenValiditySeconds(5) //tempo de validade do token
			.refreshTokenValiditySeconds(3600 * 4);  //tempo de vida reshesh tokem
		
	}
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		
		// Adidicionar mais informacao dentro de um token
		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		// adiciona o tokenPersonalozado e o accessTokenConverter()
		// que é o responsavel por converter para JWT
		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(), accessTokenConverter()));
		
		endpoints
			.tokenStore(tokenStore())
			.tokenEnhancer(tokenEnhancerChain)
			.reuseRefreshTokens(false) //para sempre buscar um novo refresh token
			.authenticationManager(authenticationManager); //usuario de autenticacao
		
	}
	
	// utilizacao do JWT
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
		accessTokenConverter.setSigningKey("spring-security");
		return accessTokenConverter;
	}
	
	// responsavel por armazenar os token ativos
	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}
	
	// para adicinar mais informacoes ao token
	@Bean
	public TokenEnhancer tokenEnhancer() {
		return new CustomTokenEnhancer();
	}
	
}
