package project.LibraryManage.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import project.LibraryManage.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity,Long> {

}
