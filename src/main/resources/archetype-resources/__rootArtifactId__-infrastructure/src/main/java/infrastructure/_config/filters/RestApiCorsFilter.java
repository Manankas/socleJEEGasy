#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
//package ${package}.infrastructure._config.filters;
//
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.filter.CorsFilter;
//
///**
// * @author Fenonantenaina
// *
// */
//public class RestApiCorsFilter extends CorsFilter {
//
//	public RestApiCorsFilter() {
//		super(configurationSource());
//	}
//
//	private static UrlBasedCorsConfigurationSource configurationSource() {
//		 CorsConfiguration config = new CorsConfiguration();
//		 config.setAllowCredentials(true);
//		 config.addAllowedOrigin("*");
//		 config.addAllowedHeader("*");
//		 config.addAllowedMethod("*");
//		 UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		 source.registerCorsConfiguration("/api/**", config);
//		 source.registerCorsConfiguration("/oauth/token", config);
//		 return source;
//	}
//}
