package com.example.produtosapi.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("springproject")
public class ProjectProperty {

	private final Seguranca seguranca = new Seguranca();
	
	public Seguranca getSeguranca() {return seguranca;}
	
	public static class Seguranca {

		private boolean enableHttps;
		private int cookieAge;

		public boolean isEnableHttps() {return enableHttps;}
		public void setEnableHttps(boolean enableHttps) {this.enableHttps = enableHttps;}
		public int getCookieAge() {return cookieAge;}
		public void setCookieAge(int cookieAge) {this.cookieAge = cookieAge;}
		

	}
	
}
