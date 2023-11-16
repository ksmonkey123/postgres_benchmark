package ch.awae.postgres_benchmark

import org.slf4j.LoggerFactory
import org.springframework.boot.*
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.stereotype.Component
import kotlin.random.*

@SpringBootApplication
class PostgresBenchmarkApplication

fun main(args: Array<String>) {
    SpringApplication(PostgresBenchmarkApplication::class.java).apply {
        webApplicationType = WebApplicationType.NONE
    }.run(*args)
}

@Component
class InsertRunner(private val binaryDataRepo: BinaryDataRepo) : CommandLineRunner {

    private val logger = LoggerFactory.getLogger(InsertRunner::class.java)

    override fun run(vararg args: String?) {
        val requiredCount = 500_000 - binaryDataRepo.count()

        for (i in 1..requiredCount) {
            if ((i % 1000) == 1L)
                logger.info("writing record $i of $requiredCount")
            val size = Random.nextInt(250_000 .. 600_000)
            val data = Random.nextBytes(size)
            val record = BinaryData(i % 1000, data)
            binaryDataRepo.saveAndFlush(record)
        }

        logger.info("done")

    }

}