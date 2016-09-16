package com.mancom.batch;

import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.mancom.domain.WebsiteData;
import com.mancom.repository.WebsiteDataRepository;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	public DataSource dataSource;

	@Inject
	private WebsiteDataRepository websiteDataRepository;

	@Bean
	public FlatFileItemReader<WebsiteData> reader() {

		FlatFileItemReader<WebsiteData> reader = new FlatFileItemReader<WebsiteData>();
		reader.setResource(new ClassPathResource("data.csv"));
		reader.setLinesToSkip(1);

		reader.setLineMapper(new DefaultLineMapper<WebsiteData>() {
			{
				setLineTokenizer(new DelimitedLineTokenizer("|") {
					{
						setNames(new String[] { "downloadDate", "url", "counter" });
					}
				});
				setFieldSetMapper(new WebsiteDataFieldSetMapper());
			}
		});

		return reader;
	}

	@Bean
	public JdbcBatchItemWriter<WebsiteData> writer() {
		JdbcBatchItemWriter<WebsiteData> writer = new JdbcBatchItemWriter<WebsiteData>();
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<WebsiteData>());
		writer.setSql(
				"insert into website_data (download_date, url, counter) VALUES (trunc(:downloadDate), :url, :counter)");
		writer.setDataSource(dataSource);
		return writer;
	}
	// end::readerwriterprocessor[]

	// @Bean
	// public ItemWriter jpawriter(final List <? extends FieldSet> fs) {
	//
	// for (FieldSet fieldSet : fs) {
	// WebsiteData wsd = new WebsiteData();
	//
	// wsd.setDownloadDate(fieldSet.readDate("downloadDate"));
	// wsd.setUrl(fieldSet.readString("url"));
	// wsd.setCounter(fieldSet.readLong("counter"));
	// websiteDataRepository.save(wsd);
	// }
	// }

	// tag::jobstep[]
	@Bean
	public Job importUserJob() {
		return jobBuilderFactory.get("importWebsiteDataJob").incrementer(new RunIdIncrementer()).flow(step1()).end()
				.build();
	}

	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step").<WebsiteData, WebsiteData>chunk(10).reader(reader()).writer(writer())
				.build();
	}
	// end::jobstep[]
}