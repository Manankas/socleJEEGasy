#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/**
 * @author Fenonantenaina
 *
 */
@TypeDef(name = TypeDefEncryptedString.TYPE_ENCRYPTED_STRING, typeClass = EncryptedStringType.class, parameters = {
		@Parameter(name = "encryptorRegisteredName", value = TypeDefEncryptedString.ENCRYPTOR) })
@TypeDef(name = TypeDefSaltedEncryptedString.TYPE_ENCRYPTED_STRING, typeClass = EncryptedStringType.class, parameters = {
		@Parameter(name = "encryptorRegisteredName", value = TypeDefSaltedEncryptedString.ENCRYPTOR) }) 
package ${package}.data.entites;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.TypeDef;
import org.jasypt.hibernate5.type.EncryptedStringType;

import ${package}.common.databases.typedefs.TypeDefEncryptedString;
import ${package}.common.databases.typedefs.TypeDefSaltedEncryptedString;
