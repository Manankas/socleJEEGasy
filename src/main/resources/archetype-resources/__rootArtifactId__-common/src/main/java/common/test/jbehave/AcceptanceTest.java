#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.common.test.jbehave;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;


/**
 * Ajouter @ContextConfiguration et @ImportResource
 * @author Fenonantenaina
 *
 */
@ActiveProfiles("tests")
@DirtiesContext
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface AcceptanceTest {

}
