package com.example.demoapi.config;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.index.IndexOperations;

@Configuration
public class MongoIndexConfig {

    private static final Logger logger = LoggerFactory.getLogger(MongoIndexConfig.class);
    private static final String COLLECTION_NAME = "courses";

    @Bean
    public ApplicationRunner createCourseIndexes(MongoTemplate mongoTemplate) {
        return args -> {
            try {
                IndexOperations indexOperations = mongoTemplate.indexOps(COLLECTION_NAME);

                ensureIndexIfMissing(
                        mongoTemplate,
                        indexOperations,
                        new Document("category", 1),
                        new Index().on("category", Sort.Direction.ASC).named("idx_courses_category")
                );

                ensureIndexIfMissing(
                        mongoTemplate,
                        indexOperations,
                        new Document("published", 1),
                        new Index().on("published", Sort.Direction.ASC).named("idx_courses_published")
                );

                ensureIndexIfMissing(
                        mongoTemplate,
                        indexOperations,
                        new Document("category", 1).append("published", 1),
                        new Index()
                                .on("category", Sort.Direction.ASC)
                                .on("published", Sort.Direction.ASC)
                                .named("idx_courses_category_published")
                );

                logger.info("MongoDB indexes checked/created for {} collection", COLLECTION_NAME);
            } catch (Exception exception) {
                logger.warn(
                        "Skipping MongoDB index initialization for {} because MongoDB is unavailable: {}",
                        COLLECTION_NAME,
                        exception.getMessage()
                );
                logger.debug("MongoDB index initialization failure details", exception);
            }
        };
    }

    private void ensureIndexIfMissing(
            MongoTemplate mongoTemplate,
            IndexOperations indexOperations,
            Document expectedKey,
            Index index
    ) {
        if (hasIndexWithKey(mongoTemplate, COLLECTION_NAME, expectedKey)) {
            logger.info("Skipping existing index for {} with key {}", COLLECTION_NAME, expectedKey.toJson());
            return;
        }

        indexOperations.ensureIndex(index);
    }

    private boolean hasIndexWithKey(MongoTemplate mongoTemplate, String collectionName, Document expectedKey) {
        for (Document indexDoc : mongoTemplate.getCollection(collectionName).listIndexes()) {
            Object key = indexDoc.get("key");
            if (expectedKey.equals(key)) {
                return true;
            }
        }
        return false;
    }
}