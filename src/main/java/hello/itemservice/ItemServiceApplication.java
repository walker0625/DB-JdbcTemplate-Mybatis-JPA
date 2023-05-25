package hello.itemservice;

import hello.itemservice.config.*;
import hello.itemservice.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;


@Slf4j
//@Import(MemoryConfig.class)
@Import(JdbcTemplateConfig.class)
@SpringBootApplication(scanBasePackages = "hello.itemservice.web")
public class ItemServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItemServiceApplication.class, args);
	}

	@Bean
	@Profile("local")
	public TestDataInit testDataInit(ItemRepository itemRepository) {
		return new TestDataInit(itemRepository);
	}

	/* application.properties의 datasource 정보가 없으면 부트가 자동으로 memory mode로 db를 실행시킴
	@Bean
	@Profile("test")
	public DataSource dataSource() {
		log.info("h2 embedded mode");

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:mem:db;DB_CLOSE_DELAY=-1"); // jdbc:h2:mem:db > embedded mode h2 사용
		dataSource.setUsername("sa");                          // DB_CLOSE_DELAY=-1 > 모든 connection이 끝나도 db가 꺼지지 않도록 함
		dataSource.setPassword("");

		// 기본 schema는 /resource/schema.sql 파일에 작성하면 db가 올라오면서 자동 실행
		return dataSource;
	}
	*/
}
