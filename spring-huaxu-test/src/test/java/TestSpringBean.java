import bean.MyBean;
import bean.aop.AopBean;
import bean.factoryBean.Car;
import bean.lookup.LookUpTestCode;
import bean.replacedMenthod.ChangeMethod;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.context.support.XmlWebApplicationContext;

import java.io.IOException;
import java.io.InputStream;

/**
 * <p>项目名称: spring</p>
 * <p>文件名称: MyBeanFactoryTest</p>
 * <p>文件描述: </p>
 * <p>创建日期: 2019/06/26 16:21</p>
 * <p>创建用户：huaxu</p>
 */
public class TestSpringBean {

	@Test
	public void testBeanFactory() {
		BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("spring-context.xml"));

		BeanDefinition beanDefinition = ((XmlBeanFactory) beanFactory).getBeanDefinition("myBean");
		Assert.assertTrue("12ms".equals(beanDefinition.getAttribute("speed")));

		MyBean myBean = (MyBean) beanFactory.getBean("myBean");
		Assert.assertTrue("strTest".equals(myBean.getStr()));
	}

	@Test
	public void testLookup() {
		BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("spring-context.xml"));
		LookUpTestCode lookUpTestCode = (LookUpTestCode) beanFactory.getBean("lookupBean");
		lookUpTestCode.showMe();
	}

	@Test
	public void testMethod(){
		BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("spring-context.xml"));
		ChangeMethod method = (ChangeMethod) beanFactory.getBean("testMethod");
		method.changeMe();
		method.changeMe("test");
	}
	@Test
	public void testStaticFactoryBean() throws IOException {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
		Car bmw = (Car) context.getBean("bmwCar");
		Car audi = (Car) context.getBean("audiCar");
		System.out.println(bmw);
		System.out.println(audi);
	}

	@Test
	public void testInstanceFactoryBean() throws IOException {
		BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("spring-context.xml"));
		Car card4 = (Car) beanFactory.getBean("car4");
		Car card6 = (Car) beanFactory.getBean("car6");
		System.out.println(card4);
		System.out.println(card6);
	}


	@Test
	public void testAop(){
		BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("spring-aop.xml"));
		AopBean bean = (AopBean) beanFactory.getBean("aopBean");
		Assert.assertTrue("huaxu".equals(bean.getName()));
	}
}