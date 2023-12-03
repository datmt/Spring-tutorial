package com.datmt.spring.batch.config;

import com.datmt.spring.batch.models.OriginalUser;
import com.datmt.spring.batch.models.TargetUser;
import com.datmt.spring.batch.processor.UserProcessor;
import jakarta.transaction.TransactionManager;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.support.JdbcTransactionManager;

import javax.sql.DataSource;

@Configuration
public class BatchConfig {
    @Bean
    public JdbcCursorItemReader<OriginalUser> reader(DataSource dataSource) {
        return new JdbcCursorItemReaderBuilder<OriginalUser>()
                .name("myDataReader")
                .dataSource(dataSource)
                .sql("SELECT * FROM users")
                .rowMapper(new BeanPropertyRowMapper<>(OriginalUser.class))
                .build();
    }

    @Bean
    public MongoItemWriter<TargetUser> writer(MongoTemplate mongoTemplate) {
        MongoItemWriter<TargetUser> writer = new MongoItemWriter<>();
        writer.setTemplate(mongoTemplate);
        writer.setCollection("batch_job");
        return writer;
    }

    @Bean
    public UserProcessor processor() {
        return new UserProcessor();
    }

    @Bean
    public Step step1(
            JobRepository jobRepository,
            JdbcTransactionManager transactionManager,
            ItemProcessor<OriginalUser, TargetUser> processor,
            ItemReader<OriginalUser> reader,
            ItemWriter<TargetUser> writer) {
        return new StepBuilder("step1", jobRepository)
                .<OriginalUser, TargetUser>chunk(10, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    public JdbcTransactionManager transactionManager(DataSource dataSource) {
        return new JdbcTransactionManager(dataSource);
    }
    @Bean
    public Job job(JobRepository jobRepository, Step step1) {
        return new JobBuilder("CopyToDocJob", jobRepository)
                .start(step1)
                .build();
    }
}
