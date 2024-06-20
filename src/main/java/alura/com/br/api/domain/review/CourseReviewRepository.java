package alura.com.br.api.domain.review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseReviewRepository extends JpaRepository<CourseReview, Long> {
    List<CourseReview> findByCourseId(Long courseId);

    @Query(value =
            "SELECT " +
                    "    CASE WHEN COUNT(*) != 0 THEN " +
                    "        (COUNT(CASE WHEN rating >= 9 THEN 1 END) - " +
                    "         COUNT(CASE WHEN rating <= 6 THEN 1 END)) / " +
                    "         COUNT(*) * 100 " +
                    "    ELSE " +
                    "        0 " +
                    "    END as nps " +
                    "FROM " +
                    "    course_review " +
                    "WHERE " +
                    "    course_id = :courseId",
            nativeQuery = true)
    double calculateNPS(@Param("courseId") Long courseId);
}

