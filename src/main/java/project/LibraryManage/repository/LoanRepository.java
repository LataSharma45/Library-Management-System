package project.LibraryManage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.LibraryManage.entity.LoanEntity;

@Repository
public interface LoanRepository extends JpaRepository<LoanEntity,Long> {

}
