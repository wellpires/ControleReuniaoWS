package br.com.everis.controlereunioesws.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import br.com.everis.controlereunioesws.ControleReunioesWS;

@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses={ControleReunioesWS.class})
public class AppWebConfiguration extends WebMvcConfigurerAdapter {

}
