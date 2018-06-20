#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.data._config;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.jasypt.encryption.pbe.PBEStringCleanablePasswordEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.hibernate5.encryptor.HibernatePBEStringEncryptor;
import org.jasypt.salt.SaltGenerator;
import org.jasypt.salt.ZeroSaltGenerator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import ${package}.common.databases.typedefs.TypeDefEncryptedString;
import ${package}.common.databases.typedefs.TypeDefSaltedEncryptedString;


/**
 * @author Fenonantenaina
 *
 */
@Configuration
@EntityScan(basePackages = { "${package}.data.entites" })
@ComponentScan(basePackages = { "${package}.data.properties" })
public class DomainConfig {


    @Bean(name="zeroSaltGenerator")
    public SaltGenerator zeroSaltGenerator() {
        return new ZeroSaltGenerator();
    }
    
    @Bean(name="saltGenerator")
    public SaltGenerator saltGenerator() {
        return new org.jasypt.salt.RandomSaltGenerator();
    }
    
    @Bean(name="strongEncryptor")
    public PooledPBEStringEncryptor strongEncryptor(@Qualifier("zeroSaltGenerator") SaltGenerator zeroSaltGenerator, 
            @Value("${symbol_dollar}{application.bdd-encryptor.algorithm}") String algorithm,
            @Value("${symbol_dollar}{application.bdd-encryptor.password}") String password,
            @Value("${symbol_dollar}{application.bdd-encryptor.pool-size}") Integer poolSize) {
        System.out.println("====================ZERO SALTED=====================");
        System.out.format("==> application.bdd-encryptor.algorithm : %s${symbol_escape}n", algorithm);
        System.out.format("==> application.bdd-encryptor.password : %s${symbol_escape}n", password);
        System.out.format("==> application.bdd-encryptor.pool-size : %d${symbol_escape}n", poolSize);
        System.out.format("==> Type of SaltGenerator : %s${symbol_escape}n", zeroSaltGenerator.getClass().toString());
        System.out.println("============================================");
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        encryptor.setSaltGenerator(zeroSaltGenerator);
        encryptor.setProvider(new BouncyCastleProvider());
        encryptor.setAlgorithm(algorithm);
        encryptor.setPoolSize(poolSize);
        encryptor.setPassword(password);
        return encryptor;
    }
    
       @Bean(name="strongSaltedEncryptor")
        public PooledPBEStringEncryptor strongSaltedEncryptor(@Qualifier("saltGenerator") SaltGenerator saltGenerator, 
                @Value("${symbol_dollar}{application.bdd-encryptor.algorithm}") String algorithm,
                @Value("${symbol_dollar}{application.bdd-encryptor.password}") String password,
                @Value("${symbol_dollar}{application.bdd-encryptor.pool-size}") Integer poolSize) {
            System.out.println("===================SALTED======================");
            System.out.format("==> application.bdd-encryptor.algorithm : %s${symbol_escape}n", algorithm);
            System.out.format("==> application.bdd-encryptor.password : %s${symbol_escape}n", password);
            System.out.format("==> application.bdd-encryptor.pool-size : %d${symbol_escape}n", poolSize);
            System.out.format("==> Type of SaltGenerator : %s${symbol_escape}n", saltGenerator.getClass().toString());
            System.out.println("============================================");
            PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
            encryptor.setSaltGenerator(saltGenerator);
            encryptor.setProvider(new BouncyCastleProvider());
            encryptor.setAlgorithm(algorithm);
            encryptor.setPoolSize(poolSize);
            encryptor.setPassword(password);
            return encryptor;
        }

    @Bean(name=TypeDefEncryptedString.ENCRYPTOR)
    public HibernatePBEStringEncryptor hibernateStringEncryptor(@Qualifier("strongEncryptor") PooledPBEStringEncryptor strongEncryptor) {
        HibernatePBEStringEncryptor hbEncryptor = new HibernatePBEStringEncryptor();
        hbEncryptor.setEncryptor(strongEncryptor);
        hbEncryptor.setRegisteredName(TypeDefEncryptedString.ENCRYPTOR);
        return hbEncryptor;
    }

       @Bean(name=TypeDefSaltedEncryptedString.ENCRYPTOR)
        public HibernatePBEStringEncryptor hibernateStringSaltedEncryptor(@Qualifier("strongSaltedEncryptor") PooledPBEStringEncryptor strongSaltedEncryptor) {
            HibernatePBEStringEncryptor hbEncryptor = new HibernatePBEStringEncryptor();
            hbEncryptor.setEncryptor(strongSaltedEncryptor);
            hbEncryptor.setRegisteredName(TypeDefSaltedEncryptedString.ENCRYPTOR);
            return hbEncryptor;
        }

}

