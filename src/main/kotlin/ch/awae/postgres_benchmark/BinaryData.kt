package ch.awae.postgres_benchmark

import jakarta.persistence.*
import org.hibernate.annotations.JdbcTypeCode
import org.springframework.data.jpa.repository.*
import org.springframework.stereotype.*
import java.sql.Types
import java.time.*

@Entity
class BinaryData(
    val groupId: Long,
    @Lob
    @JdbcTypeCode(Types.BINARY)
    val data: ByteArray,
    val creUid: String = "dummy",
    val mutUid: String = "dummy",
    val creDat: LocalDateTime = LocalDateTime.now(),
    val mutDat: LocalDateTime = LocalDateTime.now(),
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Version
    var version: Int = 0
}

@Repository
interface BinaryDataRepo : JpaRepository<BinaryData, Long>