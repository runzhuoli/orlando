package config;

import java.util.ArrayList;
import java.util.List;

import ca.rlm.orlando.springmvc.PackageInfo;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.AbstractHandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * Java based Web context configuration class. Including argument resolution, message converter,
 * view resolution etc.,
 *
 * @author Rugal Bernstein
 * @since 0.2
 */
@ComponentScan(basePackageClasses = PackageInfo.class)
@Configuration
@EnableWebMvc
public class SpringMvcApplicationContext implements WebMvcConfigurer {

  /**
   * Configure servlet.
   *
   * @param configurer bean
   */
  @Override
  public void configureDefaultServletHandling(final DefaultServletHandlerConfigurer configurer) {
    configurer.enable();
  }

  /**
   * Configure content negotiation.
   *
   * @param configurer bean
   */
  @Override
  public void configureContentNegotiation(final ContentNegotiationConfigurer configurer) {
    configurer.favorPathExtension(false).favorParameter(false);
    configurer.defaultContentType(MediaType.APPLICATION_JSON);
  }

  /**
   * Register message converter.
   *
   * @param converters bean
   */
  @Override
  public void configureMessageConverters(final List<HttpMessageConverter<?>> converters) {
    converters.add(gsonHttpMessageConverter());
    converters.add(byteArrayHttpMessageConverter());
  }

  /**
   * Configure static resource.
   *
   * @param registry bean
   */
  @Override
  public void addResourceHandlers(final ResourceHandlerRegistry registry) {
  }

  /**
   * Configure springmvc interceptor.
   *
   * @param registry bean
   */
  @Override
  public void addInterceptors(final InterceptorRegistry registry) {
  }

  /**
   * Configure CORS.
   *
   * @param registry bean
   */
  @Override
  public void addCorsMappings(final CorsRegistry registry) {
    registry.addMapping("/**")
      .allowedOrigins("*")
      .allowedMethods("*")
      .allowedHeaders("*");
  }

  private HttpMessageConverter byteArrayHttpMessageConverter() {
    final List<MediaType> supportedMediaTypes = new ArrayList<>();
    supportedMediaTypes.add(MediaType.IMAGE_GIF);
    supportedMediaTypes.add(MediaType.IMAGE_JPEG);
    supportedMediaTypes.add(MediaType.IMAGE_PNG);
    final ByteArrayHttpMessageConverter messageConverter = new ByteArrayHttpMessageConverter();
    messageConverter.setSupportedMediaTypes(supportedMediaTypes);
    return messageConverter;
  }

  private HttpMessageConverter gsonHttpMessageConverter() {
    final GsonHttpMessageConverter messageConverter = new GsonHttpMessageConverter();
    messageConverter.setGson(new Gson());
    final List<MediaType> supportedMediaTypes = new ArrayList<>();
    supportedMediaTypes.add(MediaType.APPLICATION_JSON);
    messageConverter.setSupportedMediaTypes(supportedMediaTypes);
    return messageConverter;
  }

  /**
   * Handler adapter.
   *
   * @return adapter
   */
  @Bean
  public HandlerAdapter handlerAdapter() {
    return new RequestMappingHandlerAdapter();
  }

  /**
   * Handler mapping.
   *
   * @return mapping
   */
  @Bean
  public AbstractHandlerMapping defaultAnnotationHandlerMapping() {
    final RequestMappingHandlerMapping mapping = new RequestMappingHandlerMapping();
    mapping.setUseSuffixPatternMatch(false);
    return mapping;
  }
}
