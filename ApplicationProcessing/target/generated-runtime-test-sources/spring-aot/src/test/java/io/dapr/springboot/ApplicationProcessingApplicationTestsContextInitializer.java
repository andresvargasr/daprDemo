package io.dapr.springboot;

import org.springframework.aot.beans.factory.BeanDefinitionRegistrar;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

public final class ApplicationProcessingApplicationTestsContextInitializer {
  public static void registerDaprBeanPostProcessor(DefaultListableBeanFactory beanFactory) {
    BeanDefinitionRegistrar.of("daprBeanPostProcessor", DaprBeanPostProcessor.class).withConstructor(ConfigurableBeanFactory.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> new DaprBeanPostProcessor(attributes.get(0)))).register(beanFactory);
  }
}
