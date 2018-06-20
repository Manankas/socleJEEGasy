#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.common.databases.typedefs;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.TypeDef;
import org.jasypt.hibernate5.type.EncryptedStringType;

/**
 * @author Fenonantenaina
 *
 */
@Inherited
@Documented
@Retention(RUNTIME)
@Target(ElementType.TYPE)
@TypeDef(
        name=TypeDefSaltedEncryptedString.TYPE_ENCRYPTED_STRING,
        typeClass = EncryptedStringType.class,
        parameters = {
            @Parameter(name="encryptorRegisteredName",value=TypeDefSaltedEncryptedString.ENCRYPTOR)
        }
    )
public @interface TypeDefSaltedEncryptedString {

    public static final String ENCRYPTOR = "hibernateStringSaltedEncryptor";
    public static final String TYPE_ENCRYPTED_STRING = "saltedEncryptedString";
}
