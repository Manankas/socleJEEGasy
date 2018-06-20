#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.common.test.jbehave;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.steps.AbstractStepsFactory;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.ClassUtils;

/**
 * @author Fenonantenaina
 *
 */
public class CustomSpringStepsFactory extends AbstractStepsFactory implements ApplicationContextAware {


	private ApplicationContext context;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		context = applicationContext;
	}
	
	public CustomSpringStepsFactory(Configuration configuration, ApplicationContext appContext) {
		super(configuration);
		this.context = appContext;
	}

	@Override
	protected List<Class<?>> stepsTypes() {
		// Using set because in some cases (eg: scoped proxies),
		// there may be two actual beans for each defined bean).
		Set<Class<?>> types = new HashSet<Class<?>>();
		while(context == null){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for (String name : context.getBeanDefinitionNames()) {
			Class<?> type = beanType(name);
			if (isAllowed(type) && hasAnnotatedMethods(type)) {
				types.add(type);
			}
		}
		return new ArrayList<Class<?>>(types);
	}

	/**
	 * Checks if type returned from context is allowed, i.e. not null and not
	 * abstract.
	 * 
	 * @param type
	 *            the Class of the bean
	 * @return A boolean, <code>true</code> if allowed
	 */
	protected boolean isAllowed(Class<?> type) {
		return type != null && !Modifier.isAbstract(type.getModifiers());
	}

	public Object createInstanceOfType(Class<?> type) {
		for (String name : context.getBeanDefinitionNames()) {
			Class<?> beanType = beanType(name);
			if (type.equals(beanType)) {
				return context.getBean(name);
			}
		}
		throw new StepsInstanceNotFound(type, this);
	}

	/**
	 * Return the bean type, untangling the proxy if needed
	 * 
	 * @param name
	 *            the bean name
	 * @return The Class of the bean
	 */
	private Class<?> beanType(String name) {
		Class<?> type = context.getType(name);
		if (ClassUtils.isCglibProxyClass(type)) {
			return AopProxyUtils.ultimateTargetClass(context.getBean(name));
		}
		return type;
	}

}
